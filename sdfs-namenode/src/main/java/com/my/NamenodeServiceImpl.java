package com.my;

import com.my.rpc.grpc.namenode.*;
import io.grpc.stub.StreamObserver;

public class NamenodeServiceImpl implements NamenodeServiceGrpc.NamenodeService {

    public static final Integer STATUS_SUCCESS = 1;
    public static final Integer STATUS_FAILURE = 2;

    private DatanodeManager datanodeManager;

    public NamenodeServiceImpl(DatanodeManager datanodeManager) {
        this.datanodeManager = datanodeManager;
    }

    @Override
    public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
        DatanodeInfo datanodeInfo = new DatanodeInfo();
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
        DatanodeInfo datanodeInfo = new DatanodeInfo();
        datanodeInfo.setIp(request.getIp());
        datanodeInfo.setHostname(request.getHostname());
        datanodeManager.heartbeat(datanodeInfo);

        HeartbeatResponse response = HeartbeatResponse.newBuilder()
                .setStatus(STATUS_SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
