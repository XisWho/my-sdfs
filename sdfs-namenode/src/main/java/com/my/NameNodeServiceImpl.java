package com.my;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.my.rpc.grpc.namenode.*;
import io.grpc.stub.StreamObserver;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NameNodeServiceImpl implements NameNodeServiceGrpc.NameNodeService {

    public static final Integer STATUS_SUCCESS = 1;
    public static final Integer STATUS_FAILURE = 2;
    public static final Integer STATUS_SHUTDOWN = 3;

    public static final Integer BACKUP_NODE_FETCH_SIZE = 10;

    private DataNodeManager datanodeManager;
    private FSNameSystem fsNameSystem;

    private volatile Boolean isRunning = true;

    /**
     * 当前缓存里的editslog最大的一个txid
     */
    private long currentBufferedMaxTxid = 0L;
    /**
     * 当前内存里缓冲了哪个磁盘文件的数据
     */
    private String bufferedFlushedTxid;
    /**
     * 当前缓冲的一小部分editslog
     */
    private JSONArray currentBufferedEditsLog = new JSONArray();

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
        fsNameSystem.saveCheckpointTxid();

        ShutdownResponse response = ShutdownResponse.newBuilder()
                .setStatus(STATUS_SUCCESS)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void fetchEditsLog(FetchEditsLogRequest request, StreamObserver<FetchEditsLogResponse> responseObserver) {
        if(!isRunning) {
            FetchEditsLogResponse response = FetchEditsLogResponse.newBuilder()
                    .setEditsLog(new JSONArray().toJSONString())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        long syncedTxid = request.getSyncedTxid();

        JSONArray fetchedEditsLog = new JSONArray();
        List<String> flushedTxids = fsNameSystem.getFsEditlog().getFlushedTxids();  // 这里不加锁，使用线程安全的集合类

        // 如果此时还没有刷出来任何磁盘文件的话，那么此时数据仅仅存在于内存缓冲里
        if(flushedTxids.size() == 0) {
            System.out.println("暂时没有任何磁盘文件，直接从内存缓冲中拉取editslog......");
            fetchedEditsLog = fetchFromBufferedEditsLog(syncedTxid);
        }
        // 如果此时发现已经有落地磁盘的文件了，这个时候就要扫描所有的磁盘文件的索引范围
        else {
            // 第一种情况，你要拉取的txid是在某个磁盘文件里的
            // 有磁盘文件，而且内存里还缓存了某个磁盘文件的数据了
            if(bufferedFlushedTxid != null) {
                // 如果要拉取的数据就在当前缓存的磁盘文件数据里
                if(existInFlushedFile(syncedTxid, bufferedFlushedTxid)) {
                    System.out.println("上一次已经缓存过磁盘文件的数据，直接从磁盘文件缓存中拉取editslog......");
                    fetchedEditsLog = fetchFromCurrentBuffer(syncedTxid);
                }
                // 如果要拉取的数据不在当前缓存的磁盘文件数据里了，那么需要从下一个磁盘文件去拉取
                else {
                    String nextFlushedTxid = getNextFlushedTxid(flushedTxids, bufferedFlushedTxid);
                    // 如果可以找到下一个磁盘文件，那么就从下一个磁盘文件里开始读取数据
                    if(nextFlushedTxid != null) {
                        System.out.println("上一次缓存的磁盘文件找不到要拉取的数据，从下一个磁盘文件中拉取editslog......");
                        fetchedEditsLog = fetchFromFlushedFile(syncedTxid, nextFlushedTxid);
                    }
                    // 如果没有找到下一个文件，此时就需要从内存里去继续读取
                    else {
                        System.out.println("上一次缓存的磁盘文件找不到要拉取的数据，而且没有下一个磁盘文件，尝试从内存缓冲中拉取editslog......");
                        fetchedEditsLog = fetchFromBufferedEditsLog(syncedTxid);
                    }
                }
            }
            // 第一次尝试从磁盘文件里去拉取
            else {
                // 遍历所有的磁盘文件的索引范围，0-390，391-782
                Boolean fechedFromFlushedFile = false;

                for(String flushedTxid : flushedTxids) {
                    // 如果要拉取的下一条数据就是在某个磁盘文件里
                    if(existInFlushedFile(syncedTxid, flushedTxid)) {
                        System.out.println("尝试从磁盘文件中拉取editslog，flushedTxid=" + flushedTxid);
                        // 此时可以把这个磁盘文件里以及下一个磁盘文件的的数据都读取出来，放到内存里来缓存
                        // 就怕一个磁盘文件的数据不足够10条
                        fetchedEditsLog = fetchFromFlushedFile(syncedTxid, flushedTxid);
                        fechedFromFlushedFile = true;
                        break;
                    }
                }

                // 第二种情况，你要拉取的txid已经比磁盘文件里的全部都新了，还在内存缓冲里
                // 如果没有找到下一个文件，此时就需要从内存里去继续读取
                if(!fechedFromFlushedFile) {
                    System.out.println("所有磁盘文件都没找到要拉取的editslog，尝试直接从内存缓冲中拉取editslog......");
                    fetchedEditsLog = fetchFromBufferedEditsLog(syncedTxid);
                }
            }
        }

        FetchEditsLogResponse response = FetchEditsLogResponse.newBuilder()
                .setEditsLog(fetchedEditsLog.toJSONString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private JSONArray fetchFromFlushedFile(long syncedTxid, String flushedTxid) {
        try {
            String[] flushedTxidSplited = flushedTxid.split("_");
            long startTxid = Long.valueOf(flushedTxidSplited[0]);
            long endTxid = Long.valueOf(flushedTxidSplited[1]);

            String currentEditsLogFile = "E:\\code\\java-code\\my-project\\my-sdfs\\editslog\\edits-"
                    + startTxid + "-" + endTxid + ".log";
            List<String> editsLogs = Files.readAllLines(Paths.get(currentEditsLogFile),
                    StandardCharsets.UTF_8);

            currentBufferedEditsLog.clear();
            for(String editsLog : editsLogs) {
                currentBufferedEditsLog.add(JSONObject.parseObject(editsLog));
                currentBufferedMaxTxid = JSONObject.parseObject(editsLog).getLongValue("txid");
            }
            bufferedFlushedTxid = flushedTxid; // 缓存了某个刷入磁盘文件的数据

            return fetchFromCurrentBuffer(syncedTxid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    private String getNextFlushedTxid(List<String> flushedTxids, String bufferedFlushedTxid) {
        for(int i = 0; i < flushedTxids.size(); i++) {
            if(flushedTxids.get(i).equals(bufferedFlushedTxid)) {
                if(i + 1 < flushedTxids.size()) {
                    return flushedTxids.get(i + 1);
                }
            }
        }
        return null;
    }

    private boolean existInFlushedFile(long syncedTxid, String flushedTxid) {
        String[] flushedTxidSplited = flushedTxid.split("_");

        long startTxid = Long.valueOf(flushedTxidSplited[0]);
        long endTxid = Long.valueOf(flushedTxidSplited[1]);
        long fetchTxid = syncedTxid + 1;

        if(fetchTxid >= startTxid && fetchTxid <= endTxid) {
            return true;
        }

        return false;
    }

    /**
     * 从内存缓冲的editslog中拉取数据
     */
    private JSONArray fetchFromBufferedEditsLog(long syncedTxid) {
        // 如果要拉取的txid还在上一次内存缓存中，此时继续从内存缓冲中来拉取即可
        long fetchTxid = syncedTxid + 1;
        if(fetchTxid <= currentBufferedMaxTxid) {
            System.out.println("尝试从内存缓冲拉取的时候，发现上一次内存缓存有数据可供拉取......");
            return fetchFromCurrentBuffer(syncedTxid);
        }

        currentBufferedEditsLog.clear();

        // 从内存里来重新拉取一次缓冲
        String[] bufferedEditsLog = fsNameSystem.getFsEditlog().getBufferedEditsLog(); // 在这里加锁
        if(bufferedEditsLog != null) {
            for(String editsLog : bufferedEditsLog) {
                JSONObject jsonObject = JSONObject.parseObject(editsLog);
                currentBufferedEditsLog.add(jsonObject);
                // 我们在这里记录一下，当前内存缓存中的数据最大的一个txid是多少，这样下次再拉取可以
                // 判断，内存缓存里的数据是否还可以读取，不要每次都重新从内存缓冲中去加载
                currentBufferedMaxTxid = jsonObject.getLongValue("txid");
            }
            bufferedFlushedTxid = null;

            return fetchFromCurrentBuffer(syncedTxid);
        }
        return new JSONArray();
    }

    /**
     * 从当前已经在内存里缓存的数据中拉取editslog
     */
    private JSONArray fetchFromCurrentBuffer(long syncedTxid) {
        int fetchCount = 0;
        JSONArray fetchedEditsLog = new JSONArray();
        long fetchTxid = syncedTxid + 1;

        for(int i = 0; i < currentBufferedEditsLog.size(); i++) {
            if(currentBufferedEditsLog.getJSONObject(i).getLong("txid") == fetchTxid) {
                fetchedEditsLog.add(currentBufferedEditsLog.getJSONObject(i));
                fetchTxid = currentBufferedEditsLog.getJSONObject(i).getLongValue("txid") + 1;
                fetchCount++;
            }
            if(fetchCount == BACKUP_NODE_FETCH_SIZE) {
                break;
            }
        }

        return fetchedEditsLog;
    }

    @Override
    public void updateCheckpointTxid(UpdateCheckpointTxidRequest request, StreamObserver<UpdateCheckpointTxidResponse> responseObserver) {
        long txid = request.getTxid();
        fsNameSystem.setCheckpointTxid(txid);

        UpdateCheckpointTxidResponse response = UpdateCheckpointTxidResponse.newBuilder()
                .setStatus(1)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
