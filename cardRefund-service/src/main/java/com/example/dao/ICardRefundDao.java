package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.CardRefund;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICardRefundDao extends BaseMapper<CardRefund> {
    List<CardRefund> getCardRefundList(@Param("currentPage") int currentPage,
                                       @Param("pageSize") int pageSize,
                                       @Param("startDate") String startDate,
                                       @Param("endDate") String endDate,
                                       @Param("startTime") String startTime,
                                       @Param("endTime") String endTime);

    //获取总数
    int getCardRefundListNum(@Param("startDate")String startDate,
                             @Param("endDate")String endDate,
                             @Param("startTime")String startTime,
                             @Param("endTime")String endTime);
}
