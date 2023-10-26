package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.FoodType;

import java.util.List;

public interface IFoodTypeService extends IService<FoodType> {
    List<FoodType> getFoodTypeList(int currentPage, int pageSize);

    int getFoodTypeNum();

    int getCount(String typeName);
}
