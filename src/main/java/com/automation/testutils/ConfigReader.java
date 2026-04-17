package com.automation.testutils;
import java.io.FileInputStream;
import java.util.Properties;
/**
 * ConfigReader class provides utility methods for:
 * 1. Loading configuration properties from a file
 * 2. Fetching configuration values using keys
 *
 * This class helps in managing environment-specific data
 */
public class ConfigReader {
    /**
     * Properties object to store key-value pairs from config file.
     */
    private static Properties prop;
    /**
     * Loads the configuration file into memory.
     *
     * Reads the config.properties file from the resources folder
     * and initializes the Properties object.
     *
     * @throws RuntimeException if the config file cannot be loaded
     */
    public static void loadConfig() {
        try {
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir")
                            + "/src/main/java/com/automation/resources/config/config.properties"
            );

            prop = new Properties();
            prop.load(fis);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file");
        }
    }
    /**
     * Retrieves the value associated with the given key
     * from the loaded configuration.
     *
     * @param key the property key to fetch
     * @return the value corresponding to the given key
     */
    public static String get(String key) {
        return prop.getProperty(key);
    }
}
