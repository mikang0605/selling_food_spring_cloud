package com.example.clients;

import com.example.pojo.Personnel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("personnel-service")
public interface PersonnelCLient {

    @GetMapping("loss")
    int loss(@RequestParam("userid") int userid);

    @GetMapping("/unhook")
    int unhook(@RequestParam("userid") int userid);

    @GetMapping("/getuinfobyid")
    Personnel getUserInfoByUserid(@RequestParam("userid") int userid);

}
