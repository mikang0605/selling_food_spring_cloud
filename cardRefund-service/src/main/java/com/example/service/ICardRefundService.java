package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.CardRefund;

import java.util.List;

public interface ICardRefundService extends IService<CardRefund> {
    List<CardRefund> getCardRefundList(int currentPage,
                                       int pageSize,
                                       String startDate,
                                       String endDate,
                                       String startTime,
                                       String endTime);

    int getCardRefundListNum(String startDate, String endDate, String startTime, String endTime);
}
