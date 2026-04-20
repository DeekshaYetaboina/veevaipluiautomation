package veeva.ipl.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * TestDataUtils class provides utility methods for:
 * 1. Reading test data from JSON files
 * 2. Capturing screenshots during test execution
 * <p>
 * This class supports data-driven testing and reusable helper methods
 * for automation frameworks.
 */
public class TestDataUtils {
    /**
     * Generates the full file path for a given JSON file name.
     *
     * @param jsondata the name of the JSON file (without extension)
     * @return the complete file path to the JSON file
     */
    public String getPath(String jsondata) {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\data\\" + jsondata + ".json";
    }

    /**
     * Reads a single string value from a JSON file based on the given key.
     *
     * @param filename the full path of the JSON file
     * @param key      the key whose value needs to be fetched
     * @return the string value associated with the key
     * @throws IOException if there is an issue reading the file
     */
    public String getJsonString(String filename, String key) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(new File(filename), Map.class);
        return (String) data.get(key);
    }

    /**
     * Reads a list of string values from a JSON file based on the given key.
     *
     * @param filename the full path of the JSON file
     * @param key      the key whose list of values needs to be fetched
     * @return a list of strings associated with the key
     * @throws IOException if there is an issue reading the file
     */
    public List<String> getJsonData(String filename, String key) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<String>> data = mapper.readValue(new File(filename), Map.class);
        return data.get(key);
    }
}