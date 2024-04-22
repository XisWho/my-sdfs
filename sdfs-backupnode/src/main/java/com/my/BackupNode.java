package com.my;

public class BackupNode {

    private FSNamesystem namesystem;
    private NameNodeRpcClient namenode;

    public static void main(String[] args) {
        BackupNode backupNode = new BackupNode();
        backupNode.init();
        backupNode.start();
    }

    public void init() {
        this.namesystem = new FSNamesystem();
        this.namenode = new NameNodeRpcClient();
    }

    public void start() {
        EditsLogFetcher editsLogFetcher = new EditsLogFetcher(namesystem, namenode);
        editsLogFetcher.start();
    }

}
