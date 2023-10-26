package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Loss;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILossDao extends BaseMapper<Loss> {

    //获取全部挂失信息
    List<Loss> getAllLoss(@Param("depart") String depart,
                          @Param("currentPage") int currentPage,
                          @Param("pageSize") int pageSize,
                          @Param("userid") Integer userid,
                          @Param("lossDate") String lossDate,
                          @Param("unhookDate") String unhookDate);

    //获取挂失信息数目
    int getLossNum(@Param("depart") String depart,
                   @Param("userid") Integer userid,
                   @Param("lossDate") String lossDate,
                   @Param("unhookDate") String unhookDate);

}
