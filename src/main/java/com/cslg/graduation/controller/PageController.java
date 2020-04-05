package com.cslg.graduation.controller;

import com.cslg.graduation.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 页面跳转Controller
 */
@Controller
public class PageController {
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
    //介绍页
    @RequestMapping("/info")
    public String infoPage(){
        return "/page/info";
    }
    //个人中心页
    @RequestMapping("/center")
    public String centerPage(){
        return "/page/center";
    }
    //修改页
    @RequestMapping("/centerUpdate")
    public String centerUpdatePage(){
        return "/page/userUpdate";
    }
    //学习页
    @RequestMapping("/learn")
    public String learnPage(){
        return "/page/learn";
    }
    //博客页
    @RequestMapping("/forum")
    public String forumPage(){
        return "/forum/main";
    }
    //帖子详情页
    @RequestMapping("/detail")
    public String detailPage(){
        return "/forum/topicDetails";
    }
    //添加帖子页
    @RequestMapping("/addTopic")
    public String addTopicPage(){
        return "forum/topicAdd";
    }
    //后台登录页
    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "admin/login";
    }
    //后台管理页
    @RequestMapping("/admin")
    public String adminPage(){
        return "forum/main";
    }
    //兴趣管理页
    @RequestMapping("/adminLearnPage")
    public String addPage(){
        return "admin/learning";
    }
    //帖子管理页
    @RequestMapping("/adminTopicPage")
    public String adminTopicPage(){
        return "admin/topic";
    }
    //
    @RequestMapping("/adminUserPage")
    public String adminUserPage(){
        return "admin/user";
    }
    //板块管理页
    @RequestMapping("/adminZonePage")
    public String adminZonePage(){
        return "admin/zone";
    }

}
