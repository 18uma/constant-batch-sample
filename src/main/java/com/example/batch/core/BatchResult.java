package com.example.batch.core;

import java.time.LocalDateTime;

public class BatchResult {
    private final boolean success;
    private final String message;
    private final LocalDateTime endTime;
    private final int processedCount;
    private final Throwable error;
    
    private BatchResult(boolean success, String message, int processedCount, Throwable error) {
        this.success = success;
        this.message = message;
        this.endTime = LocalDateTime.now();
        this.processedCount = processedCount;
        this.error = error;
    }
    
    public static BatchResult success(int processedCount) {
        return new BatchResult(true, "Success", processedCount, null);
    }
    
    public static BatchResult success(String message, int processedCount) {
        return new BatchResult(true, message, processedCount, null);
    }
    
    public static BatchResult failure(String message, Throwable error) {
        return new BatchResult(false, message, 0, error);
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public int getProcessedCount() {
        return processedCount;
    }
    
    public Throwable getError() {
        return error;
    }
}
