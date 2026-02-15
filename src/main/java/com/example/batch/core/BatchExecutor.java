package com.example.batch.core;

import com.example.batch.util.LockManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BatchExecutor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final LockManager lockManager;
    
    public BatchExecutor() {
        this.lockManager = new LockManager();
    }
    
    public final BatchResult execute() {
        BatchContext context = new BatchContext(getBatchId());
        
        if (!lockManager.acquireLock(getBatchId())) {
            logger.warn("Batch {} is already running", getBatchId());
            return BatchResult.failure("Already running", null);
        }
        
        try {
            logger.info("Batch {} started", getBatchId());
            
            preProcess(context);
            BatchResult result = process(context);
            postProcess(context, result);
            
            logger.info("Batch {} completed: {}", getBatchId(), result.getMessage());
            return result;
            
        } catch (Exception e) {
            logger.error("Batch {} failed", getBatchId(), e);
            return BatchResult.failure(e.getMessage(), e);
        } finally {
            lockManager.releaseLock(getBatchId());
        }
    }
    
    protected abstract String getBatchId();
    
    protected abstract BatchResult process(BatchContext context) throws Exception;
    
    protected void preProcess(BatchContext context) throws Exception {
        // Override if needed
    }
    
    protected void postProcess(BatchContext context, BatchResult result) throws Exception {
        // Override if needed
    }
}
