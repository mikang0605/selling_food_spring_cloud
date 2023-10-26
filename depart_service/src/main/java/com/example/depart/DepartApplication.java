package com.example.depart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.depart.dao")
public class DepartApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartApplication.class, args);
    }

}
