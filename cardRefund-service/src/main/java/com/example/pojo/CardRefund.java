package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Ttkmx")
public class CardRefund {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private int userid;
    private String rq;
    private String sj;
    private String kh;
    private String glh;
    private String xm;
    private String bm;
    private double je;
    private double je1;
    private double gbf;
    private String tklb;

}
