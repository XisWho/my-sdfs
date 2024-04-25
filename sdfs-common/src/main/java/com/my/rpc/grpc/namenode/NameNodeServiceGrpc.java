package com.my.rpc.grpc.namenode;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class NameNodeServiceGrpc {

  private NameNodeServiceGrpc() {}

  public static final String SERVICE_NAME = "com.my.rpc.grpc.namenode.NameNodeService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.RegisterRequest,
      com.my.rpc.grpc.namenode.RegisterResponse> METHOD_REGISTER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "register"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.RegisterRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.RegisterResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.HeartbeatRequest,
      com.my.rpc.grpc.namenode.HeartbeatResponse> METHOD_HEARTBEAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "heartbeat"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.HeartbeatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.HeartbeatResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.MkdirRequest,
      com.my.rpc.grpc.namenode.MkdirResponse> METHOD_MKDIR =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "mkdir"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.MkdirRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.MkdirResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.ShutdownRequest,
      com.my.rpc.grpc.namenode.ShutdownResponse> METHOD_SHUTDOWN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "shutdown"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.ShutdownRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.ShutdownResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.FetchEditsLogRequest,
      com.my.rpc.grpc.namenode.FetchEditsLogResponse> METHOD_FETCH_EDITS_LOG =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "fetchEditsLog"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.FetchEditsLogRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.FetchEditsLogResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest,
      com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse> METHOD_UPDATE_CHECKPOINT_TXID =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "updateCheckpointTxid"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.CreateFileRequest,
      com.my.rpc.grpc.namenode.CreateFileResponse> METHOD_CREATE_FILE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "createFile"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.CreateFileRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.CreateFileResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.AllocateDataNodesRequest,
      com.my.rpc.grpc.namenode.AllocateDataNodesResponse> METHOD_ALLOCATE_DATA_NODES =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "allocateDataNodes"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.AllocateDataNodesRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.AllocateDataNodesResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.InformReplicaReceivedRequest,
      com.my.rpc.grpc.namenode.InformReplicaReceivedResponse> METHOD_INFORM_REPLICA_RECEIVED =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "informReplicaReceived"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.InformReplicaReceivedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.InformReplicaReceivedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest,
      com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse> METHOD_REPORT_COMPLETE_STORAGE_INFO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "reportCompleteStorageInfo"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.GetDataNodeForFileRequest,
      com.my.rpc.grpc.namenode.GetDataNodeForFileResponse> METHOD_GET_DATA_NODE_FOR_FILE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "getDataNodeForFile"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.GetDataNodeForFileRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.GetDataNodeForFileResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.ReallocateDataNodeRequest,
      com.my.rpc.grpc.namenode.ReallocateDataNodeResponse> METHOD_REALLOCATE_DATA_NODE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NameNodeService", "reallocateDataNode"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.ReallocateDataNodeRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.ReallocateDataNodeResponse.getDefaultInstance()));

  public static NameNodeServiceStub newStub(io.grpc.Channel channel) {
    return new NameNodeServiceStub(channel);
  }

  public static NameNodeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NameNodeServiceBlockingStub(channel);
  }

  public static NameNodeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NameNodeServiceFutureStub(channel);
  }

  public static interface NameNodeService {

    public void register(com.my.rpc.grpc.namenode.RegisterRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.RegisterResponse> responseObserver);

    public void heartbeat(com.my.rpc.grpc.namenode.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.HeartbeatResponse> responseObserver);

    public void mkdir(com.my.rpc.grpc.namenode.MkdirRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.MkdirResponse> responseObserver);

    public void shutdown(com.my.rpc.grpc.namenode.ShutdownRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.ShutdownResponse> responseObserver);

    public void fetchEditsLog(com.my.rpc.grpc.namenode.FetchEditsLogRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.FetchEditsLogResponse> responseObserver);

    public void updateCheckpointTxid(com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse> responseObserver);

    public void createFile(com.my.rpc.grpc.namenode.CreateFileRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.CreateFileResponse> responseObserver);

    public void allocateDataNodes(com.my.rpc.grpc.namenode.AllocateDataNodesRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.AllocateDataNodesResponse> responseObserver);

    public void informReplicaReceived(com.my.rpc.grpc.namenode.InformReplicaReceivedRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.InformReplicaReceivedResponse> responseObserver);

    public void reportCompleteStorageInfo(com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse> responseObserver);

    public void getDataNodeForFile(com.my.rpc.grpc.namenode.GetDataNodeForFileRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.GetDataNodeForFileResponse> responseObserver);

    public void reallocateDataNode(com.my.rpc.grpc.namenode.ReallocateDataNodeRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.ReallocateDataNodeResponse> responseObserver);
  }

  public static interface NameNodeServiceBlockingClient {

    public com.my.rpc.grpc.namenode.RegisterResponse register(com.my.rpc.grpc.namenode.RegisterRequest request);

    public com.my.rpc.grpc.namenode.HeartbeatResponse heartbeat(com.my.rpc.grpc.namenode.HeartbeatRequest request);

    public com.my.rpc.grpc.namenode.MkdirResponse mkdir(com.my.rpc.grpc.namenode.MkdirRequest request);

    public com.my.rpc.grpc.namenode.ShutdownResponse shutdown(com.my.rpc.grpc.namenode.ShutdownRequest request);

    public com.my.rpc.grpc.namenode.FetchEditsLogResponse fetchEditsLog(com.my.rpc.grpc.namenode.FetchEditsLogRequest request);

    public com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse updateCheckpointTxid(com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest request);

    public com.my.rpc.grpc.namenode.CreateFileResponse createFile(com.my.rpc.grpc.namenode.CreateFileRequest request);

    public com.my.rpc.grpc.namenode.AllocateDataNodesResponse allocateDataNodes(com.my.rpc.grpc.namenode.AllocateDataNodesRequest request);

    public com.my.rpc.grpc.namenode.InformReplicaReceivedResponse informReplicaReceived(com.my.rpc.grpc.namenode.InformReplicaReceivedRequest request);

    public com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse reportCompleteStorageInfo(com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest request);

    public com.my.rpc.grpc.namenode.GetDataNodeForFileResponse getDataNodeForFile(com.my.rpc.grpc.namenode.GetDataNodeForFileRequest request);

    public com.my.rpc.grpc.namenode.ReallocateDataNodeResponse reallocateDataNode(com.my.rpc.grpc.namenode.ReallocateDataNodeRequest request);
  }

  public static interface NameNodeServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.RegisterResponse> register(
        com.my.rpc.grpc.namenode.RegisterRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.HeartbeatResponse> heartbeat(
        com.my.rpc.grpc.namenode.HeartbeatRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.MkdirResponse> mkdir(
        com.my.rpc.grpc.namenode.MkdirRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.ShutdownResponse> shutdown(
        com.my.rpc.grpc.namenode.ShutdownRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.FetchEditsLogResponse> fetchEditsLog(
        com.my.rpc.grpc.namenode.FetchEditsLogRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse> updateCheckpointTxid(
        com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.CreateFileResponse> createFile(
        com.my.rpc.grpc.namenode.CreateFileRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.AllocateDataNodesResponse> allocateDataNodes(
        com.my.rpc.grpc.namenode.AllocateDataNodesRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.InformReplicaReceivedResponse> informReplicaReceived(
        com.my.rpc.grpc.namenode.InformReplicaReceivedRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse> reportCompleteStorageInfo(
        com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.GetDataNodeForFileResponse> getDataNodeForFile(
        com.my.rpc.grpc.namenode.GetDataNodeForFileRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.ReallocateDataNodeResponse> reallocateDataNode(
        com.my.rpc.grpc.namenode.ReallocateDataNodeRequest request);
  }

  public static class NameNodeServiceStub extends io.grpc.stub.AbstractStub<NameNodeServiceStub>
      implements NameNodeService {
    private NameNodeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NameNodeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NameNodeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NameNodeServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void register(com.my.rpc.grpc.namenode.RegisterRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.RegisterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void heartbeat(com.my.rpc.grpc.namenode.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.HeartbeatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_HEARTBEAT, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void mkdir(com.my.rpc.grpc.namenode.MkdirRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.MkdirResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MKDIR, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void shutdown(com.my.rpc.grpc.namenode.ShutdownRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.ShutdownResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SHUTDOWN, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void fetchEditsLog(com.my.rpc.grpc.namenode.FetchEditsLogRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.FetchEditsLogResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FETCH_EDITS_LOG, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updateCheckpointTxid(com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_CHECKPOINT_TXID, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void createFile(com.my.rpc.grpc.namenode.CreateFileRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.CreateFileResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_FILE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void allocateDataNodes(com.my.rpc.grpc.namenode.AllocateDataNodesRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.AllocateDataNodesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ALLOCATE_DATA_NODES, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void informReplicaReceived(com.my.rpc.grpc.namenode.InformReplicaReceivedRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.InformReplicaReceivedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_INFORM_REPLICA_RECEIVED, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void reportCompleteStorageInfo(com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REPORT_COMPLETE_STORAGE_INFO, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void getDataNodeForFile(com.my.rpc.grpc.namenode.GetDataNodeForFileRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.GetDataNodeForFileResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_DATA_NODE_FOR_FILE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void reallocateDataNode(com.my.rpc.grpc.namenode.ReallocateDataNodeRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.ReallocateDataNodeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REALLOCATE_DATA_NODE, getCallOptions()), request, responseObserver);
    }
  }

  public static class NameNodeServiceBlockingStub extends io.grpc.stub.AbstractStub<NameNodeServiceBlockingStub>
      implements NameNodeServiceBlockingClient {
    private NameNodeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NameNodeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NameNodeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NameNodeServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.RegisterResponse register(com.my.rpc.grpc.namenode.RegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.HeartbeatResponse heartbeat(com.my.rpc.grpc.namenode.HeartbeatRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_HEARTBEAT, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.MkdirResponse mkdir(com.my.rpc.grpc.namenode.MkdirRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MKDIR, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.ShutdownResponse shutdown(com.my.rpc.grpc.namenode.ShutdownRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SHUTDOWN, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.FetchEditsLogResponse fetchEditsLog(com.my.rpc.grpc.namenode.FetchEditsLogRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_FETCH_EDITS_LOG, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse updateCheckpointTxid(com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_CHECKPOINT_TXID, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.CreateFileResponse createFile(com.my.rpc.grpc.namenode.CreateFileRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_FILE, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.AllocateDataNodesResponse allocateDataNodes(com.my.rpc.grpc.namenode.AllocateDataNodesRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ALLOCATE_DATA_NODES, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.InformReplicaReceivedResponse informReplicaReceived(com.my.rpc.grpc.namenode.InformReplicaReceivedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_INFORM_REPLICA_RECEIVED, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse reportCompleteStorageInfo(com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REPORT_COMPLETE_STORAGE_INFO, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.GetDataNodeForFileResponse getDataNodeForFile(com.my.rpc.grpc.namenode.GetDataNodeForFileRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_DATA_NODE_FOR_FILE, getCallOptions(), request);
    }

    @java.lang.Override
    public com.my.rpc.grpc.namenode.ReallocateDataNodeResponse reallocateDataNode(com.my.rpc.grpc.namenode.ReallocateDataNodeRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REALLOCATE_DATA_NODE, getCallOptions(), request);
    }
  }

  public static class NameNodeServiceFutureStub extends io.grpc.stub.AbstractStub<NameNodeServiceFutureStub>
      implements NameNodeServiceFutureClient {
    private NameNodeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NameNodeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NameNodeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NameNodeServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.RegisterResponse> register(
        com.my.rpc.grpc.namenode.RegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.HeartbeatResponse> heartbeat(
        com.my.rpc.grpc.namenode.HeartbeatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_HEARTBEAT, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.MkdirResponse> mkdir(
        com.my.rpc.grpc.namenode.MkdirRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MKDIR, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.ShutdownResponse> shutdown(
        com.my.rpc.grpc.namenode.ShutdownRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SHUTDOWN, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.FetchEditsLogResponse> fetchEditsLog(
        com.my.rpc.grpc.namenode.FetchEditsLogRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FETCH_EDITS_LOG, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse> updateCheckpointTxid(
        com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_CHECKPOINT_TXID, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.CreateFileResponse> createFile(
        com.my.rpc.grpc.namenode.CreateFileRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_FILE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.AllocateDataNodesResponse> allocateDataNodes(
        com.my.rpc.grpc.namenode.AllocateDataNodesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ALLOCATE_DATA_NODES, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.InformReplicaReceivedResponse> informReplicaReceived(
        com.my.rpc.grpc.namenode.InformReplicaReceivedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_INFORM_REPLICA_RECEIVED, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse> reportCompleteStorageInfo(
        com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REPORT_COMPLETE_STORAGE_INFO, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.GetDataNodeForFileResponse> getDataNodeForFile(
        com.my.rpc.grpc.namenode.GetDataNodeForFileRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_DATA_NODE_FOR_FILE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.ReallocateDataNodeResponse> reallocateDataNode(
        com.my.rpc.grpc.namenode.ReallocateDataNodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REALLOCATE_DATA_NODE, getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_HEARTBEAT = 1;
  private static final int METHODID_MKDIR = 2;
  private static final int METHODID_SHUTDOWN = 3;
  private static final int METHODID_FETCH_EDITS_LOG = 4;
  private static final int METHODID_UPDATE_CHECKPOINT_TXID = 5;
  private static final int METHODID_CREATE_FILE = 6;
  private static final int METHODID_ALLOCATE_DATA_NODES = 7;
  private static final int METHODID_INFORM_REPLICA_RECEIVED = 8;
  private static final int METHODID_REPORT_COMPLETE_STORAGE_INFO = 9;
  private static final int METHODID_GET_DATA_NODE_FOR_FILE = 10;
  private static final int METHODID_REALLOCATE_DATA_NODE = 11;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NameNodeService serviceImpl;
    private final int methodId;

    public MethodHandlers(NameNodeService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((com.my.rpc.grpc.namenode.RegisterRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.RegisterResponse>) responseObserver);
          break;
        case METHODID_HEARTBEAT:
          serviceImpl.heartbeat((com.my.rpc.grpc.namenode.HeartbeatRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.HeartbeatResponse>) responseObserver);
          break;
        case METHODID_MKDIR:
          serviceImpl.mkdir((com.my.rpc.grpc.namenode.MkdirRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.MkdirResponse>) responseObserver);
          break;
        case METHODID_SHUTDOWN:
          serviceImpl.shutdown((com.my.rpc.grpc.namenode.ShutdownRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.ShutdownResponse>) responseObserver);
          break;
        case METHODID_FETCH_EDITS_LOG:
          serviceImpl.fetchEditsLog((com.my.rpc.grpc.namenode.FetchEditsLogRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.FetchEditsLogResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CHECKPOINT_TXID:
          serviceImpl.updateCheckpointTxid((com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse>) responseObserver);
          break;
        case METHODID_CREATE_FILE:
          serviceImpl.createFile((com.my.rpc.grpc.namenode.CreateFileRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.CreateFileResponse>) responseObserver);
          break;
        case METHODID_ALLOCATE_DATA_NODES:
          serviceImpl.allocateDataNodes((com.my.rpc.grpc.namenode.AllocateDataNodesRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.AllocateDataNodesResponse>) responseObserver);
          break;
        case METHODID_INFORM_REPLICA_RECEIVED:
          serviceImpl.informReplicaReceived((com.my.rpc.grpc.namenode.InformReplicaReceivedRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.InformReplicaReceivedResponse>) responseObserver);
          break;
        case METHODID_REPORT_COMPLETE_STORAGE_INFO:
          serviceImpl.reportCompleteStorageInfo((com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse>) responseObserver);
          break;
        case METHODID_GET_DATA_NODE_FOR_FILE:
          serviceImpl.getDataNodeForFile((com.my.rpc.grpc.namenode.GetDataNodeForFileRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.GetDataNodeForFileResponse>) responseObserver);
          break;
        case METHODID_REALLOCATE_DATA_NODE:
          serviceImpl.reallocateDataNode((com.my.rpc.grpc.namenode.ReallocateDataNodeRequest) request,
              (io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.ReallocateDataNodeResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final NameNodeService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_REGISTER,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.RegisterRequest,
              com.my.rpc.grpc.namenode.RegisterResponse>(
                serviceImpl, METHODID_REGISTER)))
        .addMethod(
          METHOD_HEARTBEAT,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.HeartbeatRequest,
              com.my.rpc.grpc.namenode.HeartbeatResponse>(
                serviceImpl, METHODID_HEARTBEAT)))
        .addMethod(
          METHOD_MKDIR,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.MkdirRequest,
              com.my.rpc.grpc.namenode.MkdirResponse>(
                serviceImpl, METHODID_MKDIR)))
        .addMethod(
          METHOD_SHUTDOWN,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.ShutdownRequest,
              com.my.rpc.grpc.namenode.ShutdownResponse>(
                serviceImpl, METHODID_SHUTDOWN)))
        .addMethod(
          METHOD_FETCH_EDITS_LOG,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.FetchEditsLogRequest,
              com.my.rpc.grpc.namenode.FetchEditsLogResponse>(
                serviceImpl, METHODID_FETCH_EDITS_LOG)))
        .addMethod(
          METHOD_UPDATE_CHECKPOINT_TXID,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.UpdateCheckpointTxidRequest,
              com.my.rpc.grpc.namenode.UpdateCheckpointTxidResponse>(
                serviceImpl, METHODID_UPDATE_CHECKPOINT_TXID)))
        .addMethod(
          METHOD_CREATE_FILE,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.CreateFileRequest,
              com.my.rpc.grpc.namenode.CreateFileResponse>(
                serviceImpl, METHODID_CREATE_FILE)))
        .addMethod(
          METHOD_ALLOCATE_DATA_NODES,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.AllocateDataNodesRequest,
              com.my.rpc.grpc.namenode.AllocateDataNodesResponse>(
                serviceImpl, METHODID_ALLOCATE_DATA_NODES)))
        .addMethod(
          METHOD_INFORM_REPLICA_RECEIVED,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.InformReplicaReceivedRequest,
              com.my.rpc.grpc.namenode.InformReplicaReceivedResponse>(
                serviceImpl, METHODID_INFORM_REPLICA_RECEIVED)))
        .addMethod(
          METHOD_REPORT_COMPLETE_STORAGE_INFO,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.ReportCompleteStorageInfoRequest,
              com.my.rpc.grpc.namenode.ReportCompleteStorageInfoResponse>(
                serviceImpl, METHODID_REPORT_COMPLETE_STORAGE_INFO)))
        .addMethod(
          METHOD_GET_DATA_NODE_FOR_FILE,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.GetDataNodeForFileRequest,
              com.my.rpc.grpc.namenode.GetDataNodeForFileResponse>(
                serviceImpl, METHODID_GET_DATA_NODE_FOR_FILE)))
        .addMethod(
          METHOD_REALLOCATE_DATA_NODE,
          asyncUnaryCall(
            new MethodHandlers<
              com.my.rpc.grpc.namenode.ReallocateDataNodeRequest,
              com.my.rpc.grpc.namenode.ReallocateDataNodeResponse>(
                serviceImpl, METHODID_REALLOCATE_DATA_NODE)))
        .build();
  }
}
