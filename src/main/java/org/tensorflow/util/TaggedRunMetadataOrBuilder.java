// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: event.proto

package org.tensorflow.util;

public interface TaggedRunMetadataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.TaggedRunMetadata)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Tag name associated with this metadata.
   * </pre>
   *
   * <code>string tag = 1;</code>
   */
  java.lang.String getTag();
  /**
   * <pre>
   * Tag name associated with this metadata.
   * </pre>
   *
   * <code>string tag = 1;</code>
   */
  com.google.protobuf.ByteString
      getTagBytes();

  /**
   * <pre>
   * Byte-encoded version of the `RunMetadata` proto in order to allow lazy
   * deserialization.
   * </pre>
   *
   * <code>bytes run_metadata = 2;</code>
   */
  com.google.protobuf.ByteString getRunMetadata();
}