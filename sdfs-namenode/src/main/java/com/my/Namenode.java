package com.my;

public class Namenode implements LifeCycle {

    private DatanodeManager datanodeManager;

    private NamenodeRpcServer namenodeRpcServer;

    public Namenode() {
        this.datanodeManager = new DatanodeManager();
        this.namenodeRpcServer = new NamenodeRpcServer(datanodeManager);
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
