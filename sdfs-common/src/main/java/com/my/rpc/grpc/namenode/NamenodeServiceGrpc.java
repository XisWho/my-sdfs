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
public class NamenodeServiceGrpc {

  private NamenodeServiceGrpc() {}

  public static final String SERVICE_NAME = "com.my.rpc.grpc.namenode.NamenodeService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.RegisterRequest,
      com.my.rpc.grpc.namenode.RegisterResponse> METHOD_REGISTER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NamenodeService", "register"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.RegisterRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.RegisterResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.my.rpc.grpc.namenode.HeartbeatRequest,
      com.my.rpc.grpc.namenode.HeartbeatResponse> METHOD_HEARTBEAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.my.rpc.grpc.namenode.NamenodeService", "heartbeat"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.HeartbeatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.my.rpc.grpc.namenode.HeartbeatResponse.getDefaultInstance()));

  public static NamenodeServiceStub newStub(io.grpc.Channel channel) {
    return new NamenodeServiceStub(channel);
  }

  public static NamenodeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NamenodeServiceBlockingStub(channel);
  }

  public static NamenodeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NamenodeServiceFutureStub(channel);
  }

  public static interface NamenodeService {

    public void register(com.my.rpc.grpc.namenode.RegisterRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.RegisterResponse> responseObserver);

    public void heartbeat(com.my.rpc.grpc.namenode.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.my.rpc.grpc.namenode.HeartbeatResponse> responseObserver);
  }

  public static interface NamenodeServiceBlockingClient {

    public com.my.rpc.grpc.namenode.RegisterResponse register(com.my.rpc.grpc.namenode.RegisterRequest request);

    public com.my.rpc.grpc.namenode.HeartbeatResponse heartbeat(com.my.rpc.grpc.namenode.HeartbeatRequest request);
  }

  public static interface NamenodeServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.RegisterResponse> register(
        com.my.rpc.grpc.namenode.RegisterRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.my.rpc.grpc.namenode.HeartbeatResponse> heartbeat(
        com.my.rpc.grpc.namenode.HeartbeatRequest request);
  }

  public static class NamenodeServiceStub extends io.grpc.stub.AbstractStub<NamenodeServiceStub>
      implements NamenodeService {
    private NamenodeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NamenodeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NamenodeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NamenodeServiceStub(channel, callOptions);
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
  }

  public static class NamenodeServiceBlockingStub extends io.grpc.stub.AbstractStub<NamenodeServiceBlockingStub>
      implements NamenodeServiceBlockingClient {
    private NamenodeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NamenodeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NamenodeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NamenodeServiceBlockingStub(channel, callOptions);
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
  }

  public static class NamenodeServiceFutureStub extends io.grpc.stub.AbstractStub<NamenodeServiceFutureStub>
      implements NamenodeServiceFutureClient {
    private NamenodeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NamenodeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NamenodeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NamenodeServiceFutureStub(channel, callOptions);
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
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_HEARTBEAT = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NamenodeService serviceImpl;
    private final int methodId;

    public MethodHandlers(NamenodeService serviceImpl, int methodId) {
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
      final NamenodeService serviceImpl) {
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
        .build();
  }
}
