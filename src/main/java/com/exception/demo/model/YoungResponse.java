package com.exception.demo.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YoungResponse {
    private Timestamp timestamp;
    private String message;
    private Integer statusCode;
    private List<Young> young;
}