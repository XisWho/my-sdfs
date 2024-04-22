package com.my;

public class FSNamesystem {

    private FSDirectory directory;

    public FSNamesystem() {
        this.directory = new FSDirectory();
    }

    /**
     * 创建目录
     * @param path 目录路径
     * @return 是否成功
     */
    public Boolean mkdir(long txid, String path) throws Exception {
        this.directory.mkdir(txid, path); // 第一步就是基于FSDirectory这个组件来真正去管理文件目录树
        return true;
    }

    public FSImage getFSImage() {
        return directory.getFSImage();
    }

}
