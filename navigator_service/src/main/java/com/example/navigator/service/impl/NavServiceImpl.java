package com.example.navigator.service.impl;

import com.example.navigator.dao.INavDao;
import com.example.navigator.pojo.Navigator;
import com.example.navigator.service.INavService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavServiceImpl implements INavService {

    @Autowired
    private INavDao dao;

    @Override
    public List<Navigator> getNav() {
        return dao.selectList(null);
    }

    @Override
    public List<Navigator> sortNav(List<Navigator> navList) {
        List<Navigator> delList = new ArrayList<>();
        for(int i = 0; i < navList.size(); i++){
            Navigator nav1 = navList.get(i);
            for (Navigator nav2 : navList) {
                if (nav1.getId() == nav2.getPid()) {
                    nav1.getChildren().add(nav2);
                }
            }
            if(nav1.getChildren().size() == 0){
                delList.add(nav1);
            }
        }
        navList.removeAll(delList);
        delList.clear();
        for (int i = 0; i < navList.size(); i++){
            Navigator nav1 = navList.get(i);
            for (Navigator nav2 : navList){
                if(nav2.getPid() == nav1.getId()){
                    nav1.getChildren().removeIf(child -> child.getId() == nav2.getId());
                    nav1.getChildren().add(nav2);
                    delList.add(nav2);
                }
            }
        }
        navList.removeAll(delList);
        return navList;
    }
}
