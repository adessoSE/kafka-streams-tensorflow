// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: log_memory.proto

package org.tensorflow.framework;

/**
 * Protobuf type {@code tensorflow.MemoryLogStep}
 */
public  final class MemoryLogStep extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tensorflow.MemoryLogStep)
    MemoryLogStepOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MemoryLogStep.newBuilder() to construct.
  private MemoryLogStep(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MemoryLogStep() {
    handle_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MemoryLogStep(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            stepId_ = input.readInt64();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            handle_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.framework.LogMemoryProtos.internal_static_tensorflow_MemoryLogStep_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.framework.LogMemoryProtos.internal_static_tensorflow_MemoryLogStep_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.framework.MemoryLogStep.class, org.tensorflow.framework.MemoryLogStep.Builder.class);
  }

  public static final int STEP_ID_FIELD_NUMBER = 1;
  private long stepId_;
  /**
   * <pre>
   * Process-unique step id.
   * </pre>
   *
   * <code>int64 step_id = 1;</code>
   */
  public long getStepId() {
    return stepId_;
  }

  public static final int HANDLE_FIELD_NUMBER = 2;
  private volatile java.lang.Object handle_;
  /**
   * <pre>
   * Handle describing the feeds and fetches of the step.
   * </pre>
   *
   * <code>string handle = 2;</code>
   */
  public java.lang.String getHandle() {
    java.lang.Object ref = handle_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      handle_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Handle describing the feeds and fetches of the step.
   * </pre>
   *
   * <code>string handle = 2;</code>
   */
  public com.google.protobuf.ByteString
      getHandleBytes() {
    java.lang.Object ref = handle_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      handle_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (stepId_ != 0L) {
      output.writeInt64(1, stepId_);
    }
    if (!getHandleBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, handle_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (stepId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, stepId_);
    }
    if (!getHandleBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, handle_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.tensorflow.framework.MemoryLogStep)) {
      return super.equals(obj);
    }
    org.tensorflow.framework.MemoryLogStep other = (org.tensorflow.framework.MemoryLogStep) obj;

    if (getStepId()
        != other.getStepId()) return false;
    if (!getHandle()
        .equals(other.getHandle())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + STEP_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getStepId());
    hash = (37 * hash) + HANDLE_FIELD_NUMBER;
    hash = (53 * hash) + getHandle().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.tensorflow.framework.MemoryLogStep parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.framework.MemoryLogStep parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.framework.MemoryLogStep parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.framework.MemoryLogStep parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.framework.MemoryLogStep parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.framework.MemoryLogStep parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.framework.MemoryLogStep parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.framework.MemoryLogStep parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.framework.MemoryLogStep parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.tensorflow.framework.MemoryLogStep parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.framework.MemoryLogStep parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.framework.MemoryLogStep parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.tensorflow.framework.MemoryLogStep prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * Protobuf type {@code tensorflow.MemoryLogStep}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.MemoryLogStep)
      org.tensorflow.framework.MemoryLogStepOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.framework.LogMemoryProtos.internal_static_tensorflow_MemoryLogStep_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.framework.LogMemoryProtos.internal_static_tensorflow_MemoryLogStep_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.framework.MemoryLogStep.class, org.tensorflow.framework.MemoryLogStep.Builder.class);
    }

    // Construct using org.tensorflow.framework.MemoryLogStep.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      stepId_ = 0L;

      handle_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.framework.LogMemoryProtos.internal_static_tensorflow_MemoryLogStep_descriptor;
    }

    @java.lang.Override
    public org.tensorflow.framework.MemoryLogStep getDefaultInstanceForType() {
      return org.tensorflow.framework.MemoryLogStep.getDefaultInstance();
    }

    @java.lang.Override
    public org.tensorflow.framework.MemoryLogStep build() {
      org.tensorflow.framework.MemoryLogStep result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.tensorflow.framework.MemoryLogStep buildPartial() {
      org.tensorflow.framework.MemoryLogStep result = new org.tensorflow.framework.MemoryLogStep(this);
      result.stepId_ = stepId_;
      result.handle_ = handle_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.tensorflow.framework.MemoryLogStep) {
        return mergeFrom((org.tensorflow.framework.MemoryLogStep)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.framework.MemoryLogStep other) {
      if (other == org.tensorflow.framework.MemoryLogStep.getDefaultInstance()) return this;
      if (other.getStepId() != 0L) {
        setStepId(other.getStepId());
      }
      if (!other.getHandle().isEmpty()) {
        handle_ = other.handle_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.tensorflow.framework.MemoryLogStep parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.tensorflow.framework.MemoryLogStep) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long stepId_ ;
    /**
     * <pre>
     * Process-unique step id.
     * </pre>
     *
     * <code>int64 step_id = 1;</code>
     */
    public long getStepId() {
      return stepId_;
    }
    /**
     * <pre>
     * Process-unique step id.
     * </pre>
     *
     * <code>int64 step_id = 1;</code>
     */
    public Builder setStepId(long value) {
      
      stepId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Process-unique step id.
     * </pre>
     *
     * <code>int64 step_id = 1;</code>
     */
    public Builder clearStepId() {
      
      stepId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object handle_ = "";
    /**
     * <pre>
     * Handle describing the feeds and fetches of the step.
     * </pre>
     *
     * <code>string handle = 2;</code>
     */
    public java.lang.String getHandle() {
      java.lang.Object ref = handle_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        handle_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * Handle describing the feeds and fetches of the step.
     * </pre>
     *
     * <code>string handle = 2;</code>
     */
    public com.google.protobuf.ByteString
        getHandleBytes() {
      java.lang.Object ref = handle_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        handle_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Handle describing the feeds and fetches of the step.
     * </pre>
     *
     * <code>string handle = 2;</code>
     */
    public Builder setHandle(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      handle_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Handle describing the feeds and fetches of the step.
     * </pre>
     *
     * <code>string handle = 2;</code>
     */
    public Builder clearHandle() {
      
      handle_ = getDefaultInstance().getHandle();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Handle describing the feeds and fetches of the step.
     * </pre>
     *
     * <code>string handle = 2;</code>
     */
    public Builder setHandleBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      handle_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tensorflow.MemoryLogStep)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.MemoryLogStep)
  private static final org.tensorflow.framework.MemoryLogStep DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.framework.MemoryLogStep();
  }

  public static org.tensorflow.framework.MemoryLogStep getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MemoryLogStep>
      PARSER = new com.google.protobuf.AbstractParser<MemoryLogStep>() {
    @java.lang.Override
    public MemoryLogStep parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MemoryLogStep(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MemoryLogStep> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MemoryLogStep> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.tensorflow.framework.MemoryLogStep getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
