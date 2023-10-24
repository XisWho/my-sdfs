package com.my;

public class Client implements LifeCycle {

    private Transport transport;

    @Override
    public void init() {
        transport.init();
    }

    @Override
    public void start() {
        DatanodeInfo datanodeInfo = new DatanodeInfo();
        transport.register(datanodeInfo);
    }

}
