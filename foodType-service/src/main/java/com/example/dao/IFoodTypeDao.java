package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.FoodType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodTypeDao extends BaseMapper<FoodType> {

    List<FoodType> getFoodTypeList(@Param("currentPage") int currentPage,
                                   @Param("pageSize") int pageSize);

}
