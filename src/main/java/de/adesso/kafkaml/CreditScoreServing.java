package de.adesso.kafkaml;

import de.adesso.kafkaml.conf.ConfigReader;
import de.adesso.kafkaml.mysql.MySQLKafkaAdapter;

import java.util.Arrays;

public class CreditScoreServing {

    public static void main(String[] args){
        // Read config
        ConfigReader confReader = new ConfigReader(args[0], Arrays.copyOfRange(args, 1, args.length));
        // Run Kafka
        MySQLKafkaAdapter kafkaServing = new MySQLKafkaAdapter(confReader.conf);
        kafkaServing.runKafka();
    }
}
