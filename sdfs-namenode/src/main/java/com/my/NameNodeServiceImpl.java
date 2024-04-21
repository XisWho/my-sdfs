package com.my;

import com.my.rpc.grpc.namenode.*;
import io.grpc.stub.StreamObserver;

public class NameNodeServiceImpl implements NameNodeServiceGrpc.NameNodeService {

    public static final Integer STATUS_SUCCESS = 1;
    public static final Integer STATUS_FAILURE = 2;

    private DataNodeManager datanodeManager;
    private FSNameSystem fsNameSystem;

    public NameNodeServiceImpl(DataNodeManager datanodeManager, FSNameSystem fsNameSystem) {
        this.datanodeManager = datanodeManager;
        this.fsNameSystem = fsNameSystem;
    }

    @Override
    public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
        DataNodeInfo datanodeInfo = new DataNodeInfo();
        datanodeInfo.setIp(request.getIp());
        datanodeInfo.setHostname(request.getHostname());
        datanodeManager.register(datanodeInfo);

        RegisterResponse response = RegisterResponse.newBuilder()
                .setStatus(STATUS_SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void heartbeat(HeartbeatRequest request, StreamObserver<HeartbeatResponse> responseObserver) {
        DataNodeInfo datanodeInfo = new DataNodeInfo();
        datanodeInfo.setIp(request.getIp());
        datanodeInfo.setHostname(request.getHostname());
        datanodeManager.heartbeat(datanodeInfo);

        HeartbeatResponse response = HeartbeatResponse.newBuilder()
                .setStatus(STATUS_SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void mkdir(MkdirRequest request, StreamObserver<MkdirResponse> responseObserver) {
        fsNameSystem.mkdir(request.getPath());

        MkdirResponse response = MkdirResponse.newBuilder()
                .setStatus(STATUS_SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
