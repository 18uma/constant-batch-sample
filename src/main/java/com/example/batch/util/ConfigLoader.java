package com.example.batch.util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigLoader {
    private final Map<String, Object> config;
    
    public ConfigLoader(String configFile) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                throw new IllegalArgumentException("Config file not found: " + configFile);
            }
            Yaml yaml = new Yaml();
            this.config = yaml.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }
    
    public String getString(String key) {
        return (String) getNestedValue(key);
    }
    
    public Integer getInt(String key) {
        Object value = getNestedValue(key);
        return value instanceof Integer ? (Integer) value : Integer.parseInt(value.toString());
    }
    
    public Boolean getBoolean(String key) {
        Object value = getNestedValue(key);
        return value instanceof Boolean ? (Boolean) value : Boolean.parseBoolean(value.toString());
    }
    
    private Object getNestedValue(String key) {
        String[] keys = key.split("\\.");
        Object current = config;
        
        for (String k : keys) {
            if (current instanceof Map) {
                current = ((Map<?, ?>) current).get(k);
            } else {
                return null;
            }
        }
        return current;
    }
}
