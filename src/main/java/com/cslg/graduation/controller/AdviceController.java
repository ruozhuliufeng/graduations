package com.cslg.graduation.controller;

import com.cslg.graduation.entity.Advice;
import com.cslg.graduation.service.AdviceService;
import com.cslg.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 建议处理Controller
 * @author ruozhuliufeng
 */
@Controller
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestParam String name,@RequestParam String email,@RequestParam String message){
        Advice advice = new Advice();
        advice.setEmail(email);
        advice.setName(name);
        advice.setAdvice(message);
        adviceService.add(advice);
        return new Result();
    }
    @DeleteMapping("/delete")
    public String delete(Integer id){
        adviceService.deleteById(id);
        return "redirect:/admin/advice";
    }
}
