package com.cslg.graduation.controller;

import com.cslg.graduation.entity.Hobby;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.service.HobbyService;
import com.cslg.graduation.util.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hobby")
public class HobbyController {

    @Reference
    private HobbyService hobbyService;

    @GetMapping("/findAll")
    public List<Hobby> findAll(){
        return hobbyService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Hobby> findPage(int page, int size){
        return hobbyService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Hobby> findList(@RequestBody Map<String, Object> searchMap){
        return hobbyService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Hobby> findPage(@RequestBody Map<String, Object> searchMap, int page, int size){
        return  hobbyService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Hobby findById(Integer id){
        return hobbyService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Hobby hobby){
        hobbyService.add(hobby);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Hobby hobby){
        hobbyService.update(hobby);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        hobbyService.delete(id);
        return new Result();
    }

}
