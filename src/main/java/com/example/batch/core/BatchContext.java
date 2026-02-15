package com.example.batch.core;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class BatchContext {
    private final String batchId;
    private final LocalDateTime startTime;
    private final Map<String, Object> attributes;
    
    public BatchContext(String batchId) {
        this.batchId = batchId;
        this.startTime = LocalDateTime.now();
        this.attributes = new HashMap<>();
    }
    
    public String getBatchId() {
        return batchId;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }
    
    public Object getAttribute(String key) {
        return attributes.get(key);
    }
    
    public Map<String, Object> getAttributes() {
        return new HashMap<>(attributes);
    }
}
