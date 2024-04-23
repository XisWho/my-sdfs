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
                    .setCode(1)
                    .build();
            ShutdownResponse response = namenode.shutdown(request);
            System.out.println("接收到NameNode返回的关闭响应：" + response.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean upload(byte[] file, String filename) {
        // 必须先用filename发送一个RPC接口调用到master节点
        // 去尝试在文件目录树里创建一个文件
        // 此时还需要进行查重，如果这个文件已经存在，就不让你上传了
        if(!createFile(filename)) {
            return false;
        }

        // 就是找master节点去要多个数据节点的地址
        // 就是你要考虑自己上传几个副本，找对应副本数量的数据节点的地址
        // 尽可能在分配数据节点的时候，保证每个数据节点放的数据量是比较均衡的
        // 保证集群里各个机器上放的数据比较均衡
        String dataNodes = allocateDataNodes(1000);

        // 依次把文件的副本上传到各个数据节点上去
        // 还要考虑到，如果上传的过程中，某个数据节点他上传失败
        // 此时你需要有一个容错机制的考量

        return true;
    }

    private boolean createFile(String filename) {
        CreateFileRequest request = CreateFileRequest.newBuilder()
                .setFilename(filename)
                .build();
        CreateFileResponse response = namenode.createFile(request);

        if(response.getStatus() == 1) {
            return true;
        }
        return false;
    }

    private String allocateDataNodes(long fileSize) {
        AllocateDataNodesRequest request = AllocateDataNodesRequest.newBuilder()
                .setFileSize(fileSize)
                .build();
        AllocateDataNodesResponse response = namenode.allocateDataNodes(request);
        System.out.println("分配的datanodes=" + response.getDatanodes());
        return response.getDatanodes();
    }

    public static void main(String[] args) throws InterruptedException {
        FileSystem fileSystem = new FileSystemImpl();

        /*for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 50; j++) {
                    fileSystem.mkdir("/usr/warehouse/flink-"+ Thread.currentThread().getName()+"-"+j);
                }
            }).start();
        }*/

        fileSystem.upload(null, "/usr/warehouse/iphone001.png");

        Thread.sleep(100000);
         // fileSystem.shutdown();
    }

}