package com.example.batch.sample;

import com.example.batch.core.BatchResult;
import com.example.batch.util.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class CsvProcessBatchTest {
    private static final String INPUT_FILE = "test_input.csv";
    private static final String OUTPUT_FILE = "test_output.csv";
    
    @Before
    public void setup() throws IOException {
        List<String[]> records = Arrays.asList(
            new String[]{"id", "name", "value"},
            new String[]{"1", "test1", "100"},
            new String[]{"2", "test2", "200"}
        );
        FileUtil.writeCsv(INPUT_FILE, records);
    }
    
    @After
    public void cleanup() throws IOException {
        FileUtil.delete(INPUT_FILE);
        FileUtil.delete(OUTPUT_FILE);
    }
    
    @Test
    public void testExecuteBatch() throws IOException {
        CsvProcessBatch batch = new CsvProcessBatch(INPUT_FILE, OUTPUT_FILE);
        BatchResult result = batch.execute();
        
        assertTrue(result.isSuccess());
        assertEquals(3, result.getProcessedCount());
        assertTrue(FileUtil.exists(OUTPUT_FILE));
        
        List<String[]> output = FileUtil.readCsv(OUTPUT_FILE);
        assertEquals(3, output.size());
        assertEquals(4, output.get(0).length);
    }
    
    @Test
    public void testFileNotFound() {
        CsvProcessBatch batch = new CsvProcessBatch("nonexistent.csv", OUTPUT_FILE);
        BatchResult result = batch.execute();
        
        assertFalse(result.isSuccess());
    }
}
