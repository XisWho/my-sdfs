package com.my;

public class NameNodeBootstrap {

    public static void main(String[] args) {
        NameNode namenode = new NameNode();
        namenode.init();
        namenode.start();
    }

}
