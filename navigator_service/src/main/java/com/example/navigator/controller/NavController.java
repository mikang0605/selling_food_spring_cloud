package com.example.navigator.controller;

import com.example.navigator.pojo.Navigator;
import com.example.navigator.pojo.dto.Result;
import com.example.navigator.service.INavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NavController {

    @Autowired
    private INavService service;

    @GetMapping("/navlist")
    public Result getNavList(){
        List<Navigator> navList = service.sortNav(service.getNav());
        Result result = new Result();
        result.setCode(20000);
        result.setMessage("成功获取菜单目录");
        result.setData(navList);
        return result;
    }

}
