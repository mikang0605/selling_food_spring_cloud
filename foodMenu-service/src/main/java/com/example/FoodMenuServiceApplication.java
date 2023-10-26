package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.dao")
@Configuration
public class FoodMenuServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodMenuServiceApplication.class, args);
    }

}
