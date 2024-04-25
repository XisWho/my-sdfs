package com.my;

import java.io.IOException;

public interface FileSystem {

    void mkdir(String path);

    void shutdown();

    boolean upload(byte[] file, String filename, long fileSize) throws Exception;

    byte[] download(String filename) throws IOException, InterruptedException;

}
