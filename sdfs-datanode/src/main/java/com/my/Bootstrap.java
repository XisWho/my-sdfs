package com.my;

public class Bootstrap {

    public static void main(String[] args) {
        DataNode datanode = new DataNode();
        datanode.init();
        datanode.start();
    }

}
