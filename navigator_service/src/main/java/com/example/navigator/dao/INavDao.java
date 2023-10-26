package com.example.navigator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.navigator.pojo.Navigator;
import org.springframework.stereotype.Repository;

@Repository
public interface INavDao extends BaseMapper<Navigator> {
}
