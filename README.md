# Kafka Streams Tensorflow integration

## Introduction

Integrates Kafka Streams and Tensorflow asynchronously using gRPC

This project is based on
https://github.com/kaiwaehner/kafka-streams-machine-learning-example

## Use Cases

### Fraud

In order to do fraud detection, this application will read from a Kafka topic using Kafka Streams, send each message to Tensorflow and write the resulting message (including the classification) to another Kafka topic.

### Credit Scoring
To be implemented

## Pseudocode (Fraud)

```
1) For each message in INPUT_TOPIC:
    - Send to Tensorflow model
    - Add callback to sent message

2) For each sent message:
    (onSuccess)
        -Send message to output Kafka topic asynchronously, without waiting for acknowledgement.

3) For each message sent to Kafka Topic
    (Acknowledgement: Success/failure)
        - Show status returned by Kafka acknowledgement
````

## Building and Running

### Building this application

Because this is a Maven application, the following should suffice:
```bash
git clone https://github.com/adessoAG/kafka-streams-tensorflow.git
mvn clean package
``` 

### Running this application

As described in [Confluent - Running Streams Application](https://docs.confluent.io/current/streams/developer-guide/running-app.html) this application can be executed as follows:
```bash
java -cp kafka-streams-tensorflow-1.0-SNAPSHOT-jar-with-dependencies.jar de.adesso.kafkaml.FraudModelServing test.fraud.properties
```
This application also supports overwriting parameters from the configuration file via additional command line parameters:
```bash
java -cp kafka-streams-tensorflow-1.0-SNAPSHOT-jar-with-dependencies.jar de.adesso.kafkaml.FraudModelServing test.fraud.properties tf.model.version=2.0
```
 

### Building Tensorflow .proto files

The Tensorflow files were built using the following commands:

```

Install ProtoC:
sudo yum install autoconf automake libtool curl make g++ unzip libatomic
https://gist.github.com/sofyanhadia/37787e5ed098c97919b8c593f0ec44d8


wget tensorflow and tensorflow_serving from git releases
 
cd /.../tensorflow/tensorflow/core/framework

wget http://central.maven.org/maven2/io/grpc/protoc-gen-grpc-java/1.19.0/protoc-gen-grpc-java-1.19.0-linux-x86_64.exe

Keep trying to build using:
protoc -I=. --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java --grpc-java_out=java-pb-files --java_out=java-pb-files *.proto

If there is an error with something missing, copy all .proto-files from that directory here and remove/replace their paths using the following commands
find ./*.proto -type f -exec sed -i 's+tensorflow_serving/config/++g' {} +

# Alternatively:
Directory strucutre:
test/tensorflow/core
test/tensorflow_serving/framework/

cd test

for x in $(find '*.proto')
do
protoc -I=. --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java-1.19.0-linux-x86_64.exe \
--grpc-java_out=java-pb-files --java_out=java-pb-files $x
done

```
## References

### Framework Documentation
https://grpc.io/docs/tutorials/basic/java.html

### Code
https://github.com/grpc/grpc-java/blob/master/benchmarks/src/main/java/io/grpc/benchmarks/qps/AsyncClient.java

https://github.com/tensorflow/serving/blob/master/tensorflow_serving/apis/prediction_service.proto

https://github.com/tobegit3hub/deep_image_model/blob/master/java_predict_client/src/main/java/com/tobe/InceptionPredictClient.java

https://github.com/fzschornack/tensorflow-java-client/blob/master/src/main/java/tensorflow/serving/PredictionServiceGrpc.java

 
