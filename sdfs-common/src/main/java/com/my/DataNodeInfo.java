package com.my;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
@Setter
public class DataNodeInfo implements Comparable<DataNodeInfo> {

    private String ip;

    private String hostname;

    /**
     * NIO端口
     */
    private int nioPort;

    private long latestHeartbeatTime;

    /**
     * 已经存储数据的大小
     */
    private long storedDataSize;

    public DataNodeInfo() {
        storedDataSize = 0L;
    }

    public void addStoredDataSize(long storedDataSize) {
        this.storedDataSize += storedDataSize;
    }

    @Override
    public int compareTo(DataNodeInfo o) {
        if(this.storedDataSize - o.getStoredDataSize() > 0) {
            return 1;
        } else if(this.storedDataSize - o.getStoredDataSize() < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "DataNodeInfo{" +
                "ip='" + ip + '\'' +
                ", hostname='" + hostname + '\'' +
                ", nioPort=" + nioPort +
                ", latestHeartbeatTime=" + latestHeartbeatTime +
                ", storedDataSize=" + storedDataSize +
                '}';
    }

    public String getId() {
        return ip + "_" + hostname;
    }

    /**
     * 副本复制任务队列
     */
    private ConcurrentLinkedQueue<ReplicateTask> replicateTaskQueue =
            new ConcurrentLinkedQueue<ReplicateTask>();

    public void addReplicateTask(ReplicateTask replicateTask) {
        replicateTaskQueue.offer(replicateTask);
    }

    public ReplicateTask pollReplicateTask() {
        if(!replicateTaskQueue.isEmpty()) {
            return replicateTaskQueue.poll();
        }
        return null;
    }

    /**
     * 副本复制任务
     *
     */
    public static class ReplicateTask {

        private String filename;
        private Long fileLength;
        private DataNodeInfo sourceDatanode;
        private DataNodeInfo destDatanode;

        public ReplicateTask(String filename, Long fileLength, DataNodeInfo sourceDatanode, DataNodeInfo destDatanode) {
            this.filename = filename;
            this.fileLength = fileLength;
            this.sourceDatanode = sourceDatanode;
            this.destDatanode = destDatanode;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public Long getFileLength() {
            return fileLength;
        }

        public void setFileLength(Long fileLength) {
            this.fileLength = fileLength;
        }

        public DataNodeInfo getSourceDatanode() {
            return sourceDatanode;
        }

        public void setSourceDatanode(DataNodeInfo sourceDatanode) {
            this.sourceDatanode = sourceDatanode;
        }

        public DataNodeInfo getDestDatanode() {
            return destDatanode;
        }

        public void setDestDatanode(DataNodeInfo destDatanode) {
            this.destDatanode = destDatanode;
        }

        @Override
        public String toString() {
            return "ReplicateTask [filename=" + filename + ", fileLength=" + fileLength + ", sourceDatanode="
                    + sourceDatanode + ", destDatanode=" + destDatanode + "]";
        }

    }

    /**
     * 删除副本任务
     */
    private ConcurrentLinkedQueue<RemoveReplicaTask> removeReplicaTaskQueue =
            new ConcurrentLinkedQueue<RemoveReplicaTask>();

    public void addRemoveReplicaTask(RemoveReplicaTask removeReplicaTask) {
        removeReplicaTaskQueue.offer(removeReplicaTask);
    }

    public RemoveReplicaTask pollRemoveReplicaTask() {
        if(!removeReplicaTaskQueue.isEmpty()) {
            return removeReplicaTaskQueue.poll();
        }
        return null;
    }

    /**
     * 删除副本任务
     *
     */
    public static class RemoveReplicaTask {

        private String filename;
        private DataNodeInfo datanode;

        public RemoveReplicaTask(String filename, DataNodeInfo datanode) {
            this.filename = filename;
            this.datanode = datanode;
        }

        public String getFilename() {
            return filename;
        }
        public void setFilename(String filename) {
            this.filename = filename;
        }
        public DataNodeInfo getDatanode() {
            return datanode;
        }
        public void setDatanode(DataNodeInfo datanode) {
            this.datanode = datanode;
        }

    }

}
