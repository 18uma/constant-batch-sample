package com.example.batch.util;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class LockManagerTest {
    private static final String TEST_BATCH_ID = "TEST_BATCH";
    private final LockManager lockManager = new LockManager();
    
    @After
    public void cleanup() {
        lockManager.releaseLock(TEST_BATCH_ID);
    }
    
    @Test
    public void testAcquireLock() {
        assertTrue(lockManager.acquireLock(TEST_BATCH_ID));
    }
    
    @Test
    public void testDuplicateLock() {
        assertTrue(lockManager.acquireLock(TEST_BATCH_ID));
        assertFalse(lockManager.acquireLock(TEST_BATCH_ID));
    }
    
    @Test
    public void testReleaseLock() {
        lockManager.acquireLock(TEST_BATCH_ID);
        lockManager.releaseLock(TEST_BATCH_ID);
        assertTrue(lockManager.acquireLock(TEST_BATCH_ID));
    }
}
