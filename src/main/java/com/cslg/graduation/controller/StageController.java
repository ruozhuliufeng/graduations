package com.cslg.graduation.controller;

import com.cslg.graduation.entity.Stage;
import com.cslg.graduation.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/stage")
public class StageController {

    @Autowired
    private StageService stageService;


    @GetMapping("/findById")
    public String findById(Integer id, HttpSession httpSession){
        Stage stage = stageService.findById(id);
        httpSession.setAttribute("stage",stage);
        return "/admin/stageUpdate";
    }


    @PostMapping("/add")
    public String add(Stage stage){
        stageService.add(stage);
        return "redirect:/admin/stage";
    }

    @PostMapping("/update")
    public String update(Stage stage){
        stageService.update(stage);
        return "redirect:/admin/stage";
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        stageService.delete(id);
        return "redirect:/admin/stage";
    }

}
