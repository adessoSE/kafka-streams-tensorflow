package de.adesso.kafkaml.grpc;

import com.google.common.util.concurrent.FutureCallback;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import de.adesso.kafkaml.conf.ConfigReader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.tensorflow.framework.TensorProto;
import tensorflow.serving.Predict;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class TensorflowKafkaCallback implements FutureCallback<Predict.PredictResponse> {

    private final String bootstrapServers;
    private final String outputTopic;

    private Producer<String, String> producer = null;

    public TensorflowKafkaCallback(Map<String, String> confIn) {
        bootstrapServers = confIn.get(ConfigReader.KAFKA_BOOTSTRAP_SERVERS);
        outputTopic = confIn.get(ConfigReader.KAFKA_OUTPUT_TOPIC);
    }

    @Override
    public void onSuccess(@Nullable Predict.PredictResponse predictResponse) {
        final Map<String, TensorProto> output = Objects.requireNonNull(predictResponse).getOutputsMap();
        output.forEach((key, proto) -> {
            // TensorProto --> JSON
            // Get JSON from TensorProto and send to Kafka topic
            try {
                String json = JsonFormat.printer().print(proto);
                sendMessage(outputTopic, key, json);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace(System.err);
            }
        });
    }

    @Override
    public void onFailure(@Nullable Throwable throwable) {
        Objects.requireNonNull(throwable).printStackTrace(System.err);
    }


    // Asynchronous usage of Kafka producer


    private Producer<String, String> createProducer(String bootstrapServers) {
        // Initialize Producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "kafka-tensorflow-producer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    private void sendMessage(String topic, String key, String value) {
        // Lazy initialization - Initialize producer as soon as it is required
        if (producer == null) {
            // Get configuration and run initialization
            producer = createProducer(bootstrapServers);
        }
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, key, value);
        // Avoid to .get-call in order to keep it asynchronously
        // Define Record to send and callback for acknowledgement
        producer.send(record, (metadata, e) -> {
            if (metadata != null) {
                System.out.printf("Sent record(key=%s value=%s) " +
                                "meta(partition=%d, offset=%d)\n",
                        record.key(), record.value(), metadata.partition(),
                        metadata.offset());
            } else {
                e.printStackTrace();
            }
        });
    }

    public void shutdown() {
        if (producer != null) {
            producer.flush();
            producer.close();
        }
    }
}
