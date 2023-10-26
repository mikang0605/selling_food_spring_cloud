package com.example.user_service.pojo.dto;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;
}
