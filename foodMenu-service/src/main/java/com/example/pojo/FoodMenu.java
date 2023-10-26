package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@TableName("Food_Menu")
public class FoodMenu {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("menu_no")
    private String menu_no;
    @TableField("menu_name")
    private String menu_name;
    private String menutype;
    private String price;
    private String images;
    private String bz;
    private int inuse;
    private byte[] photo;
    private String stype;
    private int usenum;
    private int pernum;
    private int idimage;
    private int ftype;
}
