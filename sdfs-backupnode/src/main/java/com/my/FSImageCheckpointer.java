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
    public static final Integer CHECKPOINT_INTERVAL = 60;

    private BackupNode backupNode;
    private FSNamesystem namesystem;
    private NameNodeRpcClient namenode;

    private String lastFsimageFile = "";

    private long checkpointTime = System.currentTimeMillis();

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
                if(!namesystem.isFinishedRecover()) {
                    System.out.println("当前还没完成元数据恢复，不进行checkpoint......");
                    Thread.sleep(1000);
                    continue;
                }

                if(lastFsimageFile.equals("")) {
                    this.lastFsimageFile = namesystem.getCheckpointFile();
                }

                long now = System.currentTimeMillis();
                // 判断backupNode重启后进行checkpoint的时间是否距离保存的checkpointTime有相当的间隔
                // 本来第一个版本中checkpointTime会去取FSNamesystem.checkpointTime的（磁盘保存的）
                // 但是后面改成了checkpointTime = System.currentTimeMillis();其实这样也对，这样的意思是等待CHECKPOINT_INTERVAL之后才能checkpoint
                // 其实跟TimeUnit.SECONDS.sleep(CHECKPOINT_INTERVAL);的意思也一样，这里是可以有优化的，考虑一下FSNamesystem中checkpointTime保存到磁盘是否有必要
                if(now - checkpointTime > CHECKPOINT_INTERVAL) {
                    if (!namenode.isNamenodeRunning()) {
                        System.out.println("namenode当前无法访问，不执行checkpoint......");
                        continue;
                    }

                    System.out.println("准备执行checkpoint操作.....");
                    doCheckpoint();
                    System.out.println("完成checkpoint操作......");
                }

                TimeUnit.SECONDS.sleep(CHECKPOINT_INTERVAL);
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
        saveCheckpointInfo(fsimage);
    }

    /**
     * 持久化checkpoint信息
     * @param fsimage
     */
    private void saveCheckpointInfo(FSImage fsimage) {
        String path = "E:\\code\\java-code\\my-project\\my-sdfs\\backupnode\\checkpoint-info.meta";

        RandomAccessFile raf = null;
        FileOutputStream out = null;
        FileChannel channel = null;

        try {
            File file = new File(path);
            if(file.exists()) {
                file.delete();
            }

            long now = System.currentTimeMillis();
            this.checkpointTime = now;
            long checkpointTxid = fsimage.getMaxTxid();
            ByteBuffer buffer = ByteBuffer.wrap(String.valueOf(now + "_" + checkpointTxid + "_" + lastFsimageFile).getBytes());

            raf = new RandomAccessFile(path, "rw");
            out = new FileOutputStream(raf.getFD());
            channel = out.getChannel();

            channel.write(buffer);
            channel.force(false);

            System.out.println("checkpoint信息持久化到磁盘文件......");
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
