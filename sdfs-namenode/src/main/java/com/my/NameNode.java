package com.my;

public class NameNode implements LifeCycle {

    private DataNodeManager datanodeManager;
    private FSNameSystem fsNameSystem;

    private NameNodeRpcServer namenodeRpcServer;


    public NameNode() {
        this.datanodeManager = new DataNodeManager();
        this.fsNameSystem = new FSNameSystem();
        this.namenodeRpcServer = new NameNodeRpcServer(datanodeManager, fsNameSystem);
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
    }

}
