package de.adesso.kafkaml;

import de.adesso.kafkaml.conf.ConfigReader;
import de.adesso.kafkaml.grpc.GRPCKafkaAdapter;

import java.util.Arrays;

public class FraudModelServing {

    public static void main(String[] args) {
        // Read config
        ConfigReader confReader = new ConfigReader(args[0], Arrays.copyOfRange(args, 1, args.length));
        // Run Kafka
        GRPCKafkaAdapter kafkaServing = new GRPCKafkaAdapter(confReader.conf);
        kafkaServing.runKafka();

    }
}
