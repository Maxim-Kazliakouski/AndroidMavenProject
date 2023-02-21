package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmulatorPropertyReader {
    private static String emulatorPropertiesPath = "configs/emulator.properties";
    private static volatile Properties properties;
    private static InputStream inputStream;

    private EmulatorPropertyReader() {
    }

    private static String getCorrectPath() {
        if (emulatorPropertiesPath.charAt(0) != '/')
            emulatorPropertiesPath = "/" + emulatorPropertiesPath;
        return emulatorPropertiesPath;
    }

    public static Properties readProperties() {
        properties = new Properties();
        try {
            inputStream = EmulatorPropertyReader.class.getResourceAsStream(getCorrectPath());
            if (inputStream != null)
                properties.load(inputStream);
        } catch (Exception ex) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (properties.getProperty("config_file") != null) {
            Properties additionalProperties = getProperties(properties.getProperty("config_file"));
            properties.putAll(additionalProperties);
        }
        return properties;
    }

    private static Properties loadProperties() {
        return properties != null ? properties : readProperties();
    }

    public static Properties getProperties(String path) {
        emulatorPropertiesPath = path;
        return readProperties();
    }

    public static String getProperty(String propertyName) {
        return loadProperties().getProperty(propertyName);
    }
}