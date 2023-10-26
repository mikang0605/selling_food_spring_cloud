package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.CardReplace;

import java.util.List;

public interface ICardReplaceService extends IService<CardReplace> {
    List<CardReplace> getCardReplaceTable(int currentPage, int pageSize, Integer userid, String departCode);

    int getCardReplaceTableNum(Integer userid, String departCode);
}
