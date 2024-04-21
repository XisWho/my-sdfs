package com.my;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DatanodeManager implements LifeCycle {

    private int datanodeInactiveInterval = 60 * 1000;
    private int datanodeActiveCheckInterval = 30 * 1000;

    private Map<String, DatanodeInfo> datanodes;

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
    public void register(DatanodeInfo datanodeInfo) {
        String ip = datanodeInfo.getIp();
        String hostname = datanodeInfo.getHostname();
        String key = ip + "_" + hostname;
        datanodeInfo.setLatestHeartbeatTime(System.currentTimeMillis());
        datanodes.put(key, datanodeInfo);
    }

    public void heartbeat(DatanodeInfo datanodeInfo) {
        String ip = datanodeInfo.getIp();
        String hostname = datanodeInfo.getHostname();
        String key = ip + "_" + hostname;
        DatanodeInfo registeredDatanodeInfo = datanodes.get(key);
        registeredDatanodeInfo.setLatestHeartbeatTime(System.currentTimeMillis());
    }

    /**
     * 根据心跳检查Datanode是否存活的任务线程
     */
    class DatanodeActiveCheckThread extends Thread {

        @Override
        public void run() {
            while (true) {
                List<String> toRemoveDatanodeKeys = new ArrayList<>();
                for (Map.Entry<String, DatanodeInfo> entry : datanodes.entrySet()) {
                    String key = entry.getKey();
                    DatanodeInfo datanodeInfo = entry.getValue();
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
