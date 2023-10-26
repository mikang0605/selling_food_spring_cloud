package com.example.service;

import com.example.pojo.Personnel;
import com.example.pojo.PersonnelExport;

import java.util.List;

public interface IPersonnelService {
    List<Personnel> getList(String keyword, int currentPage, int pageSize, String code);

    //添加人员
    int addPersonnel(Personnel personnel);

    //修改人员信息
    int updatePersonnel(Personnel personnel);

    //删除人员信息
    int deletePersonnel(int id);

    //获取人员总数
    int getTotalPersonnel(String keyword, String code);

    //获取需要导出的人员信息
    List<PersonnelExport> getExportList();

    //解析excel中的部门
    String analysisAndAddDepart(String departName, Personnel personnel, String upDepart);

    //将状态由正常改为挂失
    int loss(int userid);

    //通过userid获取人员信息
    Personnel getUserInfoByUserid(int userid);

    //将状态由挂失改为正常
    int unhook(int userid);

    //查询人员挂失状态
    String selectStatus(int userid);

    //修改卡号
    int changeCardNum(int userid, String kh);

    //修改状态为注销
    int changeStatusToLogoff(int userid);
}
