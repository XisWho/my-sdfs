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
        Boolean registerResult = transport.register();
        transport.startHeartbeat();
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
