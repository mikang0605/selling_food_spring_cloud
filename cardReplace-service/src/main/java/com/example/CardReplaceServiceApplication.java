package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.dao")
@EnableFeignClients(basePackages = "com.example.clients")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class CardReplaceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardReplaceServiceApplication.class, args);
    }

}
