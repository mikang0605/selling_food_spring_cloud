package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.IFoodMenuDao;
import com.example.pojo.FoodMenu;
import com.example.service.IFoodMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodMenuImpl extends ServiceImpl<IFoodMenuDao, FoodMenu> implements IFoodMenuService {

    @Autowired
    private IFoodMenuDao dao;

    @Override
    public List<FoodMenu> getFoodMenuList(int currentPage, int pageSize, String stype, String menutype, String menu_name) {
        return dao.getFoodMenuList(currentPage, pageSize, stype, menutype, menu_name);
    }

    @Override
    public int getFoodMenuNum(String stype, String menutype, String menu_name) {
        return dao.getFoodMenuNum(stype, menutype, menu_name);
    }

    @Override
    public int getNoRepeat(String number) {
        return dao.getNoRepeat(number);
    }
}
