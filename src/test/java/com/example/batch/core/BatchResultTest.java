package com.example.batch.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class BatchResultTest {
    
    @Test
    public void testSuccessResult() {
        BatchResult result = BatchResult.success(100);
        assertTrue(result.isSuccess());
        assertEquals(100, result.getProcessedCount());
        assertNotNull(result.getEndTime());
    }
    
    @Test
    public void testSuccessResultWithMessage() {
        BatchResult result = BatchResult.success("Completed", 50);
        assertTrue(result.isSuccess());
        assertEquals("Completed", result.getMessage());
        assertEquals(50, result.getProcessedCount());
    }
    
    @Test
    public void testFailureResult() {
        Exception error = new Exception("Test error");
        BatchResult result = BatchResult.failure("Failed", error);
        
        assertFalse(result.isSuccess());
        assertEquals("Failed", result.getMessage());
        assertEquals(error, result.getError());
        assertEquals(0, result.getProcessedCount());
    }
}
