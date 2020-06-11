package com.study.bicycle.controller;

import com.study.bicycle.entity.User;
import com.study.bicycle.service.IUserService;
import com.study.bicycle.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @PostMapping("login")
    public JsonResult<User> login(String phone, String password){
        log.info("#########################");
        log.info("########用户登录########");
        log.info("#########################");
        log.info("phone={},password={}",phone,password);
        User user = userService.login(phone,password);
        return new JsonResult<>(user);
    }
    @GetMapping("detail/{id}")
    public JsonResult<User> detail(@PathVariable Integer id){
        log.info("#########################");
        log.info("########用户详情########");
        log.info("#########################");
        log.info("id={}",id);
        User user = userService.findUserInfo(id);
        return new JsonResult<>(user);
    }
    @PostMapping("change")
    public JsonResult<User> change(User user){
        log.info("#########################");
        log.info("########修改用户信息########");
        log.info("#########################");
        log.info("user={}",user);
        userService.changeUserInfo(user);
        return new JsonResult<>();
    }
    @GetMapping("list")
    public JsonResult<List<User>> list(){
        log.info("#########################");
        log.info("########查询用户列表########");
        log.info("#########################");
        List<User> list = userService.findAll();
        return new JsonResult<>(list);
    }
    @GetMapping("setAdmin/{id}")
    public JsonResult<Void> setAdmin(@PathVariable Integer id){
        log.info("#########################");
        log.info("########设置管理员########");
        log.info("#########################");
        log.info("id={}",id);
        userService.changeAdmin(id);
        return new JsonResult<>();
    }
    @PostMapping("reg")
    public JsonResult<User> reg(String phone, String password){
        log.info("#########################");
        log.info("########注册用户########");
        log.info("#########################");
        log.info("phone={},password={}",phone,password);
        User user = userService.reg(phone,password);
        return new JsonResult<>(user);
    }
    @GetMapping("findByNameLike")
    public JsonResult<List<User>> findByNameLike(String name){
        log.info("#########################");
        log.info("########模糊查询用户########");
        log.info("#########################");
        log.info("name={}",name);
        List<User> list = userService.findUserLike(name);
        return new JsonResult<>(list);
    }
}
