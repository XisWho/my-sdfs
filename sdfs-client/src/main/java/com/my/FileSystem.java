package com.my;

public interface FileSystem {

    void mkdir(String path);

    void shutdown();

    boolean upload(byte[] file, String filename, long fileSize);

    byte[] download(String filename);

}
