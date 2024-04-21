package com.my;

import java.util.ArrayList;
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
