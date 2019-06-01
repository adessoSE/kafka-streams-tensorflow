package de.adesso.kafkaml.mysql;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import de.adesso.kafkaml.conf.ConfigReader;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class MySQLKafkaAdapter {

    // Kafka Variables
    private final String inputTopic;
    private final String bootstrapServers;
    //MySQL Variables
    private final String databaseURL;
    private final String databaseUser;
    private final String databasePassword;

    public MySQLKafkaAdapter(Map<String, String> confIn){
        inputTopic = confIn.get(ConfigReader.KAFKA_INPUT_TOPIC);
        bootstrapServers = confIn.get(ConfigReader.KAFKA_BOOTSTRAP_SERVERS);
        databaseURL = confIn.get(ConfigReader.MYSQL_URI);
        databaseUser = confIn.get(ConfigReader.MYSQL_USER);
        databasePassword = confIn.get(ConfigReader.MYSQL_PASSWORD);
    }

    public void runKafka(){
        Properties kafkaStreamsConfig = generateKafkaConfig();
        System.out.println("Generated Kafka Config");
        final StreamsBuilder builder = new StreamsBuilder();
        System.out.println("Initialized Streams Builder");
        try (ConnectionSource source = new JdbcConnectionSource(String.format("%s?user=%s&password=%s", databaseURL,
                databaseUser, databasePassword))){

            Dao<Transaction, Integer> transactionDao = DaoManager.createDao(source, Transaction.class);

            // Key is not relevant
            final KStream<String, String> inputLines = builder.stream(inputTopic);
            inputLines.foreach((key, value) -> {
                try {
                    transactionDao.create(new Transaction(value));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Defined stream");

            startKafkaStream(kafkaStreamsConfig, builder);
        }catch (Exception e) {
            System.out.println("Could not connect to the MySQL database");
            e.printStackTrace();
        }
    }

    private Properties generateKafkaConfig() {
        // Configure Kafka Streams Application
        final Properties streamsConfiguration = new Properties();
        // Give the Streams application a unique name. The name must be unique in the Kafka cluster
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG,
                "kafka-streams-tensorflow-creditscore");
        // Where to find Kafka broker(s).
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // Specify default (de)serializers for record keys and for record values
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return streamsConfiguration;
    }

    private void startKafkaStream(Properties kafkaStreamsConfig, StreamsBuilder builder) {
        // Start Kafka Streams Application to process new incoming images from the Input
        // Initialize streams application
        System.out.println("Initializing Streams");
        final KafkaStreams streams = new KafkaStreams(builder.build(), kafkaStreamsConfig);

        System.out.println("Starting Streams");
        streams.cleanUp();
        streams.start();

        System.out.println("\nListening to input at topic " + inputTopic);
        System.out.println("Credit scoring is running...");

        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka
        // Streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

}
