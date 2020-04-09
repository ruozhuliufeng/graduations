package com.cslg.graduation.controller;

import com.cslg.graduation.entity.Comment;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.service.CommentService;
import com.cslg.graduation.util.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/comment")
public class CommentController {

    @Reference
    private CommentService commentService;

    @GetMapping("/findAll")
    public List<Comment> findAll(){
        return commentService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Comment> findPage(int page, int size){
        return commentService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Comment> findList(@RequestBody Map<String, Object> searchMap){
        return commentService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Comment> findPage(@RequestBody Map<String, Object> searchMap, int page, int size){
        return  commentService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Comment findById(Integer id){
        return commentService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Comment comment){
        commentService.update(comment);
        return new Result();
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        commentService.delete(id);
        return "redirect:/admin/comment";
    }

}
