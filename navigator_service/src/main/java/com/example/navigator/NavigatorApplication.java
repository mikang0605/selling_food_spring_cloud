package com.example.navigator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.navigator.dao")
public class NavigatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NavigatorApplication.class, args);
    }

}
