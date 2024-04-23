package com.my;

import com.my.rpc.grpc.namenode.*;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

import static com.my.DataNodeConfig.*;

public class Transport implements LifeCycle {

    private NameNodeServiceGrpc.NameNodeServiceBlockingStub namenode;

    @Override
    public void init() {
        ManagedChannel channel = NettyChannelBuilder
                .forAddress(NAMENODE_HOSTNAME, NAMENODE_PORT)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        this.namenode = NameNodeServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void start() {
    }

    public boolean register() {
        // 发送rpc接口调用请求到NameNode去进行注册
        System.out.println("发送RPC请求到NameNode进行注册.......");

        // 在这里进行注册的时候会提供哪些信息过去呢？
        // 比如说当前这台机器的ip地址、hostname，这两个东西假设是写在配置文件里的
        // 我们写代码的时候，主要是在本地来运行和测试，有一些ip和hostname，就直接在代码里写死了
        // 大家后面自己可以留空做一些完善，你可以加一些配置文件读取的代码

        // 通过RPC接口发送到NameNode他的注册接口上去

        RegisterRequest request = RegisterRequest.newBuilder()
                .setIp(DATANODE_IP)
                .setHostname(DATANODE_HOSTNAME)
                .setNioPort(NIO_PORT)
                .build();
        RegisterResponse response = namenode.register(request);
        System.out.println("接收到NameNode返回的注册响应：" + response.getStatus());

        if(response.getStatus() == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 开启发送心跳的线程
     */
    public void startHeartbeat() {
        new HeartbeatThread().start();
    }

    /**
     * 负责心跳的线程
     * @author zhonghuashishan
     *
     */
    class HeartbeatThread extends Thread {

        @Override
        public void run() {
            try {
                while(true) {
                    System.out.println("发送RPC请求到NameNode进行心跳.......");
                    // 通过RPC接口发送到NameNode他的注册接口上去

                    HeartbeatRequest request = HeartbeatRequest.newBuilder()
                            .setIp(DATANODE_IP)
                            .setHostname(DATANODE_HOSTNAME)
                            .setNioPort(NIO_PORT)
                            .build();
                    HeartbeatResponse response = namenode.heartbeat(request);
                    System.out.println("接收到NameNode返回的心跳响应：" + response.getStatus());

                    Thread.sleep(30 * 1000); // 每隔30秒发送一次心跳到NameNode上去
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 通知Master节点自己收到了一个文件的副本
     * @param filename
     * @throws Exception
     */
    public void informReplicaReceived(String filename) throws Exception {
        InformReplicaReceivedRequest request = InformReplicaReceivedRequest.newBuilder()
                .setHostname(DATANODE_HOSTNAME)
                .setIp(DATANODE_IP)
                .setFilename(filename)
                .build();
        namenode.informReplicaReceived(request);
    }

}
