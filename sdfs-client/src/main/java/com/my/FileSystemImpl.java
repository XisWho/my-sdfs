package com.my;

import com.my.rpc.grpc.namenode.*;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

public class FileSystemImpl implements FileSystem {

    private static final String NAMENODE_HOSTNAME = "localhost";
    private static final Integer NAMENODE_PORT = 50070;

    private NameNodeServiceGrpc.NameNodeServiceBlockingStub namenode;

    public FileSystemImpl() {
        ManagedChannel channel = NettyChannelBuilder
                .forAddress(NAMENODE_HOSTNAME, NAMENODE_PORT)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        this.namenode = NameNodeServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void mkdir(String path) {
        try {
            MkdirRequest request = MkdirRequest.newBuilder()
                    .setPath(path)
                    .build();
            MkdirResponse response = namenode.mkdir(request);
            System.out.println("接收到NameNode返回的创建响应：" + response.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        try {
            ShutdownRequest request = ShutdownRequest.newBuilder()
                    .setCode("1")
                    .build();
            ShutdownResponse response = namenode.shutdown(request);
            System.out.println("接收到NameNode返回的关闭响应：" + response.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FileSystem fileSystem = new FileSystemImpl();

        /*fileSystem.mkdir("/usr");
        fileSystem.mkdir("/usr/warehouse/hive");
        fileSystem.mkdir("/usr/warehouse/spark");
        fileSystem.mkdir("/bin");*/

        /*for (int i = 0; i < 20; i++) {
            fileSystem.mkdir("/usr/warehouse/hive"+i);
        }*/

        /*for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 50; j++) {
                    fileSystem.mkdir("/usr/warehouse/hive-"+ Thread.currentThread().getName()+"-"+j);
                }
            }).start();
        }

        Thread.sleep(100000);*/
        fileSystem.shutdown();
    }

}