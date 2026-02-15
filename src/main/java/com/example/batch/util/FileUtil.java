package com.example.batch.util;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    
    public static List<String> readLines(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
    
    public static void writeLines(String filePath, List<String> lines) throws IOException {
        Files.write(Paths.get(filePath), lines);
    }
    
    public static void move(String source, String target) throws IOException {
        Files.move(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
    }
    
    public static void delete(String filePath) throws IOException {
        Files.deleteIfExists(Paths.get(filePath));
    }
    
    public static boolean exists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }
    
    public static List<String[]> readCsv(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        List<String> lines = readLines(filePath);
        
        for (String line : lines) {
            records.add(line.split(",", -1));
        }
        return records;
    }
    
    public static void writeCsv(String filePath, List<String[]> records) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String[] record : records) {
            lines.add(String.join(",", record));
        }
        writeLines(filePath, lines);
    }
}
