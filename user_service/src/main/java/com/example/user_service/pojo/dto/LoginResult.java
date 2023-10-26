package com.example.user_service.pojo.dto;

import com.example.user_service.pojo.Toptioner;
import lombok.Data;

@Data
public class LoginResult {
    private Toptioner user;
    private String token;
}
