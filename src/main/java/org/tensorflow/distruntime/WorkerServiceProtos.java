// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: worker_service.proto

package org.tensorflow.distruntime;

public final class WorkerServiceProtos {
  private WorkerServiceProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024worker_service.proto\022\017tensorflow.grpc\032" +
      "\014worker.proto2\360\t\n\rWorkerService\022H\n\tGetSt" +
      "atus\022\034.tensorflow.GetStatusRequest\032\035.ten" +
      "sorflow.GetStatusResponse\022f\n\023CreateWorke" +
      "rSession\022&.tensorflow.CreateWorkerSessio" +
      "nRequest\032\'.tensorflow.CreateWorkerSessio" +
      "nResponse\022f\n\023DeleteWorkerSession\022&.tenso" +
      "rflow.DeleteWorkerSessionRequest\032\'.tenso" +
      "rflow.DeleteWorkerSessionResponse\022T\n\rReg" +
      "isterGraph\022 .tensorflow.RegisterGraphReq" +
      "uest\032!.tensorflow.RegisterGraphResponse\022" +
      "Z\n\017DeregisterGraph\022\".tensorflow.Deregist" +
      "erGraphRequest\032#.tensorflow.DeregisterGr" +
      "aphResponse\022E\n\010RunGraph\022\033.tensorflow.Run" +
      "GraphRequest\032\034.tensorflow.RunGraphRespon" +
      "se\022Q\n\014CleanupGraph\022\037.tensorflow.CleanupG" +
      "raphRequest\032 .tensorflow.CleanupGraphRes" +
      "ponse\022K\n\nCleanupAll\022\035.tensorflow.Cleanup" +
      "AllRequest\032\036.tensorflow.CleanupAllRespon" +
      "se\022M\n\nRecvTensor\022\035.tensorflow.RecvTensor" +
      "Request\032\036.tensorflow.RecvTensorResponse\"" +
      "\000\022B\n\007Logging\022\032.tensorflow.LoggingRequest" +
      "\032\033.tensorflow.LoggingResponse\022B\n\007Tracing" +
      "\022\032.tensorflow.TracingRequest\032\033.tensorflo" +
      "w.TracingResponse\022D\n\007RecvBuf\022\032.tensorflo" +
      "w.RecvBufRequest\032\033.tensorflow.RecvBufRes" +
      "ponse\"\000\022Z\n\017GetStepSequence\022\".tensorflow." +
      "GetStepSequenceRequest\032#.tensorflow.GetS" +
      "tepSequenceResponse\022T\n\rCompleteGroup\022 .t" +
      "ensorflow.CompleteGroupRequest\032!.tensorf" +
      "low.CompleteGroupResponse\022]\n\020CompleteIns" +
      "tance\022#.tensorflow.CompleteInstanceReque" +
      "st\032$.tensorflow.CompleteInstanceResponse" +
      "Bq\n\032org.tensorflow.distruntimeB\023WorkerSe" +
      "rviceProtosP\001Z<github.com/tensorflow/ten" +
      "sorflow/tensorflow/go/core/protobufb\006pro" +
      "to3"
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
          org.tensorflow.distruntime.WorkerProtos.getDescriptor(),
        }, assigner);
    org.tensorflow.distruntime.WorkerProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}