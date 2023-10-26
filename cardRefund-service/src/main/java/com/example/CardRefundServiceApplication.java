package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example.clients")
@MapperScan("com.example.dao")
public class CardRefundServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardRefundServiceApplication.class, args);
    }

}
