package com.example.batch.sample;

import com.example.batch.core.BatchResult;

public class SampleBatchMain {
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java SampleBatchMain <input_file> <output_file>");
            System.exit(1);
        }
        
        String inputFile = args[0];
        String outputFile = args[1];
        
        CsvProcessBatch batch = new CsvProcessBatch(inputFile, outputFile);
        BatchResult result = batch.execute();
        
        if (result.isSuccess()) {
            System.out.println("Batch completed successfully");
            System.exit(0);
        } else {
            System.err.println("Batch failed: " + result.getMessage());
            System.exit(1);
        }
    }
}
