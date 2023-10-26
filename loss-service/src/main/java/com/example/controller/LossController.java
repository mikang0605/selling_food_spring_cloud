package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.clients.DepartClient;
import com.example.clients.PersonnelCLient;
import com.example.pojo.Depart;
import com.example.pojo.Loss;
import com.example.pojo.Personnel;
import com.example.pojo.dto.Result;
import com.example.service.ILossService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LossController {

    @Autowired
    private ILossService service;

    @Autowired
    private DepartClient departService;

    @Autowired
    private PersonnelCLient personnelService;

    @GetMapping("/getallloss")
    public Result getAllLoss(@Param("userid") String depart,
                             @Param("currentPage") int currentPage,
                             @Param("pageSize") int pageSize,
                             @Param("userid") Integer userid,
                             @Param("lossDate") String lossDate,
                             @Param("unhookDate") String unhookDate){
        Result result = new Result();
        List<Depart> departs = departService.getDepart();
        String departcode = null;
        for (Depart departOne : departs) {
            if(departOne.getName().equals(depart)){
                departcode = departOne.getCode();
            }
        }
        if(departcode == null){
            result.setCode(20000);
            result.setMessage("错误！无此部门！");
        }
        List<Loss> lossList = service.getAllLoss(departcode, currentPage, pageSize, userid, lossDate, unhookDate);
        int num = service.getLossNum(departcode, userid, lossDate, unhookDate);
        Map<String,Object> map = new HashMap<>();
        map.put("list", lossList);
        map.put("total", num);
        result.setCode(20000);
        result.setMessage("成功获取挂失信息");
        result.setData(map);
        return result;
    }

    @PostMapping("/addLossInfo")
    public Result addLossInfo(@RequestBody String json){
        Result result = new Result();
        List<Integer> userids = JSON.parseArray(json, Integer.class);
        int flag = 0;
        for (Integer userid : userids) {
            int personnelLoss = personnelService.loss(userid);
            Loss loss = new Loss();
            loss.setUserid(userid);
            loss.setState("挂失");
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            loss.setRq(format.format(date));
            loss.setSj(sdf.format(date));
            Personnel info = personnelService.getUserInfoByUserid(userid);
            loss.setKh(info.getKh());
            loss.setGlh(info.getGlh());
            loss.setXm(info.getXm());
            Depart depart = departService.getDepartByCode(info.getDepart());
            loss.setBm(depart.getName());
            loss.setJe(info.getJe());
            boolean i = service.save(loss);
            if(personnelLoss > 0 && i){
                flag++;
            }
        }
        result.setCode(20000);
        if(flag == userids.size()){
            result.setMessage("所有人员挂失成功");
        }
        else {
            result.setMessage("人员挂失失败");
        }
        return result;
    }

    @PostMapping("/unhook")
    public Result unhook(@Param("id") Integer id, @Param("userid") Integer userid){
        Result result = new Result();
        int i = personnelService.unhook(userid);
        Loss loss = new Loss();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        loss.setJgrq(format.format(date));
        loss.setJgsj(sdf.format(date));
        loss.setId(id);
        UpdateWrapper<Loss> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        boolean unhook = service.update(loss, wrapper);
        result.setCode(20000);
        if(i > 0 && unhook){
            result.setMessage("解挂成功");
        }else{
            result.setMessage("解挂失败");
        }
        return result;
    }

}
