package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static Properties getConfig(){
        Properties  props = new Properties();
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (is == null) {
                throw new IllegalStateException("config.properties not found on classpath");}
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
        return props;
    }
    }

