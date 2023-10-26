package com.example.dao;

import com.example.pojo.Personnel;
import com.example.pojo.PersonnelExport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPersonnelDao {

    List<Personnel> getList(@Param("keyword") String keyword,
                            @Param("currentPage") int currentPage,
                            @Param("pageSize") int pageSize,
                            @Param("code") String code);

    //添加人员
    int addPersonnel(Personnel personnel);

    //修改人员信息
    int updatePersonnel(Personnel personnel);

    //删除人员信息
    int deletePersonnel(int id);

    //获取人员总数
    int getTotalPersonnel(@Param("keyword") String keyword,
                          @Param("code") String code);

    //获取需要的所有的人员信息
    List<PersonnelExport> getExportList();

    //将状态从正常改为挂失
    int loss(int userid);

    //按userid获取人员信息
    Personnel getUserInfoByUserid(int userid);

    //将状态从挂失改为正常
    int unhook(int userid);

    //查询人员状态是否为挂失
    String selectStatus(int userid);

    //更换卡号
    int changeCardNum(@Param("userid") int userid,
                      @Param("kh") String kh);

    //修改状态为注销
    int changeStatusToLogoff(int userid);

}
