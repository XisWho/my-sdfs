package com.my;

public class NameNode implements LifeCycle {

    private DataNodeManager datanodeManager;
    private FSNameSystem fsNameSystem;

    private NameNodeRpcServer namenodeRpcServer;
    private FSImageUploadServer fsImageUploadServer;


    public NameNode() {
        this.datanodeManager = new DataNodeManager();
        this.fsNameSystem = new FSNameSystem(this.datanodeManager);
        this.namenodeRpcServer = new NameNodeRpcServer(datanodeManager, fsNameSystem);
        this.fsImageUploadServer = new FSImageUploadServer();
    }

    @Override
    public void init() {
        datanodeManager.init();
        namenodeRpcServer.init();
    }

    @Override
    public void start() {
        datanodeManager.start();
        namenodeRpcServer.start();
        fsImageUploadServer.start();
    }

}
