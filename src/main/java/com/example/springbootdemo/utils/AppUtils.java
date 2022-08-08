package com.example.springbootdemo.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static String timestampToString(Timestamp date) {
        return formatter.format(date.toLocalDateTime());
    }

    public static Timestamp stringToTimestamp(String date) {
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(date));
        return Timestamp.valueOf(localDateTime);
    }

}
