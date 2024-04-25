package com.my;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataNodeManager implements LifeCycle {

    private int datanodeInactiveInterval = 60 * 1000;
    private int datanodeActiveCheckInterval = 30 * 1000;

    private Map<String, DataNodeInfo> datanodes;

    private FSNameSystem namesystem;

    public void setNamesystem(FSNameSystem namesystem) {
        this.namesystem = namesystem;
    }

    @Override
    public void init() {
        datanodes = new ConcurrentHashMap<>();
    }

    @Override
    public void start() {
        // 启动根据心跳检查Datanode是否存活的任务线程
        new DatanodeActiveCheckThread().start();
    }

    /**
     * Datanode注册
     * @param datanodeInfo
     */
    public boolean register(DataNodeInfo datanodeInfo) {
        String ip = datanodeInfo.getIp();
        String hostname = datanodeInfo.getHostname();
        String key = ip + "_" + hostname;

        if(datanodes.containsKey(key)) {
            System.out.println("注册失败，当前已经存在这个DataNode了......");
            return false;
        }

        datanodeInfo.setLatestHeartbeatTime(System.currentTimeMillis());
        datanodes.put(key, datanodeInfo);
        return true;
    }

    public boolean heartbeat(DataNodeInfo datanodeInfo) {
        String ip = datanodeInfo.getIp();
        String hostname = datanodeInfo.getHostname();
        String key = ip + "_" + hostname;
        DataNodeInfo registeredDataNodeInfo = datanodes.get(key);

        if(registeredDataNodeInfo == null) {
            // 这个时候就需要指示DataNode重新注册以及全量上报
            System.out.println("心跳失败，需要重新注册.......");
            return false;
        }

        registeredDataNodeInfo.setLatestHeartbeatTime(System.currentTimeMillis());
        registeredDataNodeInfo.setNioPort(datanodeInfo.getNioPort());
        return true;
    }

    public List<DataNodeInfo> allocateDataNodes(long fileSize) {
        synchronized(this) {
            // 取出来所有的datanode，并且按照已经存储的数据大小来排序
            List<DataNodeInfo> datanodeList = new ArrayList<DataNodeInfo>();
            for(DataNodeInfo datanode : datanodes.values()) {
                datanodeList.add(datanode);
            }

            Collections.sort(datanodeList);

            // 选择存储数据最少的头两个datanode出来
            List<DataNodeInfo> selectedDatanodes = new ArrayList<DataNodeInfo>();
            if(datanodeList.size() >= 2) {
                selectedDatanodes.add(datanodeList.get(0));
                selectedDatanodes.add(datanodeList.get(1));

                // 你可以做成这里是副本数量可以动态配置的，但是我这里的话呢给写死了，就是双副本

                // 默认认为：要上传的文件会被放到那两个datanode上去
                // 此时就应该更新那两个datanode存储数据的大小，加上上传文件的大小
                // 你只有这样做了，后面别人再次要过来上传文件的时候，就可以基于最新的存储情况来进行排序了
                datanodeList.get(0).addStoredDataSize(fileSize);
                datanodeList.get(1).addStoredDataSize(fileSize);
            }

            return selectedDatanodes;
        }
    }

    /**
     * 获取DataNode信息
     * @param ip
     * @param hostname
     * @return
     */
    public DataNodeInfo getDatanode(String ip, String hostname) {
        return datanodes.get(ip + "_" + hostname);
    }

    /**
     * 获取DataNode信息
     * @return
     */
    public DataNodeInfo getDatanode(String id) {
        return datanodes.get(id);
    }

    /**
     * 设置一个DataNode的存储数据的大小
     * @param ip
     * @param hostname
     * @param storedDataSize
     */
    public void setStoredDataSize(String ip, String hostname, Long storedDataSize) {
        DataNodeInfo datanode = datanodes.get(ip + "_" + hostname);
        datanode.setStoredDataSize(storedDataSize);
    }

    public DataNodeInfo reallocateDataNode(long fileSize, String excludedDataNodeId) {
        synchronized(this) {
            // 先得把排除掉的那个数据节点的存储的数据量减少文件的大小
            DataNodeInfo excludedDataNode = datanodes.get(excludedDataNodeId);
            excludedDataNode.addStoredDataSize(-fileSize);

            // 取出来所有的datanode，并且按照已经存储的数据大小来排序
            List<DataNodeInfo> datanodeList = new ArrayList<DataNodeInfo>();
            for(DataNodeInfo datanode : datanodes.values()) {
                if(!excludedDataNode.equals(datanode)) {
                    datanodeList.add(datanode);
                }
            }
            Collections.sort(datanodeList);

            // 选择存储数据最少的一个datanode出来
            DataNodeInfo selectedDatanode = null;
            if(datanodeList.size() >= 1) {
                selectedDatanode = datanodeList.get(0);
                datanodeList.get(0).addStoredDataSize(fileSize);
            }

            return selectedDatanode;
        }
    }

    /**
     * 根据心跳检查Datanode是否存活的任务线程
     */
    class DatanodeActiveCheckThread extends Thread {

        @Override
        public void run() {
            while (true) {
                List<DataNodeInfo> toRemoveDatanodeKeys = new ArrayList<>();
                for (Map.Entry<String, DataNodeInfo> entry : datanodes.entrySet()) {
                    String key = entry.getKey();
                    DataNodeInfo datanodeInfo = entry.getValue();
                    long interval = System.currentTimeMillis() - datanodeInfo.getLatestHeartbeatTime();
                    if (interval > datanodeInactiveInterval) {
                        toRemoveDatanodeKeys.add(datanodeInfo);
                    }
                }

                if(!toRemoveDatanodeKeys.isEmpty()) {
                    for(DataNodeInfo toRemoveDatanode : toRemoveDatanodeKeys) {
                        System.out.println("数据节点【" + toRemoveDatanode + "】宕机，需要 进行副本复制......");
                        // 还有一个地方是在哪儿呢？就是这个叫做，我们还维护了一块是数据节点和副本的关系
                        createLostReplicaTask(toRemoveDatanode);
                        // 来实现DataNode宕机之后的一些处理
                        datanodes.remove(toRemoveDatanode.getIp() + "_" + toRemoveDatanode.getHostname());
                        System.out.println("从内存数据结构中删除掉这个数据节点，" + datanodes);
                        // 删除掉这个数据结构
                        namesystem.removeDeadDatanode(toRemoveDatanode);
                    }
                }

                try {
                    Thread.sleep(datanodeActiveCheckInterval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 创建丢失副本的复制任务
     */
    public void createLostReplicaTask(DataNodeInfo deadDatanode) {
        List<String> files = namesystem.getFilesByDatanode(
                deadDatanode.getIp(), deadDatanode.getHostname());

        for(String file : files) {
            String filename = file.split("_")[0];
            Long fileLength = Long.valueOf(file.split("_")[1]);
            // 获取这个复制任务的源头数据节点
            DataNodeInfo sourceDatanode = namesystem.getReplicateSource(filename, deadDatanode);
            // 复制任务的目标数据节点，第一，不能是已经死掉的节点 ；第二，不能是已经有这个副本的节点
            DataNodeInfo destDatanode = allocateReplicateDataNode(fileLength, sourceDatanode, deadDatanode);

            DataNodeInfo.ReplicateTask replicateTask = new DataNodeInfo.ReplicateTask(
                    filename, fileLength, sourceDatanode, destDatanode);

            // 将复制任务放到目标数据节点的任务队列里去
            destDatanode.addReplicateTask(replicateTask);
            System.out.println("为目标数据节点生成一个副本复制任务，" + replicateTask);
        }
    }

    /**
     * 分配用来复制副本的数据节点
     * @param fileSize
     * @return
     */
    public DataNodeInfo allocateReplicateDataNode(long fileSize,
                                                  DataNodeInfo sourceDatanode, DataNodeInfo deadDatanode) {
        synchronized(this) {
            List<DataNodeInfo> datanodeList = new ArrayList<DataNodeInfo>();
            for(DataNodeInfo datanode : datanodes.values()) {
                if(!datanode.equals(sourceDatanode) &&
                        !datanode.equals(deadDatanode)) {
                    datanodeList.add(datanode);
                }
            }
            Collections.sort(datanodeList);

            DataNodeInfo selectedDatanode = null;
            if(!datanodeList.isEmpty()) {
                // 取第一个
                selectedDatanode = datanodeList.get(0);
                datanodeList.get(0).addStoredDataSize(fileSize);
            }

            return selectedDatanode;
        }
    }

}
