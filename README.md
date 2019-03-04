# Kafka Streams Tensorflow integration for fraud detection
Integrates Kafka Streams and Tensorflow

This project is based on
https://github.com/kaiwaehner/kafka-streams-machine-learning-example

The Tensorflow files were built using the following commands:


```

Install ProtoC:
sudo yum install autoconf automake libtool curl make g++ unzip libatomic
https://gist.github.com/sofyanhadia/37787e5ed098c97919b8c593f0ec44d8


wget tensorflow and tensorflow_serving from git releases
 
cd /home/cloudera/Documents/kafka_tensorflow/1.13.0_full/tensorflow/tensorflow/core/framework

protoc -I=. --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java --grpc-java_out=java-pb-files --java_out=java-pb-files *.proto

find ./*.proto -type f -exec sed -i 's+tensorflow_serving/config/++g' {} +

wget http://central.maven.org/maven2/io/grpc/protoc-gen-grpc-java/1.19.0/protoc-gen-grpc-java-1.19.0-linux-x86_64.exe
```

