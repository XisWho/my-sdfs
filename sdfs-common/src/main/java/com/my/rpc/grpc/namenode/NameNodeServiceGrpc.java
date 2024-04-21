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
  }

  public static interface NameNodeServiceBlockingClient {

    public com.my.rpc.grpc.namenode.RegisterResponse register(com.my.rpc.grpc.namenode.RegisterRequest request);

    public com.my.rpc.grpc.namenode.HeartbeatResponse heartbeat(com.my.rpc.grpc.namenode.HeartbeatRequest request);

    public com.my.rpc.grpc.namenode.MkdirResponse mkdir(com.my.rpc.grpc.namenode.MkdirRequest request);
  }

  public static interface NameNodeServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.RegisterResponse> register(
        com.my.rpc.grpc.namenode.RegisterRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.HeartbeatResponse> heartbeat(
        com.my.rpc.grpc.namenode.HeartbeatRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.MkdirResponse> mkdir(
        com.my.rpc.grpc.namenode.MkdirRequest request);
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
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_HEARTBEAT = 1;
  private static final int METHODID_MKDIR = 2;

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
        .build();
  }
}
