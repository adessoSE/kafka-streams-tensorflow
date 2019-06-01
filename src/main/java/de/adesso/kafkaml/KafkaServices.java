package de.adesso.kafkaml;

import de.adesso.kafkaml.conf.ConfigReader;
import de.adesso.kafkaml.grpc.GRPCKafkaAdapter;
import de.adesso.kafkaml.mysql.MySQLKafkaAdapter;

import java.util.Arrays;

public class KafkaServices {

    public static void main(String[] args) {
        ConfigReader confReader = new ConfigReader(args[0], Arrays.copyOfRange(args, 1, args.length));

        MySQLKafkaAdapter kafkaMySQLServing = new MySQLKafkaAdapter(confReader.conf);
        kafkaMySQLServing.runKafka();

        GRPCKafkaAdapter kafkaGRPCServing = new GRPCKafkaAdapter(confReader.conf);
        kafkaGRPCServing.runKafka();
    }
}
