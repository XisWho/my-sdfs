package com.my;

import com.alibaba.fastjson.JSONArray;
import com.my.rpc.grpc.namenode.FetchEditsLogRequest;
import com.my.rpc.grpc.namenode.FetchEditsLogResponse;
import com.my.rpc.grpc.namenode.NameNodeServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

public class NameNodeRpcClient {

    private static final String NAMENODE_HOSTNAME = "localhost";
    private static final Integer NAMENODE_PORT = 50070;

    private NameNodeServiceGrpc.NameNodeServiceBlockingStub namenode;

    public NameNodeRpcClient() {
        ManagedChannel channel = NettyChannelBuilder
                .forAddress(NAMENODE_HOSTNAME, NAMENODE_PORT)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        this.namenode = NameNodeServiceGrpc.newBlockingStub(channel);
    }

    public JSONArray fetchEditsLog() {
        FetchEditsLogRequest request = FetchEditsLogRequest.newBuilder()
                .setCode(1)
                .build();

        FetchEditsLogResponse response = namenode.fetchEditsLog(request);
        String editsLogJson = response.getEditsLog();

        return JSONArray.parseArray(editsLogJson);
    }

}
