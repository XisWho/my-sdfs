package com.my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

public class FSImageCheckpointer extends Thread {

    /**
     * checkpoint操作的时间间隔
     */
    public static final Integer CHECKPOINT_INTERVAL = 20;

    private BackupNode backupNode;
    private FSNamesystem namesystem;
    private NameNodeRpcClient namenode;

    private String lastFsimageFile;

    public FSImageCheckpointer(BackupNode backupNode, FSNamesystem namesystem, NameNodeRpcClient namenode) {
        this.backupNode = backupNode;
        this.namesystem = namesystem;
        this.namenode = namenode;
    }

    @Override
    public void run() {
        System.out.println("fsimage checkpoint定时调度线程启动......");

        while (backupNode.isRunning()) {
            try {
                TimeUnit.SECONDS.sleep(CHECKPOINT_INTERVAL);
                System.out.println("准备执行checkpoint操作.....");
                doCheckpoint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将fsiamge持久化到磁盘上去
     * @throws Exception
     */
    private void doCheckpoint() throws Exception {
        FSImage fsimage = namesystem.getFSImage();
        removeLastFsimageFile();
        writeFSImageFile(fsimage);
        uploadFSImageFile(fsimage);
        updateCheckpointTxid(fsimage);
    }

    /**
     * 写入最新的fsimage文件
     * @throws Exception
     */
    private void writeFSImageFile(FSImage fsimage) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(fsimage.getFsimageJson().getBytes());

        // fsimage的文件名的格式，他呢应该是包含了当前这个里面最后一个editslog的txid
        String fsimageFilePath = "E:\\code\\java-code\\my-project\\my-sdfs\\backupnode\\fsimage-"
                + fsimage.getMaxTxid() + ".image";

        lastFsimageFile = fsimageFilePath;

        RandomAccessFile file = null;
        FileOutputStream out = null;
        FileChannel channel = null;

        try {
            file = new RandomAccessFile(fsimageFilePath, "rw"); // 读写模式，数据写入缓冲区中
            out = new FileOutputStream(file.getFD());
            channel = out.getChannel();

            channel.write(buffer);
            channel.force(false);  // 强制把数据刷入磁盘上
        } finally {
            if(out != null) {
                out.close();
            }
            if(file != null) {
                file.close();
            }
            if(channel != null) {
                channel.close();
            }
        }
    }

    /**
     * 删除上一个fsimage磁盘文件
     */
    private void removeLastFsimageFile() throws Exception {
        if (lastFsimageFile!=null && lastFsimageFile!="") {
            File file = new File(lastFsimageFile);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 上传fsimage文件
     * @param fsimage
     * @throws Exception
     */
    private void uploadFSImageFile(FSImage fsimage) throws Exception {
        FSImageUploader fsimageUploader = new FSImageUploader(fsimage);
        fsimageUploader.start();
    }

    /**
     * 更新checkpoint txid
     * @param fsimage
     */
    private void updateCheckpointTxid(FSImage fsimage) {
        namenode.updateCheckpointTxid(fsimage.getMaxTxid());
    }

}
