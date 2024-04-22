package com.my;

public class FSNameSystem {

    private FSDirectory fsDirectory;
    private FSEditlog fsEditlog;

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
}
