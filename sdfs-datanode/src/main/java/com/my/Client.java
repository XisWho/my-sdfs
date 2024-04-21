package com.my;

public class Client implements LifeCycle {

    private Transport transport;

    @Override
    public void init() {
        transport = new Transport();
        transport.init();
    }

    @Override
    public void start() {
        transport.register();
        transport.startHeartbeat();
    }

}
