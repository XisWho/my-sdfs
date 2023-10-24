package com.my;

public class Namenode implements LifeCycle {

    private DatanodeManager datanodeManager;

    private Server server;

    public Namenode() {
        this.datanodeManager = new DatanodeManager();
        this.server = new Server();
    }

    @Override
    public void init() {
        datanodeManager.init();
        server.init();
    }

    @Override
    public void start() {
        datanodeManager.start();
        server.start();
    }

}
