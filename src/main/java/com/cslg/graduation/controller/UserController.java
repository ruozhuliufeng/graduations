package com.cslg.graduation.controller;

import com.cslg.graduation.dto.UserDTO;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.entity.User;
import com.cslg.graduation.service.UserService;
import com.cslg.graduation.util.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession httpSession){
        User currentUser = userService.login(userDTO);
        if (currentUser==null){
            httpSession.setAttribute("msg","用户名或密码错误");
            return "redirect:/err";
        }
        httpSession.setAttribute("currentUser",currentUser);
        return "redirect:/";

    }

    //注册
    @PostMapping("/register")
    public String register(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setSex(userDTO.getSex());
        user.setType(0);
        user.setStatus(0);
        userService.add(user);
        return "redirect:/login";
    }

    /**
     *  退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
    /**
     * 管理员登录
     */
    @PostMapping("/adminLogin")
    public String adminLogin(UserDTO userDTO,HttpSession httpSession){
        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getPassword());
        User currentUser = userService.login(userDTO);
        if (currentUser.getType()!=1){
            httpSession.setAttribute("msg","登录失败，请检查输入是否正确或是否为管理员");
            return "redirect:/admin/loginPage";
        }
        httpSession.setAttribute("currentUser",currentUser);
        return "redirect:/admin";
    }

    //个人中心页
    @RequestMapping("/center")
    public String centerPage(HttpSession httpSession){
        User currentUser = (User) httpSession.getAttribute("currentUser");
        currentUser = userService.findById(currentUser.getId());
        httpSession.setAttribute("currentUser",currentUser);
        return "/page/center";
    }
    //修改页
    @RequestMapping("/centerUpdate")
    public String centerUpdatePage(){
        return "/page/userUpdate";
    }

    @GetMapping("/findPage")
    public PageResult<User> findPage(int page, int size){
        return userService.findPage(page, size);
    }

    @GetMapping("/findById")
    public String findById(Integer id,HttpSession httpSession){
        User user = userService.findById(id);
        httpSession.setAttribute("user",user);
        return "/admin/userUpdate";
    }

    @PostMapping("/update")
    public String update(UserDTO userDTO){
        User user = userService.findById(userDTO.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        int type;
        if ("管理员".equals(userDTO.getTname())){
            type=1;
        }else {
            type=0;
        }
        user.setType(type);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        userService.update(user);
        return "redirect:/admin/user";
    }

    /**
     * 个人修改
     * @param userDTO
     * @return
     */
    @PostMapping("/mupdate")
    public String mupdate(UserDTO userDTO){
        User user = userService.findById(userDTO.getId());
        if (user==null){
            //返回错误页面
            return "/err";
        }
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setHname(userDTO.getHname());
        user.setSname("第一阶段");
        user.setSex(userDTO.getSex());
        userService.update(user);
        return "redirect:/user/center";
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        userService.delete(id);
        return "redirect:/admin/user";
    }

}
