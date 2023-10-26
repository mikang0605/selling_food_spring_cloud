package com.example.clients;

import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("personnel-service")
public interface PersonnelClient {

    @GetMapping("changecardnum")
    int changeCardNum(@RequestParam("userid") int userid, @RequestParam("kh") String kh);

}
