package com.example.batch.util;

import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class DateTimeUtilTest {
    
    @Test
    public void testFormatDate() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        String formatted = DateTimeUtil.formatDate(date);
        assertEquals("20240115", formatted);
    }
    
    @Test
    public void testParseDate() {
        LocalDate date = DateTimeUtil.parseDate("20240115");
        assertEquals(LocalDate.of(2024, 1, 15), date);
    }
    
    @Test
    public void testGetCurrentDate() {
        String current = DateTimeUtil.getCurrentDate();
        assertNotNull(current);
        assertEquals(8, current.length());
    }
    
    @Test
    public void testGetCurrentDateTime() {
        String current = DateTimeUtil.getCurrentDateTime();
        assertNotNull(current);
        assertEquals(15, current.length());
    }
}
