package com.cslg.graduation.controller;

import com.cslg.graduation.dto.ContentDTO;
import com.cslg.graduation.entity.Content;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.service.ContentService;
import com.cslg.graduation.util.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    //点击完成任务
    @GetMapping("/complete")
    public String complete(Integer id){
        Content content = contentService.findById(id);
        content.setStatus(1);
        contentService.update(content);
        return "redirect:/hobby/learn";
    }


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
    public String add( ContentDTO contentDTO){
        Content content = new Content();
        content.setName(content.getName());
        content.setStatus(0);
        content.setClink(contentDTO.getClink());
        content.setNote(contentDTO.getNote());

        contentService.add(content);
        return "redirect:/admin/content";
    }

    @PostMapping("/update")
    public String update(Content content){
        contentService.update(content);
        return "redirect:/admin/content";
    }


    @GetMapping("/delete")
    public String delete(Integer id){
        contentService.delete(id);
        return "redirect:/admin/content";
    }

}
