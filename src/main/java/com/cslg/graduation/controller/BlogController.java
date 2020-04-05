package com.cslg.graduation.controller;

import com.cslg.graduation.entity.Blog;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.service.BlogService;
import com.cslg.graduation.util.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/blog")
public class BlogController {

    @Reference
    private BlogService blogService;

    @GetMapping("/findAll")
    public List<Blog> findAll(){
        return blogService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Blog> findPage(int page, int size){
        return blogService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Blog> findList(@RequestBody Map<String, Object> searchMap){
        return blogService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Blog> findPage(@RequestBody Map<String, Object> searchMap, int page, int size){
        return  blogService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Blog findById(Integer id){
        return blogService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Blog blog){
        blogService.add(blog);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Blog blog){
        blogService.update(blog);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        blogService.delete(id);
        return new Result();
    }

}
