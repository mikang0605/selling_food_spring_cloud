package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.FoodMenu;
import com.example.pojo.dto.Result;
import com.example.service.IFoodMenuService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class FoodMenuController {

    @Autowired
    private IFoodMenuService service;

    @GetMapping("/getlist")
    public Result getFoodMenuList(@Param("currentPage") int currentPage,
                                  @Param("pageSize") int pageSize,
                                  @Param("stype") String stype,
                                  @Param("menutype") String menutype,
                                  @Param("menu_name") String menu_name) {
        Result result = new Result();
        List<FoodMenu> foodMenuList = service.getFoodMenuList(currentPage, pageSize, stype, menutype, menu_name);
        int num = service.getFoodMenuNum(stype, menutype, menu_name);
        Map<String, Object> map = new HashMap<>();
        map.put("list", foodMenuList);
        map.put("total", num);
        result.setCode(20000);
        result.setMessage("成功获取菜品列表");
        result.setData(map);
        return result;
    }

    @PostMapping("/add")
    public Result add(@RequestParam("form") String form,
                      @RequestParam(required=false,value="file") MultipartFile file) throws IOException {
        Result result = new Result();
        result.setCode(20000);
        FoodMenu foodMenu = JSONObject.parseObject(form, FoodMenu.class);
        if(service.getNoRepeat(foodMenu.getMenu_no()) > 0){
            result.setMessage("编号重复");
            return result;
        }
        if(file != null){
            //压缩图片
            int times = 0;
            long l = file.getSize() / (500 * 1024);
            times = (int) Math.sqrt(l);
            InputStream inputStream = file.getInputStream();
            BufferedImage src = ImageIO.read(inputStream);
            BufferedImage bufferedImage = null;
            if(times > 1){
                bufferedImage = Thumbnails.of(src).outputQuality(1f).size(src.getWidth() / times,
                                src.getHeight() / times)
                        .asBufferedImage();
            }else{
                bufferedImage = src;
            }


            String folder = "src\\main\\resources\\static\\";
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.lastIndexOf(".") != -1) {
                extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                extension = extension.toLowerCase();
            }
            String uuid = UUID.randomUUID() + "." + extension;
            String filePosition = folder + uuid;
            FileOutputStream fos = new FileOutputStream(filePosition);
            ImageIO.write(bufferedImage, extension, fos);
            fos.close();
            foodMenu.setImages(uuid);
        }
        boolean i = service.save(foodMenu);
        if(i){
            result.setMessage("添加成功");
        }else {
            result.setMessage("添加失败");
        }
        return result;
    }

    @PostMapping("/del")
    public Result delFoodMenu(@Param("id") int id){
        Result result = new Result();
        boolean i = service.removeById(id);
        result.setCode(20000);
        if(i){
            result.setMessage("删除成功");
        }else {
            result.setMessage("删除失败");
        }
        return result;
    }

}
