package de.adesso.kafkaml.conf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class ConfigReader {

    public final Map<String, String> conf = new HashMap<>();

    // Tensorflow Model
    public static final String TENSORFLOW_MODEL_NAME = "tf.model.name";
    public static final String TENSORFLOW_MODEL_VERSION = "tf.model.version";
    // Tensorflow Serving
    public static final String TENSORFLOW_SERVING_HOST = "tf.serving.host";
    public static final String TENSORFLOW_SERVING_PORT = "tf.serving.port";
    // Kafka
    public static final String KAFKA_INPUT_TOPIC = "kafka.topic.input";
    public static final String KAFKA_OUTPUT_TOPIC = "kafka.topic.output";
    public static final String KAFKA_BOOTSTRAP_SERVERS = "kafka.bootstrap.servers";
    // MySQL
    public static final String MYSQL_URI = "mysql.url";
    public static final String MYSQL_USER = "mysql.user";
    public static final String MYSQL_PASSWORD = "mysql.password";

    public ConfigReader(String configFile, String[] additional_args) {
        this.conf.putAll(readConfig(configFile, additional_args));
        // Print config after initialization for better debugging
        this.conf.forEach((k, v) -> System.out.println("Setting Key=|" + k + "| Value=|" + v + "|"));
    }


    private Map<String, String> readConfig(String fileName, String[] additional_args) {
        // Read from config file
        Map<String, String> confContent = readFile(fileName);
        // Apply additional arguments
        Arrays.asList(additional_args).forEach(modifier -> {
            String[] kv = extractProperty(modifier);
            confContent.put(kv[0], kv[1]);
        });
        return confContent;
    }


    /**
     * Reads a file. For each line, this function does the
     * following: <br>
     * - Trim the line (as String) <br>
     * - If the line is not empty: <br>
     * 	insert into internal data structure
     * 	@param fileName Path of the config file
     * 	@return A map storing the configuration in key/value format
     */
    private Map<String, String> readFile(String fileName) {
        String line;
        Map<String, String> confContent = new HashMap<>();
        // Initialize readers
        try (
                FileInputStream fs = new FileInputStream(fileName);
                BufferedReader bf = new BufferedReader(new InputStreamReader(fs))
        ) {
            // Read lines and parse them accordingly
            while ((line = bf.readLine()) != null) {
                line = line.trim();
                // for each line
                // ignore empty lines
                // allow comments starting with #
                if (!(line.isEmpty() || line.startsWith("#"))) {
                    String[] kv = extractProperty(line);
                    confContent.put(kv[0], kv[1]);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }
        return confContent;
    }


    private String[] extractProperty(String line) {
        // key value pairs are separated by "="
        int idx = line.indexOf("=");
        String key = line.substring(0, idx).toLowerCase();
        String value = line.substring(idx + 1, line.length());
        return new String[]{key, value};
    }

}
