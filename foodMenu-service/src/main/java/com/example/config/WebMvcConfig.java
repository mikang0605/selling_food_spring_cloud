package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 告知系统图片文件夹 当成 静态资源访问 解决访问上传的图片还需要重启
        // path 是图片上传后的绝对地址
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
        // 这里是访问地址匹配，只要匹配到 uploadFiles 字段的话，就当静态资源映射下
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+path);
    }

}
