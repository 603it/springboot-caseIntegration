package com.halo.javamail.controller;

import com.halo.javamail.pojo.User;
import com.halo.javamail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 罗铭森
 * @date 2021/8/6 9:35
 * @description TODO(这里用一句话描述这个类的作用)
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public String register(User user){
        return userService.register(user);
    }

    /**
     * 校验邮箱中的code激活账户
     * 首先根据激活码code查询用户，之后再把状态修改为"1"
     * @param code
     * @return
     */
    @GetMapping(value = "/activation")
    public String activation(String code){
        userService.activation(code);
        return "login";
    }

    /**
     * 登陆
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public String login(User user){
        User u = userService.login(user);
        if (u !=null){
            return "welcome";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "用户名或密码错误！");
        return "login";
    }


    /**
     * 跳转到登录页面
     * @return login
     */
    @RequestMapping(value = "/loginPage")
    public String login(){
        return "login";
    }

    /**
     * 跳转到注册页面
     * @return register
     */
    @RequestMapping(value = "/registerPage")
    public String register(){
        return "register";
    }


}
