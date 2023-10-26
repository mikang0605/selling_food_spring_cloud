package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.ICardRefundDao;
import com.example.pojo.CardRefund;
import com.example.service.ICardRefundService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardRefundServiceImpl extends ServiceImpl<ICardRefundDao, CardRefund> implements ICardRefundService {

    @Autowired
    private ICardRefundDao dao;

    @Override
    public List<CardRefund> getCardRefundList(int currentPage,
                                              int pageSize,
                                              String startDate,
                                              String endDate,
                                              String startTime,
                                              String endTime) {
        return dao.getCardRefundList(currentPage, pageSize, startDate, endDate, startTime, endTime);
    }

    @Override
    public int getCardRefundListNum(String startDate, String endDate, String startTime, String endTime) {
        return dao.getCardRefundListNum(startDate, endDate, startTime, endTime);
    }
}
