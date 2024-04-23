package com.my;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataNodeInfo implements Comparable<DataNodeInfo> {

    private String ip;

    private String hostname;

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

}
