package com.my;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.my.rpc.grpc.namenode.*;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
    public boolean upload(byte[] file, String fileName, long fileSize) throws Exception {
        // 必须先用filename发送一个RPC接口调用到master节点
        // 去尝试在文件目录树里创建一个文件
        // 此时还需要进行查重，如果这个文件已经存在，就不让你上传了
        if(!createFile(fileName)) {
            return false;
        }

        // 就是找master节点去要多个数据节点的地址
        // 就是你要考虑自己上传几个副本，找对应副本数量的数据节点的地址
        // 尽可能在分配数据节点的时候，保证每个数据节点放的数据量是比较均衡的
        // 保证集群里各个机器上放的数据比较均衡
        String datanodesJson = allocateDataNodes(fileSize);

        // 依次把文件的副本上传到各个数据节点上去
        // 还要考虑到，如果上传的过程中，某个数据节点他上传失败
        // 此时你需要有一个容错机制的考量
        JSONArray datanodes = JSONArray.parseArray(datanodesJson);
        for(int i = 0; i < datanodes.size(); i++) {
            JSONObject datanode = datanodes.getJSONObject(i);
            String hostname = datanode.getString("hostname");
            int nioPort = datanode.getIntValue("nioPort");
            String ip = datanode.getString("ip");

            if(!NIOClient.sendFile(hostname, nioPort, file, fileName, fileSize)) {
                datanode = JSONObject.parseObject(
                        reallocateDataNode(fileName, fileSize, ip + "-" + hostname));
                hostname = datanode.getString("hostname");
                nioPort = datanode.getIntValue("nioPort");
                if(!NIOClient.sendFile(hostname, nioPort, file, fileName, fileSize)) {
                    throw new Exception("file upload failed......");
                }
            }
        }

        return true;
    }

    /**
     * 重新分配一个数据节点
     * @param filename
     * @param fileSize
     * @return
     */
    private String reallocateDataNode(String filename, long fileSize,
                                      String excludedDataNodeId) {
        ReallocateDataNodeRequest request = ReallocateDataNodeRequest.newBuilder()
                .setFileSize(fileSize)
                .setExcludedDataNodeId(excludedDataNodeId)
                .build();
        ReallocateDataNodeResponse response = namenode.reallocateDataNode(request);
        return response.getDatanode();
    }

    @Override
    public byte[] download(String filename) throws IOException, InterruptedException {
        // 第一个步骤，一定是调用NameNode的接口，获取这个文件的某个副本所在的DataNode
        JSONObject datanode = getDataNodeForFile(filename, "");
        System.out.println("Master分配用来下载文件的数据节点：" + datanode.toJSONString());

        // 第二个步骤，打开一个针对那个DataNode的网络连接，发送文件名过去
        // 第三个步骤，尝试从连接中读取对方传输过来的文件
        // 第四个步骤，读取到文件之后不需要写入本地的磁盘中，而是转换为一个字节数组返回即可

        String hostname = datanode.getString("hostname");
        Integer nioPort = datanode.getInteger("nioPort");
        String ip = datanode.getString("ip");

        byte[] file = null;

        try {
            file = NIOClient.readFile(hostname, nioPort, filename);
        } catch (Exception e) {
            datanode = getDataNodeForFile(filename, ip + "_" + hostname);
            hostname = datanode.getString("hostname");
            nioPort = datanode.getInteger("nioPort");

            try {
                file = NIOClient.readFile(hostname, nioPort, filename);
            } catch (Exception e2) {
                throw e2;
            }
        }

        return file;
    }

    /**
     * 获取文件的某个副本所在的机器
     * @param filename
     * @return
     * @throws Exception
     */
    private JSONObject getDataNodeForFile(String filename, String excludedDataNodeId) {
        GetDataNodeForFileRequest request = GetDataNodeForFileRequest.newBuilder()
                .setFilename(filename)
                .setExcludedDataNodeId(excludedDataNodeId)
                .build();
        GetDataNodeForFileResponse response = namenode.getDataNodeForFile(request);
        return JSONObject.parseObject(response.getDatanodeInfo());
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

    public static void main(String[] args) throws Exception {
/*        FileSystem fileSystem = new FileSystemImpl();

        *//*for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 50; j++) {
                    fileSystem.mkdir("/usr/warehouse/flink-"+ Thread.currentThread().getName()+"-"+j);
                }
            }).start();
        }*//*

        Thread.sleep(100000);
         // fileSystem.shutdown();*/

        testCreateFile();
        // testReadFile();
    }

    private static void testCreateFile() throws Exception {
        File image = new File("C:\\Users\\Administrator\\Pictures\\Saved Pictures\\test_pic.jpg");
        long imageLength = image.length();

        ByteBuffer buffer = ByteBuffer.allocate((int)imageLength);

        FileInputStream imageIn = new FileInputStream(image);
        FileChannel imageChannel = imageIn.getChannel();
        imageChannel.read(buffer);

        buffer.flip();
        byte[] imageBytes = buffer.array();

        FileSystem fileSystem = new FileSystemImpl();
        fileSystem.upload(imageBytes, "/image/product/iphone.jpg", imageLength);

        imageIn.close();
        imageChannel.close();
    }

    private static void testReadFile() throws Exception {
        FileSystem fileSystem = new FileSystemImpl();
        byte[] image = fileSystem.download("/image/product/iphone.jpg");
        ByteBuffer buffer = ByteBuffer.wrap(image);

        FileOutputStream imageOut = new FileOutputStream("E:\\code\\java-code\\my-project\\my-sdfs\\tmp\\iphone.jpg");
        FileChannel imageChannel = imageOut.getChannel();
        imageChannel.write(buffer);

        imageChannel.close();
        imageOut.close();
    }

}