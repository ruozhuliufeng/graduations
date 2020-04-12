package com.cslg.graduation.controller;

import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.entity.Stage;
import com.cslg.graduation.service.StageService;
import com.cslg.graduation.util.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/stage")
public class StageController {

    @Reference
    private StageService stageService;

    @GetMapping("/findAll")
    public List<Stage> findAll(){
        return stageService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Stage> findPage(int page, int size){
        return stageService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Stage> findList(@RequestBody Map<String, Object> searchMap){
        return stageService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Stage> findPage(@RequestBody Map<String, Object> searchMap, int page, int size){
        return  stageService.findPage(searchMap,page,size);
    }

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
