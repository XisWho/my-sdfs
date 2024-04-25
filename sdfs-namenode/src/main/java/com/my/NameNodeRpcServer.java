package com.my;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class NameNodeRpcServer implements LifeCycle {

    private static final int DEFAULT_PORT = 50070;

    private NameNode namenode;

    private DataNodeManager datanodeManager;

    private FSNameSystem fsNameSystem;

    private Server server = null;

    public NameNodeRpcServer(DataNodeManager datanodeManager, FSNameSystem fsNameSystem) {
        this.datanodeManager = datanodeManager;
        this.fsNameSystem = fsNameSystem;
    }

    @Override
    public void init() {

    }

    @Override
    public void start() {
        // 启动一个rpc server，监听指定的端口号
        // 同时绑定好了自己开发的接口
        try {
            server = ServerBuilder
                    .forPort(DEFAULT_PORT)
                    .addService(NameNodeServiceGrpc.bindService(new NameNodeServiceImpl(datanodeManager, fsNameSystem)))
                    .build()
                    .start();
        } catch (Exception e) {
            System.out.println("NamenodeRpcServer启动失败");
            e.printStackTrace();
        }

        System.out.println("NamenodeRpcServer启动，监听端口号：" + DEFAULT_PORT);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                NameNodeRpcServer.this.stop();
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
