package veeva.ipl.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReader class provides utility methods for:
 * 1. Loading configuration properties from a file
 * 2. Fetching configuration values using keys
 * This class helps in managing environment-specific data
 */
public class ConfigReader {
    private static final Logger log = LogManager.getLogger(ConfigReader.class);
    private static Properties prop;

    /**
     * Loads the configuration file into memory.
     * Reads the config.properties file from the resources folder
     * and initializes the Properties object.
     */
    public static void loadConfig() {
        try {
            log.info("Loading config.properties file...");
            InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (is == null) {
                log.error("config.properties not found in resources folder");
                throw new RuntimeException("config.properties not found in resources");
            }
            prop = new Properties();
            prop.load(is);
            log.info("Config file loaded successfully");
        } catch (Exception e) {
            log.error("Failed to load config file", e);
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
            log.error("Key not found in config: {}", key);
            throw new RuntimeException("Key not found in config: " + key);
        }
        log.info("Fetching config value for key: {} -> {}", key, value);
        return value;
    }
}