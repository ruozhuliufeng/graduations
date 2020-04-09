package com.cslg.graduation.controller;

import com.cslg.graduation.dto.HobbyOutputDTO;
import com.cslg.graduation.entity.Hobby;
import com.cslg.graduation.service.CategoryService;
import com.cslg.graduation.service.HobbyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private HobbyService hobbyService;
    @Autowired
    private CategoryService categoryService;
    //首页
    @RequestMapping("/")
    public String index(HttpSession httpSession){
        List<Hobby> hobbies = hobbyService.findAll();
        List<HobbyOutputDTO> hobbyList = new ArrayList<>();
        for (int i = 0; i < hobbies.size(); i++) {
            Hobby hobby = hobbies.get(i);
            String cname = categoryService.findById(hobby.getCid()).getName();
            HobbyOutputDTO hobbyOutputDTO = new HobbyOutputDTO();
            hobbyOutputDTO.setName(hobby.getName());
            hobbyOutputDTO.setCname(cname);
            hobbyList.add(hobbyOutputDTO);
        }
        httpSession.setAttribute("hobbyList",hobbyList);
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
}
