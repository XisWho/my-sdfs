// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/my/rpc/grpc/proto/NameNodeRpc.proto

package com.my.rpc.grpc.namenode;

public interface ReallocateDataNodeRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.my.rpc.grpc.namenode.ReallocateDataNodeRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int64 fileSize = 1;</code>
   */
  long getFileSize();

  /**
   * <code>optional string excludedDataNodeId = 2;</code>
   */
  java.lang.String getExcludedDataNodeId();
  /**
   * <code>optional string excludedDataNodeId = 2;</code>
   */
  com.google.protobuf.ByteString
      getExcludedDataNodeIdBytes();
}
