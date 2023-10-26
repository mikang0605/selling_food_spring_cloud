package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Loss;

import java.util.List;

public interface ILossService extends IService<Loss> {

    List<Loss> getAllLoss(String depart, int currentPage, int pageSize, Integer userid, String lossDate, String unhookDate);

    int getLossNum(String depart, Integer userid, String lossDate, String unhookDate);

}
