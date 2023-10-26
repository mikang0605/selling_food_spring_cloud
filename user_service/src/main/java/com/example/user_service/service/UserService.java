package com.example.user_service.service;

import com.example.*;
import com.example.user_service.dao.IUserDao;
import com.example.user_service.pojo.Toptioner;
import com.example.user_service.pojo.dto.LoginResult;
import com.example.user_service.pojo.dto.Result;
import com.example.user_service.utils.ConstantUtil;
import com.example.user_service.utils.JWTUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class UserService {

    @Autowired
    private IUserDao dao;

    @Resource
    private StringRedisTemplate template;

    @GetMapping(value = "{id}")
    public Toptioner getUser(@PathVariable("id") Integer id){
        return dao.selectById(id);
    }

    @PostMapping("/login")
    public Result login(@Param("username") String username,
                        @Param("password") String password){
        Result result = new Result();
        LoginResult loginResult = new LoginResult();
        Toptioner top = new Toptioner();
        top.setName(username);
        top.setPassword(password);
        Map<String, Object> map = new HashMap<>();
        map.put("name", username);
        map.put("password", password);
        List<Toptioner> users = dao.selectByMap(map);
        if(users.size() == 0){
            result.setMessage("密码或用户名错误");
            result.setData(loginResult);
            result.setCode(20000);
            return result;
        }else{
            result.setMessage("登录成功");
        }
        Map<String,String> userInfo = new HashMap<>();
        userInfo.put("username",username);
        String token = JWTUtil.sign(userInfo, ConstantUtil.SECRET, 3600L);
        loginResult.setToken(token);
        result.setData(loginResult);
        result.setCode(20000);
        return result;
    }

    @GetMapping("/info")
    public Result getInfo(String token){
        Result result = new Result();
        Map<String, Object> verifyInfo = JWTUtil.verifyInfo(token, ConstantUtil.SECRET);
        if("0".equals(verifyInfo.get("state"))){
            result.setCode(20000);
            result.setMessage(verifyInfo.get("message").toString());
            return result;
        }
        result.setCode(20000);
        String username = JWTUtil.getClaims(token, "username");
        Map<String, Object> map = new HashMap<>();
        map.put("name", username);
        List<Toptioner> toptioners = dao.selectByMap(map);
        result.setMessage("获取信息成功");
        result.setData(toptioners.get(0));
        return result;
    }

    @PostMapping("/logout")
    public Result logout(@RequestHeader("X-Token") String token){
        Long expiresAtMill = JWTUtil.getExpiresAtMill(token);
        Result result = new Result();
        if(expiresAtMill == -1L){
            result.setMessage("token已过期");
            result.setCode(50014);
            return result;
        }else if (expiresAtMill == -2L){
            result.setMessage("token非法");
            result.setCode(50008);
            return result;
        }
        template.opsForValue().set(token,"过期token",expiresAtMill, TimeUnit.MILLISECONDS);
        result.setCode(20000);
        result.setMessage("退出登录成功");
        return result;
    }

    @PostMapping("/addUser")
    public Result addUser(@Param("form") Toptioner user){
        Result result = new Result();
        int i = dao.insert(user);
        result.setCode(20000);
        if(i > 0){
            result.setMessage("添加成功");
        }else{
            result.setMessage("添加失败");
        }
        return result;
    }

    @GetMapping("/deluser")
    public Result delUser(@Param("id") int id){
        Result result = new Result();
        result.setCode(20000);
        if(id == 1){
            result.setMessage("无法删除系统管理员");
            return result;
        }
        int delUser = dao.deleteById(id);
        if(delUser > 0){
            result.setMessage("删除成功");
        }else{
            result.setMessage("删除失败");
        }
        return result;
    }

    @PostMapping("/changepwd")
    public Result changePwd(@Param("id") int id, @Param("password") String password){
        Result result = new Result();
        result.setCode(20000);
        if(id == 1){
            result.setMessage("不可修改系统管理员的密码");
            return result;
        }
        Toptioner user = new Toptioner();
        user.setId(id);
        user.setPassword(password);
        int i = dao.updateById(user);
        if(i > 0){
            result.setMessage("修改成功");
        }else{
            result.setMessage("修改失败");
        }
        return result;
    }

    @PostMapping("/changerights")
    public Result changeRights(@Param("id") int id,
                               @Param("memo") String memo){
        Result result = new Result();
        result.setCode(20000);
        Toptioner user = new Toptioner();
        user.setId(id);
        user.setMemo(memo);
        int i = dao.updateById(user);
        if(i > 0){
            result.setMessage("修改权限成功");
        }else{
            result.setMessage("修改权限失败");
        }
        return result;
    }

    @GetMapping("/getrights")
    public Result getRightsById(@Param("id") int id){
        Result result = new Result();
        result.setCode(20000);
        result.setMessage("成功获取权限列表");
        Toptioner toptioner = dao.selectById(id);
        String s = toptioner.getMemo();
        result.setData(s);
        return result;
    }

}
