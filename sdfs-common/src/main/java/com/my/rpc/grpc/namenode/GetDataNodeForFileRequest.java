// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/my/rpc/grpc/proto/NameNodeRpc.proto

package com.my.rpc.grpc.namenode;

/**
 * Protobuf type {@code com.my.rpc.grpc.namenode.GetDataNodeForFileRequest}
 */
public  final class GetDataNodeForFileRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.my.rpc.grpc.namenode.GetDataNodeForFileRequest)
    GetDataNodeForFileRequestOrBuilder {
  // Use GetDataNodeForFileRequest.newBuilder() to construct.
  private GetDataNodeForFileRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetDataNodeForFileRequest() {
    filename_ = "";
    excludedDataNodeId_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GetDataNodeForFileRequest(
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            filename_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            excludedDataNodeId_ = s;
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
    return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.my.rpc.grpc.namenode.GetDataNodeForFileRequest.class, com.my.rpc.grpc.namenode.GetDataNodeForFileRequest.Builder.class);
  }

  public static final int FILENAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object filename_;
  /**
   * <code>optional string filename = 1;</code>
   */
  public java.lang.String getFilename() {
    java.lang.Object ref = filename_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      filename_ = s;
      return s;
    }
  }
  /**
   * <code>optional string filename = 1;</code>
   */
  public com.google.protobuf.ByteString
      getFilenameBytes() {
    java.lang.Object ref = filename_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      filename_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EXCLUDEDDATANODEID_FIELD_NUMBER = 2;
  private volatile java.lang.Object excludedDataNodeId_;
  /**
   * <code>optional string excludedDataNodeId = 2;</code>
   */
  public java.lang.String getExcludedDataNodeId() {
    java.lang.Object ref = excludedDataNodeId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      excludedDataNodeId_ = s;
      return s;
    }
  }
  /**
   * <code>optional string excludedDataNodeId = 2;</code>
   */
  public com.google.protobuf.ByteString
      getExcludedDataNodeIdBytes() {
    java.lang.Object ref = excludedDataNodeId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      excludedDataNodeId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getFilenameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, filename_);
    }
    if (!getExcludedDataNodeIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, excludedDataNodeId_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getFilenameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, filename_);
    }
    if (!getExcludedDataNodeIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, excludedDataNodeId_);
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
    if (!(obj instanceof com.my.rpc.grpc.namenode.GetDataNodeForFileRequest)) {
      return super.equals(obj);
    }
    com.my.rpc.grpc.namenode.GetDataNodeForFileRequest other = (com.my.rpc.grpc.namenode.GetDataNodeForFileRequest) obj;

    boolean result = true;
    result = result && getFilename()
        .equals(other.getFilename());
    result = result && getExcludedDataNodeId()
        .equals(other.getExcludedDataNodeId());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + FILENAME_FIELD_NUMBER;
    hash = (53 * hash) + getFilename().hashCode();
    hash = (37 * hash) + EXCLUDEDDATANODEID_FIELD_NUMBER;
    hash = (53 * hash) + getExcludedDataNodeId().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parseFrom(
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
  public static Builder newBuilder(com.my.rpc.grpc.namenode.GetDataNodeForFileRequest prototype) {
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
   * Protobuf type {@code com.my.rpc.grpc.namenode.GetDataNodeForFileRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.my.rpc.grpc.namenode.GetDataNodeForFileRequest)
      com.my.rpc.grpc.namenode.GetDataNodeForFileRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.my.rpc.grpc.namenode.GetDataNodeForFileRequest.class, com.my.rpc.grpc.namenode.GetDataNodeForFileRequest.Builder.class);
    }

    // Construct using com.my.rpc.grpc.namenode.GetDataNodeForFileRequest.newBuilder()
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
      filename_ = "";

      excludedDataNodeId_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileRequest_descriptor;
    }

    public com.my.rpc.grpc.namenode.GetDataNodeForFileRequest getDefaultInstanceForType() {
      return com.my.rpc.grpc.namenode.GetDataNodeForFileRequest.getDefaultInstance();
    }

    public com.my.rpc.grpc.namenode.GetDataNodeForFileRequest build() {
      com.my.rpc.grpc.namenode.GetDataNodeForFileRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.my.rpc.grpc.namenode.GetDataNodeForFileRequest buildPartial() {
      com.my.rpc.grpc.namenode.GetDataNodeForFileRequest result = new com.my.rpc.grpc.namenode.GetDataNodeForFileRequest(this);
      result.filename_ = filename_;
      result.excludedDataNodeId_ = excludedDataNodeId_;
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
      if (other instanceof com.my.rpc.grpc.namenode.GetDataNodeForFileRequest) {
        return mergeFrom((com.my.rpc.grpc.namenode.GetDataNodeForFileRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.my.rpc.grpc.namenode.GetDataNodeForFileRequest other) {
      if (other == com.my.rpc.grpc.namenode.GetDataNodeForFileRequest.getDefaultInstance()) return this;
      if (!other.getFilename().isEmpty()) {
        filename_ = other.filename_;
        onChanged();
      }
      if (!other.getExcludedDataNodeId().isEmpty()) {
        excludedDataNodeId_ = other.excludedDataNodeId_;
        onChanged();
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
      com.my.rpc.grpc.namenode.GetDataNodeForFileRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.my.rpc.grpc.namenode.GetDataNodeForFileRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object filename_ = "";
    /**
     * <code>optional string filename = 1;</code>
     */
    public java.lang.String getFilename() {
      java.lang.Object ref = filename_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        filename_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string filename = 1;</code>
     */
    public com.google.protobuf.ByteString
        getFilenameBytes() {
      java.lang.Object ref = filename_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        filename_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string filename = 1;</code>
     */
    public Builder setFilename(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      filename_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string filename = 1;</code>
     */
    public Builder clearFilename() {
      
      filename_ = getDefaultInstance().getFilename();
      onChanged();
      return this;
    }
    /**
     * <code>optional string filename = 1;</code>
     */
    public Builder setFilenameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      filename_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object excludedDataNodeId_ = "";
    /**
     * <code>optional string excludedDataNodeId = 2;</code>
     */
    public java.lang.String getExcludedDataNodeId() {
      java.lang.Object ref = excludedDataNodeId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        excludedDataNodeId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string excludedDataNodeId = 2;</code>
     */
    public com.google.protobuf.ByteString
        getExcludedDataNodeIdBytes() {
      java.lang.Object ref = excludedDataNodeId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        excludedDataNodeId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string excludedDataNodeId = 2;</code>
     */
    public Builder setExcludedDataNodeId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      excludedDataNodeId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string excludedDataNodeId = 2;</code>
     */
    public Builder clearExcludedDataNodeId() {
      
      excludedDataNodeId_ = getDefaultInstance().getExcludedDataNodeId();
      onChanged();
      return this;
    }
    /**
     * <code>optional string excludedDataNodeId = 2;</code>
     */
    public Builder setExcludedDataNodeIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      excludedDataNodeId_ = value;
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


    // @@protoc_insertion_point(builder_scope:com.my.rpc.grpc.namenode.GetDataNodeForFileRequest)
  }

  // @@protoc_insertion_point(class_scope:com.my.rpc.grpc.namenode.GetDataNodeForFileRequest)
  private static final com.my.rpc.grpc.namenode.GetDataNodeForFileRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.my.rpc.grpc.namenode.GetDataNodeForFileRequest();
  }

  public static com.my.rpc.grpc.namenode.GetDataNodeForFileRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetDataNodeForFileRequest>
      PARSER = new com.google.protobuf.AbstractParser<GetDataNodeForFileRequest>() {
    public GetDataNodeForFileRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetDataNodeForFileRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetDataNodeForFileRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetDataNodeForFileRequest> getParserForType() {
    return PARSER;
  }

  public com.my.rpc.grpc.namenode.GetDataNodeForFileRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

