package com.example.batch.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class BatchContextTest {
    
    @Test
    public void testCreateContext() {
        BatchContext context = new BatchContext("TEST_BATCH");
        assertEquals("TEST_BATCH", context.getBatchId());
        assertNotNull(context.getStartTime());
    }
    
    @Test
    public void testAttributes() {
        BatchContext context = new BatchContext("TEST_BATCH");
        
        context.setAttribute("key1", "value1");
        context.setAttribute("key2", 123);
        
        assertEquals("value1", context.getAttribute("key1"));
        assertEquals(123, context.getAttribute("key2"));
    }
}
