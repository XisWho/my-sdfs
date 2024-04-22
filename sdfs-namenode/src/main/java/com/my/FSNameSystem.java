package com.my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FSNameSystem {

    private FSDirectory fsDirectory;
    private FSEditlog fsEditlog;
    /**
     * 最近一次checkpoint更新到的txid，对应fsimage保存到的最大txid
     */
    private long checkpointTxid;

    public FSNameSystem() {
        fsDirectory = new FSDirectory();
        fsEditlog = new FSEditlog(this);
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

    /**
     * 将checkpoint txid保存到磁盘上去
     */
    public void saveCheckpointTxid() {
        String path = "E:\\code\\java-code\\my-project\\my-sdfs\\editslog\\checkpoint-txid.meta";

        RandomAccessFile raf = null;
        FileOutputStream out = null;
        FileChannel channel = null;

        try {
            File file = new File(path);
            if(file.exists()) {
                file.delete();
            }

            ByteBuffer buffer = ByteBuffer.wrap(String.valueOf(checkpointTxid).getBytes());

            raf = new RandomAccessFile(path, "rw");
            out = new FileOutputStream(raf.getFD());
            channel = out.getChannel();

            channel.write(buffer);
            channel.force(false);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                }
                if(raf != null) {
                    raf.close();
                }
                if(channel != null) {
                    channel.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
