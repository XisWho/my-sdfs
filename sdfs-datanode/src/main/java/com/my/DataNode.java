package com.my;

public class DataNode implements LifeCycle {

    private Client client;

    @Override
    public void init() {
        client = new Client();
        client.init();

        DataNodeNIOServer nioServer = new DataNodeNIOServer();
        nioServer.start();
    }

    @Override
    public void start() {
        client.start();
    }

}
