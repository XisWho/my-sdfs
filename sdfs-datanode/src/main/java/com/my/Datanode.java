package com.my;

public class Datanode implements LifeCycle {

    private Client client;

    @Override
    public void init() {
        client = new Client();
        client.init();
    }

    @Override
    public void start() {
        client.start();
    }

}
