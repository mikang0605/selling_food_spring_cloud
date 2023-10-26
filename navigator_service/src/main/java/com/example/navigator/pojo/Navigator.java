package com.example.navigator.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Navigator {
    private int id;
    private String name;
    private int pid;
    private String url;

    @TableField(exist = false)
    private List<Navigator> children = new ArrayList<>();

    private String icon;
}
