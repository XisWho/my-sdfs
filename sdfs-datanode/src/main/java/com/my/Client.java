package com.my;

public class Client implements LifeCycle {

    private Transport transport;

    /**
     * 磁盘存储管理组件
     */
    private StorageManager storageManager;

    /**
     * 复制任务管理组件
     */
    private ReplicateManager replicateManager;

    @Override
    public void init() {
        // 如果注册成功了才会执行全量的上报
        this.storageManager = new StorageManager();

        transport = new Transport(this.storageManager);
        this.replicateManager = new ReplicateManager(this.transport);
        transport.setReplicateManager(this.replicateManager);
        transport.init();
    }

    @Override
    public void start() {
        Boolean registerResult = transport.register();

        // 如果注册成功了才会执行全量的上报，此种情况对应datanode快速重启
        if (registerResult) {
            StorageInfo storageInfo = this.storageManager.getStorageInfo();
            this.transport.reportCompleteStorageInfo(storageInfo);
        } else {
            System.out.println("不需要全量上报存储信息......");
        }

        transport.startHeartbeat();
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
