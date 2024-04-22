package com.my;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DoubleBuffer {

    public static final Integer EDIT_LOG_BUFFER_LIMIT = 25 * 1024; // 25kb

    private EditLogBuffer currentBuffer = new EditLogBuffer();
    private EditLogBuffer syncBuffer = new EditLogBuffer();

    long startTxid = 1L;

    /**
     * 已经输入磁盘中的txid范围
     */
    private List<String> flushedTxids = new CopyOnWriteArrayList<>();

    public void write(EditLog log) {
        currentBuffer.write(log);
    }

    public boolean shouldSyncToDisk() {
        if(currentBuffer.size() >= EDIT_LOG_BUFFER_LIMIT) {
            return true;
        }
        return false;
    }

    public void setReadyToSync() {
        EditLogBuffer tmp = currentBuffer;
        currentBuffer = syncBuffer;
        syncBuffer = tmp;
    }

    public void flush() {
        syncBuffer.flush();
        syncBuffer.clear();
    }

    public List<String> getFlushedTxids() {
        return flushedTxids;
    }

    public String[] getBufferedEditsLog() {
        if(currentBuffer.size() == 0) {
            return null;
        }
        String editsLogRawData = new String(currentBuffer.getBufferData());
        return editsLogRawData.split("\n");
    }

    class EditLogBuffer {

        ByteArrayOutputStream out = new ByteArrayOutputStream(EDIT_LOG_BUFFER_LIMIT * 2);

        long endTxid;

        /**
         * 将editslog日志写入缓冲区
         * @param log
         */
        public void write(EditLog log) {
            try {
                endTxid = log.getTxid();
                out.write(log.getContent().getBytes());
                out.write("\n".getBytes());
                System.out.println("EditLogBuffer写入一条editslog"+log.getContent()+"。当前缓冲区大小为"+out.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 获取当前缓冲区已经写入数据的字节数量
         * @return
         */
        public Integer size() {
            return out.size();
        }

        public void flush() {
            byte[] data = out.toByteArray();
            ByteBuffer dataBuffer = ByteBuffer.wrap(data);

            String editsLogFilePath = "E:\\code\\java-code\\my-project\\my-sdfs\\editslog\\edits-"
                    + startTxid + "-" + endTxid + ".log";
            flushedTxids.add(startTxid + "_" + endTxid);

            RandomAccessFile file = null;
            FileOutputStream out = null;
            FileChannel editsLogFileChannel = null;

            try {
                file = new RandomAccessFile(editsLogFilePath, "rw"); // 读写模式，数据写入缓冲区中
                out = new FileOutputStream(file.getFD());
                editsLogFileChannel = out.getChannel();

                editsLogFileChannel.write(dataBuffer);
                editsLogFileChannel.force(false);  // 强制把数据刷入磁盘上
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (file != null) {
                        file.close();
                    }
                    if (editsLogFileChannel != null) {
                        editsLogFileChannel.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            startTxid = endTxid + 1;
        }

        public void clear() {
            out.reset();
        }

        public byte[] getBufferData() {
            return out.toByteArray();
        }
    }
}
