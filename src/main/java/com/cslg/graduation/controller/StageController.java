package com.cslg.graduation.controller;

import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.entity.Stage;
import com.cslg.graduation.service.StageService;
import com.cslg.graduation.util.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
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
    public Stage findById(Integer id){
        return stageService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Stage stage){
        stageService.add(stage);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Stage stage){
        stageService.update(stage);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        stageService.delete(id);
        return new Result();
    }

}
