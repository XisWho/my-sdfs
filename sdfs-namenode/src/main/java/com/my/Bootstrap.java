package com.my;

public class Bootstrap {

    public static void main(String[] args) {
        Namenode namenode = new Namenode();
        namenode.init();
        namenode.start();
    }

}
