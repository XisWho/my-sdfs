package com.my;

public class DataNode implements LifeCycle {

    private Client client;

    @Override
    public void init() {
        client = new Client();
        client.init();

        NioServer nioServer = new NioServer(client.getTransport());
        nioServer.init();
        nioServer.start();
    }

    @Override
    public void start() {
        client.start();
    }

}
