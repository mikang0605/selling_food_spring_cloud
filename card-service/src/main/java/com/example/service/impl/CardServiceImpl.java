package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.ICardDao;
import com.example.pojo.CardType;
import com.example.service.ICardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends ServiceImpl<ICardDao, CardType> implements ICardService{
}
