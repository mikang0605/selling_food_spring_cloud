package com.example.controller;

import com.example.clients.PersonnelClient;
import com.example.pojo.CardRefund;
import com.example.pojo.DataBean;
import com.example.pojo.dto.Result;
import com.example.service.ICardRefundService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class CardRefundController {

    @Autowired
    private ICardRefundService service;

    @Autowired
    private PersonnelClient personnelService;


    @GetMapping("/getlist")
    public Result getList(@Param("currentPage") int currentPage,
                          @Param("pageSize") int pageSize,
                          @Param("startDate") String startDate,
                          @Param("endDate") String endDate,
                          @Param("startTime") String startTime,
                          @Param("endTime") String endTime) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        List<CardRefund> list = service.getCardRefundList(currentPage, pageSize,startDate, endDate, startTime, endTime);
        map.put("list",list);
        int num = service.getCardRefundListNum(startDate, endDate, startTime, endTime);
        map.put("total",num);
        result.setCode(20000);
        result.setData(map);
        result.setMessage("成功获取退卡销户列表");
        return result;
    }

    @PostMapping("/logoff")
    public Result logoff(@Param("form") CardRefund cardRefund){
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        cardRefund.setRq(day.format(date));
        cardRefund.setSj(time.format(date));
        int toLogoff = personnelService.changeStatusToLogoff(cardRefund.getUserid());
        boolean insertLogoffInfo = service.save(cardRefund);
        Result result = new Result();
        result.setCode(20000);
        if (toLogoff > 0 && insertLogoffInfo){
            result.setMessage("销卡成功");
        }else{
            result.setMessage("销卡失败");
        }
        return result;
    }

    @GetMapping("/printPDF")
    public void printPDF(HttpServletResponse response,
                         @Param("startDate") String startDate,
                         @Param("endDate") String endDate,
                         @Param("startTime") String startTime,
                         @Param("endTime") String endTime) throws JRException, IOException {

        String jrxmlPath = "src\\main\\resources\\report.jrxml";
        String jasperPath = "src\\main\\resources\\report.jasper";

        //编译模板
        JasperCompileManager.compileReportToFile(jrxmlPath,jasperPath);

        //获取数据
        int num = service.getCardRefundListNum(startDate, endDate, startTime, endTime);
        List<CardRefund> cardRefundList = service.getCardRefundList(1, num, startDate, endDate, startTime, endTime);

        //构造数据
        Map<String, Object> parameters = new HashMap<>();
        if(startDate != null && startDate != ""){
            parameters.put("startDate", startDate);
        }else{
            parameters.put("startDate", "2023-08-01");
        }
        if(endDate != null && endDate != ""){
            parameters.put("endDate", endDate);
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            parameters.put("endDate", sdf.format(date));
        }

        List<DataBean> tableData = new ArrayList<>();
        for (CardRefund cardRefund : cardRefundList) {
            DataBean dataBean = new DataBean();
            dataBean.setGlh(cardRefund.getGlh());
            dataBean.setXm(cardRefund.getXm());
            dataBean.setRq(cardRefund.getRq());
            dataBean.setGbf(cardRefund.getGbf());
            dataBean.setJe(cardRefund.getJe());
            dataBean.setTotal(0);
            tableData.add(dataBean);
        }
        parameters.put("table_data", new JRBeanCollectionDataSource(tableData));

        //填充数据---使用JavaBean数据源方式填充

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperPath,
                        parameters,
                        new JRBeanCollectionDataSource(tableData));
        //输出文件
        OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    }

}
