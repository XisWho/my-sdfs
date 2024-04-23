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
    public void register(DataNodeInfo datanodeInfo) {
        String ip = datanodeInfo.getIp();
        String hostname = datanodeInfo.getHostname();
        String key = ip + "_" + hostname;
        datanodeInfo.setLatestHeartbeatTime(System.currentTimeMillis());
        datanodes.put(key, datanodeInfo);
    }

    public void heartbeat(DataNodeInfo datanodeInfo) {
        String ip = datanodeInfo.getIp();
        String hostname = datanodeInfo.getHostname();
        String key = ip + "_" + hostname;
        DataNodeInfo registeredDataNodeInfo = datanodes.get(key);
        registeredDataNodeInfo.setLatestHeartbeatTime(System.currentTimeMillis());
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
     * 根据心跳检查Datanode是否存活的任务线程
     */
    class DatanodeActiveCheckThread extends Thread {

        @Override
        public void run() {
            while (true) {
                List<String> toRemoveDatanodeKeys = new ArrayList<>();
                for (Map.Entry<String, DataNodeInfo> entry : datanodes.entrySet()) {
                    String key = entry.getKey();
                    DataNodeInfo datanodeInfo = entry.getValue();
                    long interval = System.currentTimeMillis() - datanodeInfo.getLatestHeartbeatTime();
                    if (interval > datanodeInactiveInterval) {
                        toRemoveDatanodeKeys.add(key);
                    }
                }

                for (String toRemoveDatanodeKey : toRemoveDatanodeKeys) {
                    datanodes.remove(toRemoveDatanodeKey);
                }

                try {
                    Thread.sleep(datanodeActiveCheckInterval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
