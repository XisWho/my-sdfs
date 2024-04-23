package com.my;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.my.FSDirectory.INode;

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
        recoverNamespace();
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

    private void recoverNamespace() {
        try {
            loadFSImage();
            loadCheckpointTxid();
            loadEditLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载fsimage文件到内存里来进行恢复
     */
    private void loadFSImage() throws Exception {
        FileInputStream in = null;
        FileChannel channel = null;
        try {
            String path = "E:\\code\\java-code\\my-project\\my-sdfs\\editslog\\fsimage.image";
            File file = new File(path);
            if(!file.exists()) {
                System.out.println("fsimage文件当前不存在，不进行恢复.......");
                return;
            }

            in = new FileInputStream(path);
            channel = in.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024); // 这个参数是可以动态调节的
            // 每次你接受到一个fsimage文件的时候记录一下他的大小，持久化到磁盘上去
            // 每次重启就分配对应空间的大小就可以了
            int count = channel.read(buffer);

            buffer.flip();
            String fsimageJson = new String(buffer.array(), 0, count);
            System.out.println("恢复fsimage文件中的数据：" + fsimageJson);

            INode dirTree = JSONObject.parseObject(fsimageJson, INode.class);
            fsDirectory.setDirTree(dirTree);
        } finally {
            if(in != null) {
                in.close();
            }
            if(channel != null) {
                channel.close();
            }
        }
    }

    /**
     * 加载
     * @return
     * @throws Exception
     */
    private void loadCheckpointTxid() throws Exception {
        FileInputStream in = null;
        FileChannel channel = null;
        try {
            String path = "E:\\code\\java-code\\my-project\\my-sdfs\\editslog\\checkpoint-txid.meta";

            File file = new File(path);
            if(!file.exists()) {
                System.out.println("checkpoint txid文件不存在，不进行恢复.......");
                return;
            }

            in = new FileInputStream(path);
            channel = in.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024); // 这个参数是可以动态调节的
            // 每次你接受到一个fsimage文件的时候记录一下他的大小，持久化到磁盘上去
            // 每次重启就分配对应空间的大小就可以了
            int count = channel.read(buffer);

            buffer.flip();
            long checkpointTxid = Long.valueOf(new String(buffer.array(), 0, count));
            System.out.println("恢复checkpoint txid：" + checkpointTxid);

            this.checkpointTxid = checkpointTxid;
        } finally {
            if(in != null) {
                in.close();
            }
            if(channel != null) {
                channel.close();
            }
        }
    }

    /**
     * 加载和回放editlog
     * @throws Exception
     */
    private void loadEditLog() throws Exception {
        File dir = new File("E:\\code\\java-code\\my-project\\my-sdfs\\editslog\\");

        List<File> files = new ArrayList<File>();
        for(File file : dir.listFiles()) {
            files.add(file);
        }

        Iterator<File> fileIterator = files.iterator();
        while(fileIterator.hasNext()) {
            File file = fileIterator.next();
            if(!file.getName().contains("edits")) {
                fileIterator.remove();
            }
        }

        Collections.sort(files, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                Integer o1StartTxid = Integer.valueOf(o1.getName().split("-")[1]);
                Integer o2StartTxid = Integer.valueOf(o2.getName().split("-")[1]);
                return o1StartTxid - o2StartTxid;
            }

        });

        if(files == null || files.size() == 0) {
            System.out.println("当前没有任何editlog文件，不进行恢复......");
            return;
        }

        for(File file : files) {
            if(file.getName().contains("edits")) {
                System.out.println("准备恢复editlog文件中的数据：" + file.getName());

                String[] splitedName = file.getName().split("-");
                long startTxid = Long.valueOf(splitedName[1]);
                long endTxid = Long.valueOf(splitedName[2].split("[.]")[0]);

                // 如果是checkpointTxid之后的那些editlog都要加载出来
                if(endTxid > checkpointTxid) {
                    String currentEditsLogFile = "E:\\code\\java-code\\my-project\\my-sdfs\\editslog\\edits-"
                            + startTxid + "-" + endTxid + ".log";
                    List<String> editsLogs = Files.readAllLines(Paths.get(currentEditsLogFile),
                            StandardCharsets.UTF_8);

                    for(String editLogJson : editsLogs) {
                        JSONObject editLog = JSONObject.parseObject(editLogJson);
                        long txid = editLog.getLongValue("txid");

                        if(txid > checkpointTxid) {
                            System.out.println("准备回放editlog：" + editLogJson);

                            // 回放到内存里去
                            String op = editLog.getString("OP");

                            if(op.equals("MKDIR")) {
                                String path = editLog.getString("PATH");
                                try {
                                    fsDirectory.mkdir(path);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Boolean createFile(String filename) {
        if(!fsDirectory.createFile(filename)) {
            return false;
        }
        fsEditlog.logEdit(EditLogFactory.create(filename));
        return true;
    }
}
