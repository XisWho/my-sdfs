// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/my/rpc/grpc/proto/NameNodeRpc.proto

package com.my.rpc.grpc.namenode;

/**
 * Protobuf type {@code com.my.rpc.grpc.namenode.GetDataNodeForFileResponse}
 */
public  final class GetDataNodeForFileResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.my.rpc.grpc.namenode.GetDataNodeForFileResponse)
    GetDataNodeForFileResponseOrBuilder {
  // Use GetDataNodeForFileResponse.newBuilder() to construct.
  private GetDataNodeForFileResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetDataNodeForFileResponse() {
    datanodeInfo_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GetDataNodeForFileResponse(
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

            datanodeInfo_ = s;
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
    return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.my.rpc.grpc.namenode.GetDataNodeForFileResponse.class, com.my.rpc.grpc.namenode.GetDataNodeForFileResponse.Builder.class);
  }

  public static final int DATANODEINFO_FIELD_NUMBER = 1;
  private volatile java.lang.Object datanodeInfo_;
  /**
   * <code>optional string datanodeInfo = 1;</code>
   */
  public java.lang.String getDatanodeInfo() {
    java.lang.Object ref = datanodeInfo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      datanodeInfo_ = s;
      return s;
    }
  }
  /**
   * <code>optional string datanodeInfo = 1;</code>
   */
  public com.google.protobuf.ByteString
      getDatanodeInfoBytes() {
    java.lang.Object ref = datanodeInfo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      datanodeInfo_ = b;
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
    if (!getDatanodeInfoBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, datanodeInfo_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getDatanodeInfoBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, datanodeInfo_);
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
    if (!(obj instanceof com.my.rpc.grpc.namenode.GetDataNodeForFileResponse)) {
      return super.equals(obj);
    }
    com.my.rpc.grpc.namenode.GetDataNodeForFileResponse other = (com.my.rpc.grpc.namenode.GetDataNodeForFileResponse) obj;

    boolean result = true;
    result = result && getDatanodeInfo()
        .equals(other.getDatanodeInfo());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + DATANODEINFO_FIELD_NUMBER;
    hash = (53 * hash) + getDatanodeInfo().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parseFrom(
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
  public static Builder newBuilder(com.my.rpc.grpc.namenode.GetDataNodeForFileResponse prototype) {
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
   * Protobuf type {@code com.my.rpc.grpc.namenode.GetDataNodeForFileResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.my.rpc.grpc.namenode.GetDataNodeForFileResponse)
      com.my.rpc.grpc.namenode.GetDataNodeForFileResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.my.rpc.grpc.namenode.GetDataNodeForFileResponse.class, com.my.rpc.grpc.namenode.GetDataNodeForFileResponse.Builder.class);
    }

    // Construct using com.my.rpc.grpc.namenode.GetDataNodeForFileResponse.newBuilder()
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
      datanodeInfo_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.my.rpc.grpc.namenode.NameNodeRpcProto.internal_static_com_my_rpc_grpc_namenode_GetDataNodeForFileResponse_descriptor;
    }

    public com.my.rpc.grpc.namenode.GetDataNodeForFileResponse getDefaultInstanceForType() {
      return com.my.rpc.grpc.namenode.GetDataNodeForFileResponse.getDefaultInstance();
    }

    public com.my.rpc.grpc.namenode.GetDataNodeForFileResponse build() {
      com.my.rpc.grpc.namenode.GetDataNodeForFileResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.my.rpc.grpc.namenode.GetDataNodeForFileResponse buildPartial() {
      com.my.rpc.grpc.namenode.GetDataNodeForFileResponse result = new com.my.rpc.grpc.namenode.GetDataNodeForFileResponse(this);
      result.datanodeInfo_ = datanodeInfo_;
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
      if (other instanceof com.my.rpc.grpc.namenode.GetDataNodeForFileResponse) {
        return mergeFrom((com.my.rpc.grpc.namenode.GetDataNodeForFileResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.my.rpc.grpc.namenode.GetDataNodeForFileResponse other) {
      if (other == com.my.rpc.grpc.namenode.GetDataNodeForFileResponse.getDefaultInstance()) return this;
      if (!other.getDatanodeInfo().isEmpty()) {
        datanodeInfo_ = other.datanodeInfo_;
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
      com.my.rpc.grpc.namenode.GetDataNodeForFileResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.my.rpc.grpc.namenode.GetDataNodeForFileResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object datanodeInfo_ = "";
    /**
     * <code>optional string datanodeInfo = 1;</code>
     */
    public java.lang.String getDatanodeInfo() {
      java.lang.Object ref = datanodeInfo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        datanodeInfo_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string datanodeInfo = 1;</code>
     */
    public com.google.protobuf.ByteString
        getDatanodeInfoBytes() {
      java.lang.Object ref = datanodeInfo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        datanodeInfo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string datanodeInfo = 1;</code>
     */
    public Builder setDatanodeInfo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      datanodeInfo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string datanodeInfo = 1;</code>
     */
    public Builder clearDatanodeInfo() {
      
      datanodeInfo_ = getDefaultInstance().getDatanodeInfo();
      onChanged();
      return this;
    }
    /**
     * <code>optional string datanodeInfo = 1;</code>
     */
    public Builder setDatanodeInfoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      datanodeInfo_ = value;
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


    // @@protoc_insertion_point(builder_scope:com.my.rpc.grpc.namenode.GetDataNodeForFileResponse)
  }

  // @@protoc_insertion_point(class_scope:com.my.rpc.grpc.namenode.GetDataNodeForFileResponse)
  private static final com.my.rpc.grpc.namenode.GetDataNodeForFileResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.my.rpc.grpc.namenode.GetDataNodeForFileResponse();
  }

  public static com.my.rpc.grpc.namenode.GetDataNodeForFileResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetDataNodeForFileResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetDataNodeForFileResponse>() {
    public GetDataNodeForFileResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetDataNodeForFileResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetDataNodeForFileResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetDataNodeForFileResponse> getParserForType() {
    return PARSER;
  }

  public com.my.rpc.grpc.namenode.GetDataNodeForFileResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
