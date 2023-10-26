package com.example.depart.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Depart {
    private String code;
    private String name;
    private String state1;
    private String state2;
    private String address;
    private String tel;
    private String fax;
    private String ywdw;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String cs1;
    private String cs2;
    private String cs3;
    private String cs4;
    private String cs5;

    @TableField(exist = false)
    private List<Depart> children = new ArrayList<>();
}
