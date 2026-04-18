package com.automation.testutils;
import java.io.InputStream;
import java.util.Properties;
/**
 * ConfigReader class provides utility methods for:
 * 1. Loading configuration properties from a file
 * 2. Fetching configuration values using keys
 * This class helps in managing environment-specific data
 */
public class ConfigReader {

    private static Properties prop;

    /**
     * Loads the configuration file into memory.
     * Reads the config.properties file from the resources folder
     * and initializes the Properties object.
     * @throws RuntimeException if the config file cannot be loaded
     */
    public static void loadConfig() {
        try {
            InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (is == null) {
                throw new RuntimeException("config.properties not found in resources");
            }

            prop = new Properties();
            prop.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    /**
     * Returns value for given key.
     *
     * @param key property key
     * @return value
     */
    public static String get(String key) {
        String value = prop.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Key not found in config: " + key);
        }

        return value;
    }
}
