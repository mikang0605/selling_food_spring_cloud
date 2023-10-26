package com.example.controller;

import com.example.pojo.CardType;
import com.example.pojo.dto.Result;
import com.example.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private ICardService service;

    @GetMapping("/getcardtypes")
    public Result getCardTypes(){
        Result result = new Result();
        List<CardType> cardTypes = service.list();
        result.setCode(20000);
        result.setMessage("成功获取卡类列表");
        result.setData(cardTypes);
        return result;
    }

}
