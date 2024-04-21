package com.my;

import com.my.rpc.grpc.namenode.*;
import io.grpc.stub.StreamObserver;

public class NameNodeServiceImpl implements NameNodeServiceGrpc.NameNodeService {

    public static final Integer STATUS_SUCCESS = 1;
    public static final Integer STATUS_FAILURE = 2;
    public static final Integer STATUS_SHUTDOWN = 3;

    private DataNodeManager datanodeManager;
    private FSNameSystem fsNameSystem;

    private volatile Boolean isRunning = true;

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
        MkdirResponse response;
        if (!isRunning) {
            response = MkdirResponse.newBuilder()
                    .setStatus(STATUS_SHUTDOWN)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        } else {
            fsNameSystem.mkdir(request.getPath());

            response = MkdirResponse.newBuilder()
                    .setStatus(STATUS_SUCCESS)
                    .build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void shutdown(ShutdownRequest request, StreamObserver<ShutdownResponse> responseObserver) {
        isRunning = false;
        fsNameSystem.shutdown();

        ShutdownResponse response = ShutdownResponse.newBuilder()
                .setStatus(STATUS_SUCCESS)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
