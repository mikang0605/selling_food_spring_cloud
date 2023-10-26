package com.example.user_service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user_service.pojo.Toptioner;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends BaseMapper<Toptioner> {
}
