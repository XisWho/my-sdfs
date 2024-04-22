package com.my;

import java.util.List;

public class FSEditlog {

    private long txidSeq = 0L;

    private ThreadLocal<Long> localTxid = new ThreadLocal<>();

    private DoubleBuffer doubleBuffer = new DoubleBuffer();

    private volatile Boolean isSchedulingSync = false;
    private volatile Boolean isRunningSync = false;
    private volatile Long syncTxid = 0L;

    public void logEdit(String content) {
        synchronized (this) {
            // 如果需要刷盘，那么说明currentBuffer已满，需要等待切换两个buffer
            waitSchedulingSync();

            txidSeq++;
            localTxid.set(txidSeq);

            EditLog log = new EditLog(txidSeq, content);
            doubleBuffer.write(log);

            if (!doubleBuffer.shouldSyncToDisk()) {
                return;
            }

            // 此时说明currentBuffer已满，准备调度一次同步磁盘的操作
            isSchedulingSync = true;
        }

        // 同步磁盘
        logSync();
    }

    private void waitSchedulingSync() {
        try {
            while(isSchedulingSync) {
                wait(1000);
                // 此时就释放锁，等待一秒再次尝试获取锁，去判断
                // isSchedulingSync是否为false，就可以脱离出while循环
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logSync() {
        // 一般来说，同一时间只会有一个线程会进行刷盘操作
        // 但是如果写满一个buffer的速度超过了将另一个buffer刷盘的速度，那么就会有两个线程同时进到这里
        synchronized (this) {
            Long txid = localTxid.get();

            // 如果说当前正好有人在刷内存缓冲到磁盘中去
            if (isRunningSync) {
                if (txid <= syncTxid) {
                    return;
                }

                try {
                    // 等待别人先刷完
                    while(isRunningSync) {
                        wait(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            doubleBuffer.setReadyToSync();

            syncTxid = txid;
            isSchedulingSync = false;
            notifyAll();
            isRunningSync = true;
        }

        // 真正刷盘
        doubleBuffer.flush();

        synchronized (this) {
            isRunningSync = false;
            notifyAll();
        }
    }

    public void flush() {
        // 需要先切换buffer
        doubleBuffer.setReadyToSync();

        doubleBuffer.flush();
    }

    public List<String> getFlushedTxids() {
        return doubleBuffer.getFlushedTxids();
    }

    public String[] getBufferedEditsLog() {
        synchronized(this) { // 这边此时只要获取到了锁，那么就意味着
            // 肯定没有人当前在修改这个内存数据了
            // 此时拉取就肯定可以获取到当前完整的内存缓冲里的数据
            return doubleBuffer.getBufferedEditsLog();
        }
    }
}
