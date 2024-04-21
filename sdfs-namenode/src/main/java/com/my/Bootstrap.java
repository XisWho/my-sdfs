package com.my;

public class Bootstrap {

    public static void main(String[] args) {
        NameNode namenode = new NameNode();
        namenode.init();
        namenode.start();
    }

}
