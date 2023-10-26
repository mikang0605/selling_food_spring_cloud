package com.example.clients;

import com.example.pojo.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("depart-service")
public interface DepartClient {

    @GetMapping("/getdepart")
    List<Depart> getDepart();

    @GetMapping("getdepartbycode")
    Depart getDepartByCode(@RequestParam("code") String code);

}
