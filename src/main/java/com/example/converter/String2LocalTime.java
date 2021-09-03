package com.example.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class String2LocalTime implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String s) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalTime.parse(s, fmt);
    }
}
