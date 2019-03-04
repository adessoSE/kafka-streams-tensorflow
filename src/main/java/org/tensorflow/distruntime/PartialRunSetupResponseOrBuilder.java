// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: master.proto

package org.tensorflow.distruntime;

public interface PartialRunSetupResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.PartialRunSetupResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The unique handle corresponding to the ongoing partial run call setup by
   * the invocation to PartialRunSetup. This handle may be passed to
   * RunStepRequest to send and receive tensors for this partial run.
   * </pre>
   *
   * <code>string partial_run_handle = 1;</code>
   */
  java.lang.String getPartialRunHandle();
  /**
   * <pre>
   * The unique handle corresponding to the ongoing partial run call setup by
   * the invocation to PartialRunSetup. This handle may be passed to
   * RunStepRequest to send and receive tensors for this partial run.
   * </pre>
   *
   * <code>string partial_run_handle = 1;</code>
   */
  com.google.protobuf.ByteString
      getPartialRunHandleBytes();
}