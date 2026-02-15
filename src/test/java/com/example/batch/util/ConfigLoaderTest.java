package com.example.batch.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConfigLoaderTest {
    
    @Test
    public void testLoadConfig() {
        ConfigLoader config = new ConfigLoader("application.yml");
        assertNotNull(config);
    }
    
    @Test
    public void testGetString() {
        ConfigLoader config = new ConfigLoader("application.yml");
        String name = config.getString("batch.name");
        assertEquals("constant_batch", name);
    }
    
    @Test
    public void testGetNestedValue() {
        ConfigLoader config = new ConfigLoader("application.yml");
        String level = config.getString("logging.level");
        assertEquals("INFO", level);
    }
}
