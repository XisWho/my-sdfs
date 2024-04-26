package com.my;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.my.rpc.grpc.namenode.*;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

import java.io.File;

import static com.my.DataNodeConfig.*;

public class Transport implements LifeCycle {

    public static final Integer SUCCESS = 1;
    public static final Integer FAILURE = 2;
    public static final Integer COMMAND_REGISTER = 1;
    public static final Integer COMMAND_REPORT_COMPLETE_STORAGE_INFO = 2;
    public static final Integer COMMAND_REPLICATE = 3;
    public static final Integer COMMAND_REMOVE_REPLICA = 4;

    private NameNodeServiceGrpc.NameNodeServiceBlockingStub namenode;

    private StorageManager storageManager;

    private ReplicateManager replicateManager;

    public Transport(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    public void setReplicateManager(ReplicateManager replicateManager) {
        this.replicateManager = replicateManager;
    }

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
        System.out.println("DATA_DIR="+DATA_DIR);

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

    public void reportCompleteStorageInfo(StorageInfo storageInfo) {
        if(storageInfo == null) {
            System.out.println("当前没有存储任何文件，不需要全量上报.....");
            return;
        }

        ReportCompleteStorageInfoRequest request = ReportCompleteStorageInfoRequest.newBuilder()
                .setIp(DATANODE_IP)
                .setHostname(DATANODE_HOSTNAME)
                .setFilenames(JSONArray.toJSONString(storageInfo.getFilenames()))
                .setStoredDataSize(storageInfo.getStoredDataSize())
                .build();

        namenode.reportCompleteStorageInfo(request);

        System.out.println("全量上报存储信息：" + storageInfo);
    }

    /**
     * 负责心跳的线程
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
                    // 通过RPC接口发送到NameNode他的注册接口上去
                    HeartbeatResponse response = namenode.heartbeat(request);

                    if(SUCCESS.equals(response.getStatus())) {
                        JSONArray commands = JSONArray.parseArray(response.getCommands());

                        if(commands.size() > 0) {
                            for(int i = 0; i < commands.size(); i++) {
                                JSONObject command = commands.getJSONObject(i);
                                Integer type = command.getInteger("type");
                                JSONObject task = command.getJSONObject("content");

                                if(type.equals(COMMAND_REPLICATE)) {
                                    replicateManager.addReplicateTask(task);
                                    System.out.println("接收副本复制任务，" + command);
                                } else if(type.equals(COMMAND_REMOVE_REPLICA)) {
                                    // 删除副本
                                    String filename = task.getString("filename");
                                    String absoluteFilename = FileUtils.getAbsoluteFilename(filename, DATA_DIR);
                                    File file = new File(absoluteFilename);
                                    if(file.exists()) {
                                        file.delete();
                                    }
                                }
                            }
                        }
                    }

                    // 如果心跳失败了
                    if(response.getStatus() == 2) {
                        JSONArray commands = JSONArray.parseArray(response.getCommands());

                        for(int i = 0; i < commands.size(); i++) {
                            JSONObject command = commands.getJSONObject(i);
                            Integer type = command.getInteger("type");

                            // 如果是注册的命令
                            if(type.equals(1)) {
                                register();
                            }
                            // 如果是全量上报的命令
                            else if(type.equals(2)) {
                                StorageInfo storageInfo = storageManager.getStorageInfo();
                                reportCompleteStorageInfo(storageInfo);
                            }
                        }
                    }

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
