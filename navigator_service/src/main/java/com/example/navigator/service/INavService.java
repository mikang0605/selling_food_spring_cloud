package com.example.navigator.service;

import com.example.navigator.pojo.Navigator;

import java.util.List;

public interface INavService {
    List<Navigator> getNav();

    //给菜单排序
    List<Navigator> sortNav(List<Navigator> navList);
}
