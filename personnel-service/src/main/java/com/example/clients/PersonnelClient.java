package com.example.clients;

import com.example.pojo.Depart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("depart-service")
@Repository
public interface PersonnelClient {
    @GetMapping("/getdepart")
    List<Depart> getDepart();

    @PostMapping("sortdepart")
    List<Depart> sortDepart(@RequestParam("departs") List<Depart> departs);

    @PostMapping("/add")
    int add(@RequestParam("name") String name, @RequestParam("code") String code);
}
