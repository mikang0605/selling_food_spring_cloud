package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.pojo.FoodType;
import com.example.pojo.dto.Result;
import com.example.service.IFoodTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FoodTypeController {

    @Autowired
    private IFoodTypeService service;

    @GetMapping("/getlist")
    public Result getFoodTypeList(@Param("currentPage") int currentPage,
                                  @Param("pageSize") int pageSize){
        Result result = new Result();
        result.setCode(20000);
        List<FoodType> list = service.getFoodTypeList(currentPage, pageSize);
        int num = service.getFoodTypeNum();
        Map<String, Object> map =  new HashMap<>();
        map.put("list", list);
        map.put("total", num);
        result.setMessage("成功获取菜品类别列表");
        result.setData(map);
        return result;
    }

    @PostMapping("/saveorupdate")
    public Result saveOrUpdate(@Param("form") FoodType form){
        Result result = new Result();
        int count = service.getCount(form.getTypeName());
        result.setCode(20000);
        if(count > 0){
            String typeName = form.getTypeName();
            UpdateWrapper<FoodType> wrapper = new UpdateWrapper<>();
            wrapper.eq("TypeName", typeName);
            boolean i = service.update(form, wrapper);
            if(i){
                result.setMessage("修改成功");
            }else {
                result.setMessage("修改失败");
            }
        }else {
            boolean i = service.save(form);
            if(i){
                result.setMessage("添加成功");
            }else {
                result.setMessage("添加失败");
            }
        }
        return result;
    }

    @PostMapping("/del")
    public Result delFoodType(@Param("id") int id){
        Result result = new Result();
        boolean i = service.removeById(id);
        result.setCode(20000);
        if(i){
            result.setMessage("删除成功");
        }else {
            result.setMessage("删除失败");
        }
        return result;
    }

}
