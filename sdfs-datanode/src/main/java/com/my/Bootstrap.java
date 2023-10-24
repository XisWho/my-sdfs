package com.my;

public class Bootstrap {

    public static void main(String[] args) {
        Datanode datanode = new Datanode();
        datanode.init();
        datanode.start();
    }

}
