package com.my;

import com.my.rpc.grpc.namenode.NamenodeServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class NamenodeRpcServer implements LifeCycle {

    private static final int DEFAULT_PORT = 50070;

    private Namenode namenode;

    private DatanodeManager datanodeManager;

    private Server server = null;

    public NamenodeRpcServer(DatanodeManager datanodeManager) {
        this.datanodeManager = datanodeManager;
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
                    .addService(NamenodeServiceGrpc.bindService(new NamenodeServiceImpl(datanodeManager)))
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
                NamenodeRpcServer.this.stop();
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
