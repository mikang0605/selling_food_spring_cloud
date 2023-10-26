package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Tgsmx")
public class Loss {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private int userid;
    private String state;
    private String rq;
    private String jgrq;
    private String sj;
    private String jgsj;
    private String kh;
    private String glh;
    private String xm;
    private String bm;
    private String gsbz;
    private double je;

}
