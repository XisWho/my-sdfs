package com.my;

import com.alibaba.fastjson.JSONArray;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

public class NameNodeRpcClient {

    private static final String NAMENODE_HOSTNAME = "localhost";
    private static final Integer NAMENODE_PORT = 50070;

    private NameNodeServiceGrpc.NameNodeServiceBlockingStub namenode;

    private Boolean isNamenodeRunning = true;

    public NameNodeRpcClient() {
        ManagedChannel channel = NettyChannelBuilder
                .forAddress(NAMENODE_HOSTNAME, NAMENODE_PORT)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        this.namenode = NameNodeServiceGrpc.newBlockingStub(channel);
    }

    public JSONArray fetchEditsLog(long syncedTxid) {
        FetchEditsLogRequest request = FetchEditsLogRequest.newBuilder()
                .setSyncedTxid(syncedTxid)
                .build();

        FetchEditsLogResponse response = namenode.fetchEditsLog(request);
        String editsLogJson = response.getEditsLog();

        return JSONArray.parseArray(editsLogJson);
    }

    /**
     * 更新checkpoint txid
     * @param txid
     */
    public void updateCheckpointTxid(long txid) {
        UpdateCheckpointTxidRequest request = UpdateCheckpointTxidRequest.newBuilder()
                .setTxid(txid)
                .build();
        namenode.updateCheckpointTxid(request);
    }

    public Boolean isNamenodeRunning() {
        return isNamenodeRunning;
    }
    public void setIsNamenodeRunning(Boolean isNamenodeRunning) {
        this.isNamenodeRunning = isNamenodeRunning;
    }

}
