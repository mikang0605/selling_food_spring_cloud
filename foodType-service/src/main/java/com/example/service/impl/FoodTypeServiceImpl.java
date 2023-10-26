package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.IFoodTypeDao;
import com.example.pojo.FoodType;
import com.example.service.IFoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTypeServiceImpl extends ServiceImpl<IFoodTypeDao, FoodType> implements IFoodTypeService {

    @Autowired
    private IFoodTypeDao dao;

    @Override
    public List<FoodType> getFoodTypeList(int currentPage, int pageSize) {
        return dao.getFoodTypeList(currentPage, pageSize);
    }

    @Override
    public int getFoodTypeNum() {
        return Math.toIntExact(dao.selectCount(null));
    }

    @Override
    public int getCount(String typeName) {
        QueryWrapper<FoodType> wrapper = new QueryWrapper<>();
        wrapper.eq("TypeName", typeName);
        Long count = dao.selectCount(wrapper);
        return Math.toIntExact(count);
    }
}
