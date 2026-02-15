package com.example.batch.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LockManager {
    private static final String LOCK_DIR = System.getProperty("java.io.tmpdir") + "/batch_locks";
    
    public LockManager() {
        try {
            Files.createDirectories(Paths.get(LOCK_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create lock directory", e);
        }
    }
    
    public boolean acquireLock(String batchId) {
        File lockFile = getLockFile(batchId);
        try {
            return lockFile.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }
    
    public void releaseLock(String batchId) {
        File lockFile = getLockFile(batchId);
        lockFile.delete();
    }
    
    private File getLockFile(String batchId) {
        return new File(LOCK_DIR, batchId + ".lock");
    }
}
