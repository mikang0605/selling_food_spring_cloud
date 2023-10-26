package com.example.controller;

import com.example.clients.PersonnelClient;
import com.example.pojo.CardReplace;
import com.example.pojo.dto.Result;
import com.example.service.ICardReplaceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CardReplaceController {

    @Autowired
    private ICardReplaceService service;

    @Autowired
    private PersonnelClient personnelService;

    @GetMapping("/gettable")
    public Result getTable(@Param("currentPage") int currentPage,
                           @Param("pageSize") int pageSize,
                           @Param("userid") Integer userid,
                           @Param("departCode") String departCode){
        Result result = new Result();
        List<CardReplace> list = service.getCardReplaceTable(currentPage, pageSize, userid, departCode);
        int num = service.getCardReplaceTableNum(userid, departCode);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", num);
        result.setCode(20000);
        result.setMessage("成功获取换卡补卡列表");
        result.setData(map);
        return result;
    }

    @PostMapping("/changecardnum")
    public Result changeCardNum(@Param("userid") int userid,
                                @Param("newkh") String newkh,
                                @Param("oldkh") String oldkh,
                                @Param("gbf") double gbf,
                                @Param("je") int je){
        Result result = new Result();
        CardReplace cardReplace = new CardReplace();
        int i = personnelService.changeCardNum(userid, newkh);
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        cardReplace.setRq(day.format(date));
        cardReplace.setSj(time.format(date));
        cardReplace.setUserid(userid);
        cardReplace.setOldkh(oldkh);
        cardReplace.setNewkh(newkh);
        cardReplace.setGbf(gbf);
        cardReplace.setJe(je);
        boolean j = service.save(cardReplace);
        result.setCode(20000);
        if(i > 0 && j){
            result.setMessage("修改成功");
        }else{
            result.setMessage("修改失败");
        }
        return result;
    }

}
