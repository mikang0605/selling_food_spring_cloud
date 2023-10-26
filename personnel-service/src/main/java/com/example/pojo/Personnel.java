package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Trsda")
public class Personnel {
    private int id;
    private int userId;
    private String kh;
    private String xm;
    private String xb;
    private String lb;
    private String depart;
    private String bc;
    private String glh;
    private String sfzh;
    private String fksj;
    private double ye;
    private double cje;
    private double qje;
    private double je;
    private double je1;
    private double je2;
    private double je3;
    private double je4;
    private double je5;
    private double je6;
    private String bz;
    private String state;
    private int idbz;
    private String rylb;
    private String fwfag;
    private String fwgxrq;
    private String xfrq;
    private String userid1;
    private String userid2;
    private String userpass;
    private byte[] photo;
    private String telphone;
    private double oldye;
    private String sdate;
    private String edate;
    private String khsn;
    private String strbz;
    private String NAME;
}
