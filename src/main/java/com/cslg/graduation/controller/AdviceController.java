package com.cslg.graduation.controller;

import com.cslg.graduation.entity.Advice;
import com.cslg.graduation.service.AdviceService;
import com.cslg.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 建议处理Controller
 * @author ruozhuliufeng
 */
@Controller
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @PostMapping("/add")
    public String add(Advice advice){
        adviceService.add(advice);
        return "redirect:/";
    }
    @DeleteMapping("/delete")
    public String delete(Integer id){
        adviceService.deleteById(id);
        return "redirect:/admin/advice";
    }
}
