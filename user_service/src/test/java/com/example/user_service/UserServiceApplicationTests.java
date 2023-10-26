package com.example.user_service;

import com.example.user_service.dao.IUserDao;
import com.example.user_service.pojo.Toptioner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    private IUserDao dao;

    @Test
    void contextLoads() {
        Toptioner user = dao.selectById(1);
        System.out.println(user);
    }

}
