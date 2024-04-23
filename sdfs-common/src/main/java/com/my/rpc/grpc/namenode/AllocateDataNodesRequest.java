// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/my/rpc/grpc/proto/NameNodeRpc.proto

package com.my.rpc.grpc.namenode;

/**
 * Protobuf type {@code com.my.rpc.grpc.namenode.AllocateDataNodesRequest}
 */
public  final class AllocateDataNodesRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.my.rpc.grpc.namenode.AllocateDataNodesRequest)
    AllocateDataNodesRequestOrBuilder {
  // Use AllocateDataNodesRequest.newBuilder() to construct.
  private AllocateDataNodesRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllocateDataNodesRequest() {
    fileSize_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllocateDataNodesRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            fileSize_ = input.readInt64();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_AllocateDataNodesRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_AllocateDataNodesRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.my.rpc.grpc.namenode.AllocateDataNodesRequest.class, com.my.rpc.grpc.namenode.AllocateDataNodesRequest.Builder.class);
  }

  public static final int FILESIZE_FIELD_NUMBER = 1;
  private long fileSize_;
  /**
   * <code>optional int64 fileSize = 1;</code>
   */
  public long getFileSize() {
    return fileSize_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (fileSize_ != 0L) {
      output.writeInt64(1, fileSize_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (fileSize_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, fileSize_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.my.rpc.grpc.namenode.AllocateDataNodesRequest)) {
      return super.equals(obj);
    }
    com.my.rpc.grpc.namenode.AllocateDataNodesRequest other = (com.my.rpc.grpc.namenode.AllocateDataNodesRequest) obj;

    boolean result = true;
    result = result && (getFileSize()
        == other.getFileSize());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + FILESIZE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFileSize());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.my.rpc.grpc.namenode.AllocateDataNodesRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.my.rpc.grpc.namenode.AllocateDataNodesRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.my.rpc.grpc.namenode.AllocateDataNodesRequest)
      com.my.rpc.grpc.namenode.AllocateDataNodesRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_AllocateDataNodesRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_AllocateDataNodesRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.my.rpc.grpc.namenode.AllocateDataNodesRequest.class, com.my.rpc.grpc.namenode.AllocateDataNodesRequest.Builder.class);
    }

    // Construct using com.my.rpc.grpc.namenode.AllocateDataNodesRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      fileSize_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_AllocateDataNodesRequest_descriptor;
    }

    public com.my.rpc.grpc.namenode.AllocateDataNodesRequest getDefaultInstanceForType() {
      return com.my.rpc.grpc.namenode.AllocateDataNodesRequest.getDefaultInstance();
    }

    public com.my.rpc.grpc.namenode.AllocateDataNodesRequest build() {
      com.my.rpc.grpc.namenode.AllocateDataNodesRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.my.rpc.grpc.namenode.AllocateDataNodesRequest buildPartial() {
      com.my.rpc.grpc.namenode.AllocateDataNodesRequest result = new com.my.rpc.grpc.namenode.AllocateDataNodesRequest(this);
      result.fileSize_ = fileSize_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.my.rpc.grpc.namenode.AllocateDataNodesRequest) {
        return mergeFrom((com.my.rpc.grpc.namenode.AllocateDataNodesRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.my.rpc.grpc.namenode.AllocateDataNodesRequest other) {
      if (other == com.my.rpc.grpc.namenode.AllocateDataNodesRequest.getDefaultInstance()) return this;
      if (other.getFileSize() != 0L) {
        setFileSize(other.getFileSize());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.my.rpc.grpc.namenode.AllocateDataNodesRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.my.rpc.grpc.namenode.AllocateDataNodesRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long fileSize_ ;
    /**
     * <code>optional int64 fileSize = 1;</code>
     */
    public long getFileSize() {
      return fileSize_;
    }
    /**
     * <code>optional int64 fileSize = 1;</code>
     */
    public Builder setFileSize(long value) {
      
      fileSize_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 fileSize = 1;</code>
     */
    public Builder clearFileSize() {
      
      fileSize_ = 0L;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.my.rpc.grpc.namenode.AllocateDataNodesRequest)
  }

  // @@protoc_insertion_point(class_scope:com.my.rpc.grpc.namenode.AllocateDataNodesRequest)
  private static final com.my.rpc.grpc.namenode.AllocateDataNodesRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.my.rpc.grpc.namenode.AllocateDataNodesRequest();
  }

  public static com.my.rpc.grpc.namenode.AllocateDataNodesRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllocateDataNodesRequest>
      PARSER = new com.google.protobuf.AbstractParser<AllocateDataNodesRequest>() {
    public AllocateDataNodesRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllocateDataNodesRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllocateDataNodesRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllocateDataNodesRequest> getParserForType() {
    return PARSER;
  }

  public com.my.rpc.grpc.namenode.AllocateDataNodesRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
