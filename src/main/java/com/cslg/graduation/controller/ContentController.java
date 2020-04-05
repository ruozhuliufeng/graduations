package com.cslg.graduation.controller;

import com.cslg.graduation.entity.Content;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.service.ContentService;
import com.cslg.graduation.util.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;

    @GetMapping("/findAll")
    public List<Content> findAll(){
        return contentService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Content> findPage(int page, int size){
        return contentService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Content> findList(@RequestBody Map<String, Object> searchMap){
        return contentService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Content> findPage(@RequestBody Map<String, Object> searchMap, int page, int size){
        return  contentService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Content findById(Integer id){
        return contentService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Content content){
        contentService.add(content);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Content content){
        contentService.update(content);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        contentService.delete(id);
        return new Result();
    }

}
