package com.example.service.impl;

import com.example.clients.PersonnelClient;
import com.example.dao.IPersonnelDao;
import com.example.pojo.Depart;
import com.example.pojo.Personnel;
import com.example.pojo.PersonnelExport;
import com.example.service.IPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonnelServiceImpl implements IPersonnelService {

    @Autowired
    private IPersonnelDao dao;

    @Autowired
    private PersonnelClient departService;

    @Override
    @Transactional
    public List<Personnel> getList(String keyword, int currentPage, int pageSize, String code) {
        return dao.getList(keyword, currentPage, pageSize, code);
    }

    @Override
    public int addPersonnel(Personnel personnel) {
        return dao.addPersonnel(personnel);
    }

    @Override
    public int updatePersonnel(Personnel personnel) {
        return dao.updatePersonnel(personnel);
    }

    @Override
    public int deletePersonnel(int id) {
        return dao.deletePersonnel(id);
    }

    @Override
    public int getTotalPersonnel(String keyword, String code) {
        return dao.getTotalPersonnel(keyword, code);
    }

    @Override
    public List<PersonnelExport> getExportList() {
        return dao.getExportList();
    }

    @Override
    public String analysisAndAddDepart(String departName, Personnel personnel, String upDepart) {
        //标记是否存储在部门列表中
        int flag1 = 0;
        List<Depart> departList = departService.getDepart();
        for(Depart depart : departList){
            if(depart.getName().equals(departName)){
                personnel.setDepart(depart.getCode());
                flag1 = 1;
            }
        }
        if(flag1 == 0){
            List<Depart> departs = departService.sortDepart(departList);
            List<String> codeList = departs.stream().map(Depart::getCode).collect(Collectors.toList());
            String code = "01";
            if(codeList.size() > 0){
                String s = codeList.get(codeList.size() - 1);
                int biggestCode = Integer.parseInt(s);
                if(biggestCode < 10){
                    code = upDepart + "0" + (biggestCode + 1);
                }else{
                    code = upDepart + (biggestCode + 1) + "";
                }
            }
            int addDepart = departService.add(departName, code);
            personnel.setDepart(code);
            if(addDepart > 0){
                System.out.println("成功添加部门");
            }
            return code;
        }
        return null;
    }

    @Override
    public int loss(int userid) {
        return dao.loss(userid);
    }

    @Override
    public Personnel getUserInfoByUserid(int userid) {
        return dao.getUserInfoByUserid(userid);
    }

    @Override
    public int unhook(int userid) {
        return dao.unhook(userid);
    }

    @Override
    public String selectStatus(int userid) {
        return dao.selectStatus(userid);
    }

    @Override
    public int changeCardNum(int userid, String kh) {
        return dao.changeCardNum(userid, kh);
    }

    @Override
    public int changeStatusToLogoff(int userid) {
        return dao.changeStatusToLogoff(userid);
    }

}
