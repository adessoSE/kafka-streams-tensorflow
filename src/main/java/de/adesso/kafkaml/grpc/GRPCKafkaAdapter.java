package de.adesso.kafkaml.grpc;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.util.JsonFormat;
import de.adesso.kafkaml.conf.ConfigReader;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.tensorflow.framework.TensorProto;
import tensorflow.serving.Model;
import tensorflow.serving.Predict;
import tensorflow.serving.PredictionServiceGrpc;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 *
 * Connection between Kafka and Tensorflow via asynchronous REST calls using GRPC
 *
 */
public class GRPCKafkaAdapter {

    // TODO: Extract Kafka part into separate class
    // TODO: Module structure
    // TODO: Refactor serveAndReport-Method

    // Kafka Variables
    private final String inputTopic;
    private final String bootstrapServers;

    // Tensorflow Variables
    private final String tfModelName;
    private final int tfModelVersion;

    // Tensorflow Services
    private final ManagedChannel channel;
    // FutureStub allows asynchronous behaviour
    private final PredictionServiceGrpc.PredictionServiceFutureStub fStub;

    // Kafka Services
    // FutureCallback for sending data to Kafka
    private final TensorflowKafkaCallback kafkaReporter;



    public GRPCKafkaAdapter(Map<String, String> confIn) {
        // Read configuration
        inputTopic = confIn.get(ConfigReader.KAFKA_INPUT_TOPIC);
        bootstrapServers = confIn.get(ConfigReader.KAFKA_BOOTSTRAP_SERVERS);
        tfModelName = confIn.get(ConfigReader.TENSORFLOW_MODEL_NAME);
        tfModelVersion = Integer.parseInt(confIn.get(ConfigReader.TENSORFLOW_MODEL_VERSION));

        // Initialize Tensorflow
        String tfHost = confIn.get(ConfigReader.TENSORFLOW_SERVING_HOST);
        int tfPort = Integer.parseInt(confIn.get(ConfigReader.TENSORFLOW_SERVING_PORT));
        channel = ManagedChannelBuilder.forAddress(tfHost, tfPort).usePlaintext().build();
        fStub = PredictionServiceGrpc.newFutureStub(channel);

        // Initialize Kafka
        kafkaReporter = new TensorflowKafkaCallback(confIn);
    }


    public void runKafka() {
        //TODO: Add Listeners to Future requests
        //TODO: Proper configuration
        //TODO: Kafka
        Properties kafkaStreamsConfig = generateKafkaConfig();

        final StreamsBuilder builder = new StreamsBuilder();

        final KStream<String, String> inputLines = builder.stream(inputTopic);

        // Key is not relevant
        inputLines.foreach((key, value) -> predictAndReport(tfModelName, tfModelVersion, value));

        startKafkaStream(kafkaStreamsConfig, builder);
    }

    private void startKafkaStream(Properties kafkaStreamsConfig, StreamsBuilder builder) {

        // Start Kafka Streams Application to process new incoming images from the Input
        // Topic
        final KafkaStreams streams = new KafkaStreams(builder.build(), kafkaStreamsConfig);

        streams.cleanUp();

        streams.start();

        System.out.println("Fraud classification is running...");

        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka
        // Streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        // Services of this class
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    private Properties generateKafkaConfig() {
        // Configure Kafka Streams Application
        final Properties streamsConfiguration = new Properties();
        // Give the Streams application a unique name. The name must be unique
        // in the Kafka cluster
        // against which the application is run.
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG,
                "kafka-streams-tensorflow-fraud");
        // Where to find Kafka broker(s).
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Specify default (de)serializers for record keys and for record
        // values.
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return streamsConfiguration;
    }

/*
    void runProducer(final int sendMessageCount) throws InterruptedException {
        long time = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(sendMessageCount);

        try {
            for (long index = time; index < time + sendMessageCount; index++) {
                final ProducerRecord<String, String> record =
                        new ProducerRecord<>(this.outputTopic, String.valueOf(index), "Hello Mom " + index);
                producer.send(record, (metadata, exception) -> {
                    long elapsedTime = System.currentTimeMillis() - time;
                    if (metadata != null) {
                        System.out.printf("sent record(key=%s value=%s) " +
                                        "meta(partition=%d, offset=%d) time=%d\n",
                                record.key(), record.value(), metadata.partition(),
                                metadata.offset(), elapsedTime);
                    } else {
                        exception.printStackTrace();
                    }
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await(25, TimeUnit.SECONDS);
        }finally {
            producer.flush();
            producer.close();
        }
    }
*/

/*
    private void runKafka() {
        // In the subsequent lines we define the processing topology of the
        // Streams application.
        final StreamsBuilder builder = new StreamsBuilder();

        final KStream<String, String> inputLines = builder.stream(inputTopic);

        // Key is not relevant
        inputLines.foreach((key, value) -> {
            predictAndReport(tfModelName, tfModelVersion, value);
        });
        // Start Kafka Streams Application to process new incoming images from the Input
        // Topic
        final KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);

        streams.cleanUp();

        streams.start();

        System.out.println("Fraud classification is running...");

        System.out.println("Input to Kafka Topic " + inputTopic + "; Output to Kafka Topic " + inputTopic);

        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka
        // Streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
*/

    private void predictAndReport(String modelName, int modelVersion, String contentJson) {

        // Generate features TensorProto
        TensorProto.Builder featuresTensorBuilder = TensorProto.newBuilder();

        // JSON --> TensorProto
        // Build TF Proto Object from JSON
        try {
            JsonFormat.parser().ignoringUnknownFields().merge(contentJson, featuresTensorBuilder);
            featuresTensorBuilder.build();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        // Feature proto creation
        TensorProto featuresTensorProto = featuresTensorBuilder.build();

        Model.ModelSpec.Builder modelTensorBuilder = Model.ModelSpec.newBuilder().setName(modelName);
        if (modelVersion > 0) {
            com.google.protobuf.Int64Value version = com.google.protobuf.Int64Value.newBuilder().setValue(modelVersion).build();
            modelTensorBuilder.setVersion(version);
        }
        Model.ModelSpec modelSpec = modelTensorBuilder.build();
        Predict.PredictRequest request = Predict.PredictRequest.newBuilder().setModelSpec(modelSpec)
                .putInputs("data", featuresTensorProto).build();

        // Perform gRPC request
        ListenableFuture<Predict.PredictResponse> responseFuture = fStub.predict(request); //addListener({}).get();
        Futures.addCallback(responseFuture, kafkaReporter);
    }


    private void shutdown() {
        kafkaReporter.shutdown();
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }





}
