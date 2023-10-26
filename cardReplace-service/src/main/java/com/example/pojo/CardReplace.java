package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Thk")
public class CardReplace {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String rq;
    private String sj;
    private int userid;
    private String oldkh;
    private String newkh;
    private double gbf;
    private double je;
    private double je1;
    @TableField(exist = false)
    private String xm;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String glh;
}
