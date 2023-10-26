package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("Food_Type")
public class FoodType {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("TypeName")
    private String TypeName;
    private int orderxh;
    private int flag;
    private String bz;
    private Date indate;

}
