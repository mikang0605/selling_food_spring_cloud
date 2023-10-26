package com.example.controller;

import com.example.pojo.Personnel;
import com.example.pojo.PersonnelExport;
import com.example.pojo.dto.Result;
import com.example.service.IPersonnelService;
import com.example.utils.ExcelUtil;
import org.apache.ibatis.annotations.Param;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@RestController
public class PersonnelController {

    @Autowired
    private IPersonnelService service;

    @GetMapping("/getuinfobyid")
    public Personnel getUserInfoByUserid(int userid){
        return service.getUserInfoByUserid(userid);
    }

    @GetMapping("/unhook")
    public int unhook(int userid){
        return service.unhook(userid);
    }

    @GetMapping("/loss")
    public int loss(int userid){
        return service.loss(userid);
    }

    @GetMapping("/getlist")
    public Result getList(@Param("keyword") String keyword,
                          @Param("CurrentPage") int currentPage,
                          @Param("pageSize") int pageSize,
                          @Param("code") String code){
        Result result = new Result();
        List<Personnel> list = service.getList(keyword, currentPage, pageSize, code);
        int totalPersonnel = service.getTotalPersonnel(keyword, code);
        Map<String,Object> data = new HashMap<>();
        data.put("list", list);
        data.put("total", totalPersonnel);
        result.setCode(20000);
        result.setMessage("成功获取人员列表");
        result.setData(data);
        return result;
    }

    @PostMapping("/addpersonnel")
    public Result addPersonnel(@Param("ruleForm") Personnel ruleForm){
        Result result = new Result();
        result.setCode(20000);
        int i = service.addPersonnel(ruleForm);
        if(i > 0){
            result.setMessage("添加成功");
        }else{
            result.setMessage("添加失败");
        }
        return result;
    }

    @PostMapping("/updatepersonnel")
    public Result updatePersonnel(@Param("ruleForm") Personnel personnel){
        Result result = new Result();
        result.setCode(20000);
        int i = service.updatePersonnel(personnel);
        if(i > 0){
            result.setMessage("修改成功");
        }else{
            result.setMessage("修改失败");
        }
        return result;
    }

    @PostMapping("/deletepersonnel")
    public Result deletePersonnel(@Param("id") int id){
        Result result = new Result();
        result.setCode(20000);
        int i = service.deletePersonnel(id);
        if(i > 0){
            result.setMessage("删除成功");
        }else{
            result.setMessage("删除失败");
        }
        return result;
    }

    @GetMapping("/exportpersonnel")
    public void exportPersonnel(HttpServletResponse response){
        List<PersonnelExport> dataList = service.getExportList();
        List<String> titleList = new ArrayList<>();
        Collections.addAll(titleList,"卡号",
                "姓名",
                "性别",
                "卡类",
                "工号",
                "身份证号",
                "余额",
                "工本费",
                "状态",
                "状态",
                "人员类别",
                "用户密码",
                "电话号码",
                "有效日期从",
                "有效日期止",
                "备注",
                "部门");
        HSSFWorkbook workbook;
        OutputStream output;
        String fileName = "人员信息";
        try {
            workbook = ExcelUtil.createExcel(fileName,titleList,dataList);
            response.reset();
            //中文名称要进行编码处理
//            response.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"),"ISO8859-1")+".xls");
            response.setContentType("application/x-xls");
            //输出Excel文件
            output = response.getOutputStream();
            workbook.write(output);
            output.close();
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/importpersonnel")
    public void importPersonnel(@RequestParam("file") MultipartFile file){
        try {
            Workbook workbook = null;
            if ("xls".equals(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1))){
                workbook = new HSSFWorkbook(file.getInputStream());
            }else if ("xlsx".equals(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(" . ") + 1))){
                workbook = new XSSFWorkbook(file.getInputStream());
            }
            Sheet sheet = workbook.getSheetAt(0);
            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                Personnel personnel = new Personnel();
                personnel.setKh(row.getCell(0).getStringCellValue());
                personnel.setXm(row.getCell(1).getStringCellValue());
                personnel.setXb(row.getCell(2).getStringCellValue());
                personnel.setLb(row.getCell(3).getStringCellValue());
                personnel.setGlh(row.getCell(4).getStringCellValue());
                personnel.setSfzh(row.getCell(5).getStringCellValue());
                personnel.setYe(row.getCell(6).getNumericCellValue());
                personnel.setJe(row.getCell(7).getNumericCellValue());
                personnel.setBz(row.getCell(8).getStringCellValue());
                personnel.setState(row.getCell(9).getStringCellValue());
                personnel.setRylb(row.getCell(10).getStringCellValue());
                personnel.setUserpass(row.getCell(11).getStringCellValue());
                personnel.setTelphone(row.getCell(12).getStringCellValue());
                personnel.setSdate(row.getCell(13).getStringCellValue());
                personnel.setEdate(row.getCell(14).getStringCellValue());
                personnel.setStrbz(row.getCell(15).getStringCellValue());
                //获取一级部门名称
                String departName1 = row.getCell(16).getStringCellValue();
                String depart = service.analysisAndAddDepart(departName1, personnel, "");
                if(depart == null){
                    depart = personnel.getDepart();
                }
                //获取二级部门名称
                String departName2;
                departName2 = row.getCell(17).getStringCellValue();
                if(departName2 != null && !"".equals(departName2)){
                    service.analysisAndAddDepart(departName2, personnel, depart);
                }
                int addPersonnel = service.addPersonnel(personnel);
                if(addPersonnel > 0){
                    System.out.println("成功添加人员");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getStatus")
    public Result getStatus(@Param("userid") int userid){
        Result result = new Result();
        result.setCode(20000);

        String status = service.selectStatus(userid);
        result.setMessage(status);
        return result;
    }

    @GetMapping("/changestatustologoff")
    public int changeStatusToLogoff(@Param("userid") int userid){
        return service.changeStatusToLogoff(userid);
    }

    @GetMapping("changecardnum")
    public int changeCardNum(@Param("userid") int userid, @Param("kh") String kh){
        return service.changeCardNum(userid, kh);
    }

}
