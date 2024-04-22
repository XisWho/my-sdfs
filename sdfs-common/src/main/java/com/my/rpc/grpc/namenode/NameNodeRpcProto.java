// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/my/rpc/grpc/proto/NameNodeRpc.proto

package com.my.rpc.grpc.namenode;

public final class NameNodeRpcProto {
  private NameNodeRpcProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_RegisterRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_RegisterRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_RegisterResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_RegisterResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_HeartbeatRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_HeartbeatRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_HeartbeatResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_HeartbeatResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_MkdirRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_MkdirRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_MkdirResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_MkdirResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_ShutdownRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_ShutdownRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_ShutdownResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_ShutdownResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_FetchEditsLogRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_FetchEditsLogRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_FetchEditsLogResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_FetchEditsLogResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\'com/my/rpc/grpc/proto/NameNodeRpc.prot" +
      "o\022\030com.my.rpc.grpc.namenode\"/\n\017RegisterR" +
      "equest\022\n\n\002ip\030\001 \001(\t\022\020\n\010hostname\030\002 \001(\t\"\"\n\020" +
      "RegisterResponse\022\016\n\006status\030\001 \001(\005\"0\n\020Hear" +
      "tbeatRequest\022\n\n\002ip\030\001 \001(\t\022\020\n\010hostname\030\002 \001" +
      "(\t\"#\n\021HeartbeatResponse\022\016\n\006status\030\001 \001(\005\"" +
      "\034\n\014MkdirRequest\022\014\n\004path\030\001 \001(\t\"\037\n\rMkdirRe" +
      "sponse\022\016\n\006status\030\001 \001(\005\"\037\n\017ShutdownReques" +
      "t\022\014\n\004code\030\001 \001(\005\"\"\n\020ShutdownResponse\022\016\n\006s" +
      "tatus\030\001 \001(\005\"*\n\024FetchEditsLogRequest\022\022\n\ns",
      "yncedTxid\030\001 \001(\003\")\n\025FetchEditsLogResponse" +
      "\022\020\n\010editsLog\030\001 \001(\t\"+\n\033UpdateCheckpointTx" +
      "idRequest\022\014\n\004txid\030\001 \001(\003\".\n\034UpdateCheckpo" +
      "intTxidResponse\022\016\n\006status\030\001 \001(\0052\221\005\n\017Name" +
      "NodeService\022a\n\010register\022).com.my.rpc.grp" +
      "c.namenode.RegisterRequest\032*.com.my.rpc." +
      "grpc.namenode.RegisterResponse\022d\n\theartb" +
      "eat\022*.com.my.rpc.grpc.namenode.Heartbeat" +
      "Request\032+.com.my.rpc.grpc.namenode.Heart" +
      "beatResponse\022X\n\005mkdir\022&.com.my.rpc.grpc.",
      "namenode.MkdirRequest\032\'.com.my.rpc.grpc." +
      "namenode.MkdirResponse\022a\n\010shutdown\022).com" +
      ".my.rpc.grpc.namenode.ShutdownRequest\032*." +
      "com.my.rpc.grpc.namenode.ShutdownRespons" +
      "e\022p\n\rfetchEditsLog\022..com.my.rpc.grpc.nam" +
      "enode.FetchEditsLogRequest\032/.com.my.rpc." +
      "grpc.namenode.FetchEditsLogResponse\022\205\001\n\024" +
      "updateCheckpointTxid\0225.com.my.rpc.grpc.n" +
      "amenode.UpdateCheckpointTxidRequest\0326.co" +
      "m.my.rpc.grpc.namenode.UpdateCheckpointT",
      "xidResponseB.\n\030com.my.rpc.grpc.namenodeB" +
      "\020NameNodeRpcProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_my_rpc_grpc_namenode_RegisterRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_my_rpc_grpc_namenode_RegisterRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_RegisterRequest_descriptor,
        new java.lang.String[] { "Ip", "Hostname", });
    internal_static_com_my_rpc_grpc_namenode_RegisterResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_my_rpc_grpc_namenode_RegisterResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_RegisterResponse_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_com_my_rpc_grpc_namenode_HeartbeatRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_my_rpc_grpc_namenode_HeartbeatRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_HeartbeatRequest_descriptor,
        new java.lang.String[] { "Ip", "Hostname", });
    internal_static_com_my_rpc_grpc_namenode_HeartbeatResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_my_rpc_grpc_namenode_HeartbeatResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_HeartbeatResponse_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_com_my_rpc_grpc_namenode_MkdirRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_my_rpc_grpc_namenode_MkdirRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_MkdirRequest_descriptor,
        new java.lang.String[] { "Path", });
    internal_static_com_my_rpc_grpc_namenode_MkdirResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_com_my_rpc_grpc_namenode_MkdirResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_MkdirResponse_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_com_my_rpc_grpc_namenode_ShutdownRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_com_my_rpc_grpc_namenode_ShutdownRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_ShutdownRequest_descriptor,
        new java.lang.String[] { "Code", });
    internal_static_com_my_rpc_grpc_namenode_ShutdownResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_com_my_rpc_grpc_namenode_ShutdownResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_ShutdownResponse_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_com_my_rpc_grpc_namenode_FetchEditsLogRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_com_my_rpc_grpc_namenode_FetchEditsLogRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_FetchEditsLogRequest_descriptor,
        new java.lang.String[] { "SyncedTxid", });
    internal_static_com_my_rpc_grpc_namenode_FetchEditsLogResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_com_my_rpc_grpc_namenode_FetchEditsLogResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_FetchEditsLogResponse_descriptor,
        new java.lang.String[] { "EditsLog", });
    internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidRequest_descriptor,
        new java.lang.String[] { "Txid", });
    internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidResponse_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_my_rpc_grpc_namenode_UpdateCheckpointTxidResponse_descriptor,
        new java.lang.String[] { "Status", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
