package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.FoodMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodMenuDao extends BaseMapper<FoodMenu> {

    List<FoodMenu> getFoodMenuList(@Param("currentPage") int currentPage,
                                   @Param("pageSize") int pageSize,
                                   @Param("stype") String stype,
                                   @Param("menutype") String menutype,
                                   @Param("menu_name") String menu_name);

    int getFoodMenuNum(@Param("stype") String stype,
                       @Param("menutype") String menutype,
                       @Param("menu_name") String menu_name);

    int getNoRepeat(String number);

}
