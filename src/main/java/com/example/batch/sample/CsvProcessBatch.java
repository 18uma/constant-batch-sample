package com.example.batch.sample;

import com.example.batch.core.BatchContext;
import com.example.batch.core.BatchExecutor;
import com.example.batch.core.BatchResult;
import com.example.batch.util.DateTimeUtil;
import com.example.batch.util.FileUtil;

import java.util.List;

public class CsvProcessBatch extends BatchExecutor {
    private final String inputFile;
    private final String outputFile;
    
    public CsvProcessBatch(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }
    
    @Override
    protected String getBatchId() {
        return "CSV_PROCESS_BATCH";
    }
    
    @Override
    protected void preProcess(BatchContext context) throws Exception {
        logger.info("Input file: {}", inputFile);
        logger.info("Output file: {}", outputFile);
        
        if (!FileUtil.exists(inputFile)) {
            throw new IllegalArgumentException("Input file not found: " + inputFile);
        }
    }
    
    @Override
    protected BatchResult process(BatchContext context) throws Exception {
        List<String[]> records = FileUtil.readCsv(inputFile);
        
        // 業務ロジック: 各レコードに処理日時を追加
        for (String[] record : records) {
            String[] newRecord = new String[record.length + 1];
            System.arraycopy(record, 0, newRecord, 0, record.length);
            newRecord[record.length] = DateTimeUtil.getCurrentDateTime();
            
            // 元の配列を置き換え
            for (int i = 0; i < records.size(); i++) {
                if (records.get(i) == record) {
                    records.set(i, newRecord);
                    break;
                }
            }
        }
        
        FileUtil.writeCsv(outputFile, records);
        
        return BatchResult.success("Processed successfully", records.size());
    }
    
    @Override
    protected void postProcess(BatchContext context, BatchResult result) throws Exception {
        if (result.isSuccess()) {
            logger.info("Processed {} records", result.getProcessedCount());
        }
    }
}
