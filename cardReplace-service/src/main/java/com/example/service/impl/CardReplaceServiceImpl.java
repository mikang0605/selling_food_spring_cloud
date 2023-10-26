package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.ICardReplaceDao;
import com.example.pojo.CardReplace;
import com.example.service.ICardReplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardReplaceServiceImpl extends ServiceImpl<ICardReplaceDao, CardReplace> implements ICardReplaceService {

    @Autowired
    private ICardReplaceDao dao;

    @Override
    public List<CardReplace> getCardReplaceTable(int currentPage, int pageSize, Integer userid, String departCode) {
        return dao.getCardReplaceTable(currentPage, pageSize, userid, departCode);
    }

    @Override
    public int getCardReplaceTableNum(Integer userid, String departCode) {
        return dao.getCardReplaceTableNum(userid, departCode);
    }
}
