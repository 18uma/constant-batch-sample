package com.example.batch.core;

public interface TransactionManager {
    
    void begin() throws Exception;
    
    void commit() throws Exception;
    
    void rollback() throws Exception;
    
    <T> T executeInTransaction(TransactionCallback<T> callback) throws Exception;
    
    @FunctionalInterface
    interface TransactionCallback<T> {
        T doInTransaction() throws Exception;
    }
}
