package com.example.depart.controller;

import com.example.depart.pojo.Depart;
import com.example.depart.pojo.dto.Result;
import com.example.depart.service.IDepartService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartController {

    @Autowired
    private IDepartService service;

    @GetMapping("getdepartbycode")
    public Depart getDepartByCode(String code){
        return service.getDepartByCode(code);
    }

    @GetMapping("/getdepart")
    public List<Depart> getDepart(){
        return service.getDepart();
    }

    @PostMapping("/sortdepart")
    public List<Depart> sortDepart(@Param("departs") List<Depart> departs){
        return service.sortDeparts(departs);
    }

    @PostMapping("/add")
    public int add(@Param("name") String name, @Param("code") String code){
        return service.addDepart(name, code);
    }

    @GetMapping("/getdeparts")
    public Result getDeparts(){
        Result result = new Result();
        List<Depart> departs = service.getDepart();
        List<Depart> sortList = new ArrayList<>();
        if(departs.size() != 0){
            sortList = service.sortDeparts(departs);
        }
        Depart all = new Depart();
        all.setName("全部");
        all.setChildren(sortList);
        all.setCode("all");
        result.setCode(20000);
        result.setMessage("成功获取部门信息");
        result.setData(all);
        return result;
    }

    @PostMapping("/editdepart")
    public Result editDepart(String name, String code ){
        Result result = new Result();
        int i = service.updateName(name, code);
        result.setCode(20000);
        if(i > 0){
            result.setMessage("修改信息成功");
        }else{
            result.setMessage("修改信息失败");
        }
        return result;
    }

    @PostMapping("adddepart")
    public Result addDepart(String name, String code){
        Result result = new Result();
        int i = service.addDepart(name, code);
        result.setCode(20000);
        if(i > 0){
            result.setMessage("添加成功");
        }else{
            result.setMessage("添加失败");
        }
        return result;
    }

    @PostMapping("deldepart")
    public Result delDepart(String code){
        Result result = new Result();
        result.setCode(20000);
        int people = service.havePeople(code);
        if(people > 0){
            result.setMessage("部门里有人，无法删除");
        }else {
            int delDepart = service.delDepart(code);
            if(delDepart > 0){
                result.setMessage("删除成功");
            }else {
                result.setMessage("删除失败");
            }
        }
        return result;
    }

}
