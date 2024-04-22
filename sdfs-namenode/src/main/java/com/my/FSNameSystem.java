package com.my;

public class FSNameSystem {

    private FSDirectory fsDirectory;
    private FSEditlog fsEditlog;
    /**
     * 最近一次checkpoint更新到的txid
     */
    private long checkpointTxid;

    public FSNameSystem() {
        fsDirectory = new FSDirectory();
        fsEditlog = new FSEditlog();
    }

    public FSEditlog getFsEditlog() {
        return fsEditlog;
    }

    public Boolean mkdir(String path) {
        fsDirectory.mkdir(path);
        fsEditlog.logEdit("{'OP':'MKDIR','PATH':'" + path + "'}");

        return true;
    }

    public void shutdown() {
        fsEditlog.flush();
    }

    public void setCheckpointTxid(long txid) {
        System.out.println("接收到checkpoint txid：" + txid);
        this.checkpointTxid = txid;
    }
    public long getCheckpointTxid() {
        return checkpointTxid;
    }

}
