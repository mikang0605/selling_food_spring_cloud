package com.example.depart.service;

import com.example.depart.pojo.Depart;

import java.util.List;

public interface IDepartService {
    //获取全部部门信息
    List<Depart> getDepart();

    //整理部门信息
    List<Depart> sortDeparts(List<Depart> departs);

    //修改部门名称
    int updateName(String name, String code);

    //添加新部门
    int addDepart(String name, String code);

    //验证部门里面是否有人
    int havePeople(String code);

    //删除部门
    int delDepart(String code);

    //通过code查询部门
    Depart getDepartByCode(String code);
}
