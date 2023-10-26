package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.FoodMenu;

import java.util.List;

public interface IFoodMenuService extends IService<FoodMenu> {

    List<FoodMenu> getFoodMenuList(int currentPage, int pageSize, String stype, String menutype, String menu_name);

    int getFoodMenuNum(String stype, String menutype, String menu_name);

    int getNoRepeat(String number);

}
