package com.automation.testutils;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

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

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
