package com.cslg.graduation.controller.admin;

import com.cslg.graduation.dto.UserDTO;
import com.cslg.graduation.entity.User;
import com.cslg.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理的Controller
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;

    /**
     * 后台页面跳转
     */
    @RequestMapping("/{name}")
    public String page( @PathVariable String name){
        return "/admin/" + name;
    }

    //后台登录页
    @RequestMapping("/loginPage")
    public String loginPage(){
        return "/admin/login";
    }
    @RequestMapping("/login")
    public String login(UserDTO userDTO,HttpSession httpSession){
        User adminUser = userService.login(userDTO);
        if (adminUser!=null){
            httpSession.setAttribute("adminUser",adminUser);
            return "redirect:/admin/index";
        }
        return "/admin/login";

    }
    //后台主页
    @RequestMapping("/indexPage")
    public String indexPage(HttpSession httpSession){
        User adminUser = (User) httpSession.getAttribute("adminUser");
        if (adminUser==null){
            //用户未登录
            return "/admin/login";
        }
        return "/admin/index";
    }
    //用户管理
    @RequestMapping("/user")
    public String userPage(HttpSession httpSession){
        List<User> userList = userService.findAll();
        httpSession.setAttribute("userList",userList);
        return "/admin/user";
    }
}
