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
        System.out.println(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
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

















    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<User> findPage(int page, int size){
        return userService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<User> findList(@RequestBody Map<String, Object> searchMap){
        return userService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<User> findPage(@RequestBody Map<String, Object> searchMap, int page, int size){
        return  userService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public User findById(Integer id){
        return userService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody User user){
        userService.add(user);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        userService.delete(id);
        return new Result();
    }

}
