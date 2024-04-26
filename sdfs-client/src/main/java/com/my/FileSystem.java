package com.my;

import java.io.IOException;

public interface FileSystem {

    void mkdir(String path);

    void shutdown();

    /**
     * 上传文件
     * @throws Exception
     */
    Boolean upload(FileInfo fileInfo, ResponseCallback callback) throws Exception;

    /**
     * 重试上传文件
     * @param fileInfo
     * @param excludedHost
     * @return
     * @throws Exception
     */
    Boolean retryUpload(FileInfo fileInfo, Host excludedHost) throws Exception;

    /**
     * 下载文件
     * @param filename 文件名
     * @return 文件的字节数组
     * @throws Exception
     */
    byte[] download(String filename) throws Exception;

}
