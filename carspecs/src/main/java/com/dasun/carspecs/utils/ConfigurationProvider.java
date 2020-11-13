package com.dasun.carspecs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProvider {

    private static Properties properties = null;

    private ConfigurationProvider() {
    }

    /**
     * Load configurations from property file
     */
    private static void loadConfigurations() {
        properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = ConfigurationProvider.class.
                    getResourceAsStream("/configurations.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred when reading configurations " + e);
        }
    }

    /**
     * Get Configuration by key
     *
     * @param key key
     * @return configuration value for given key
     */
    public static String getConfigurationByKey(String key) {
        loadConfigurations();
        return properties.getProperty(key);
    }
}
