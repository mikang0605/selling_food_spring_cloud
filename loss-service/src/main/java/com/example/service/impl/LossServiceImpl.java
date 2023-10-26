package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.ILossDao;
import com.example.pojo.Loss;
import com.example.service.ILossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LossServiceImpl extends ServiceImpl<ILossDao, Loss> implements ILossService {

    @Autowired
    private ILossDao dao;

    @Override
    public List<Loss> getAllLoss(String depart, int currentPage, int pageSize, Integer userid, String lossDate, String unhookDate) {
        return dao.getAllLoss(depart, currentPage, pageSize, userid, lossDate, unhookDate);
    }

    @Override
    public int getLossNum(String depart, Integer userid, String lossDate, String unhookDate) {
        return dao.getLossNum(depart, userid, lossDate, unhookDate);
    }
}
