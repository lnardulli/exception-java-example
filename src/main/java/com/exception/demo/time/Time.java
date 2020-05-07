package com.exception.demo.time;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Time {

    public static Timestamp getTime() {
        return new Timestamp(System.currentTimeMillis());
    }
}
