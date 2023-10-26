package com.example.user_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.example.user_service.dao")
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(UserServiceApplication.class, args);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

}
