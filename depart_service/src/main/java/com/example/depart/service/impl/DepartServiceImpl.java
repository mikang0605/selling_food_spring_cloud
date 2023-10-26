package com.example.depart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.depart.dao.IDepartDao;
import com.example.depart.pojo.Depart;
import com.example.depart.service.IDepartService;
import com.example.depart.utils.DepartComparator;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartServiceImpl implements IDepartService {

    @Autowired
    private IDepartDao dao;

    @Override
    public List<Depart> getDepart() {
        return dao.selectList(null);
    }

    @Override
    public List<Depart> sortDeparts(List<Depart> departs) {
        departs.sort(new DepartComparator());
        List<String> longList = departs.stream().map(Depart::getCode).collect(Collectors.toList());
        int longSize = 0;
        for(String code : longList){
            if(code.length() > longSize){
                longSize = code.length();
            }
        }
        if(longSize <= 2){
            return departs;
        }
        List<Depart> delList = new ArrayList<>();
        for(Depart depart: departs){
            if(depart.getCode().length() == longSize){
                String pCode = depart.getCode().substring(0, depart.getCode().length() - 2);
                for(Depart parent: departs){
                    if(parent.getCode().equals(pCode)){
                        parent.getChildren().add(depart);
                    }
                }
                delList.add(depart);
            }
        }
        departs.removeAll(delList);
        sortDeparts(departs);
        return departs;
    }

    @Override
    public int updateName(String name, String code) {
        Depart depart = new Depart();
        depart.setName(name);
        UpdateWrapper<Depart> wrapper = new UpdateWrapper<>();
        wrapper.eq("code", code);
        return dao.update(depart, wrapper);
    }

    @Override
    public int addDepart(String name, String code) {
        Depart depart = new Depart();
        depart.setCode(code);
        depart.setName(name);
        return dao.insert(depart);
    }

    @Override
    public int havePeople(String code) {
        QueryWrapper<Depart> wrapper = new QueryWrapper<>();
        wrapper.eq("code", code);
        return Math.toIntExact(dao.selectCount(wrapper));
    }

    @Override
    public int delDepart(String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        return dao.deleteByMap(map);
    }

    @Override
    public Depart getDepartByCode(String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        List<Depart> depart = dao.selectByMap(map);
        return depart.get(0);
    }
}
