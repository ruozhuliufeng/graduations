package com.cslg.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    //首页
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/index")
    public String index2(){
        return "index";
    }
    @RequestMapping("/err")
    public String err(){
        return "err";
    }
    //登录页
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }
    //注册页
    @RequestMapping("/register")
    public String regPage(){
        return "register";
    }
}
