package com.example.batch.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class FileUtilTest {
    private static final String TEST_FILE = "test_file.txt";
    private static final String TEST_CSV = "test_file.csv";
    
    @After
    public void cleanup() throws IOException {
        FileUtil.delete(TEST_FILE);
        FileUtil.delete(TEST_CSV);
    }
    
    @Test
    public void testReadWriteLines() throws IOException {
        List<String> lines = Arrays.asList("line1", "line2", "line3");
        FileUtil.writeLines(TEST_FILE, lines);
        
        List<String> read = FileUtil.readLines(TEST_FILE);
        assertEquals(lines, read);
    }
    
    @Test
    public void testExists() throws IOException {
        assertFalse(FileUtil.exists(TEST_FILE));
        
        FileUtil.writeLines(TEST_FILE, Arrays.asList("test"));
        assertTrue(FileUtil.exists(TEST_FILE));
    }
    
    @Test
    public void testReadWriteCsv() throws IOException {
        List<String[]> records = Arrays.asList(
            new String[]{"id", "name", "value"},
            new String[]{"1", "test1", "100"},
            new String[]{"2", "test2", "200"}
        );
        
        FileUtil.writeCsv(TEST_CSV, records);
        List<String[]> read = FileUtil.readCsv(TEST_CSV);
        
        assertEquals(records.size(), read.size());
        assertArrayEquals(records.get(0), read.get(0));
    }
}
