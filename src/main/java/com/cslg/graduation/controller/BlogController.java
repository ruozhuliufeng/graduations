package com.cslg.graduation.controller;

import com.cslg.graduation.dto.BlogInputDTO;
import com.cslg.graduation.entity.Blog;
import com.cslg.graduation.entity.Category;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.service.BlogService;
import com.cslg.graduation.service.CategoryService;
import com.cslg.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    //博客页
    @RequestMapping("/")
    public String forumPage(){

        return "/forum/main";
    }
    //帖子详情页
    @RequestMapping("/detail")
    public String detailPage(){

        return "/forum/topicDetails";
    }
    //添加帖子页
    @RequestMapping("/addTopic")
    public String addTopicPage(HttpSession httpSession) {
        List<Category> categoryList = categoryService.findAll();
        httpSession.setAttribute("categoryList",categoryList);
        return "/forum/topicAdd";
    }

    @PostMapping("/add")
    public String add(BlogInputDTO blogInputDTO){
        Date publishTime = new Date();
        Date modifyTime = new Date();
        Map<String,Object> map = new HashMap<>();
        map.put("name",blogInputDTO.getCname());
        Blog blog = new Blog();
        blog.setPublishTime(publishTime);
        blog.setModifyTime(modifyTime);
        blog.setCid(categoryService.findList(map).get(0).getId());
        blog.setTitle(blogInputDTO.getTitle());
        blog.setContent(blogInputDTO.getContent());
        blog.setGood(0);
        blog.setTop(0);
        blogService.add(blog);
        return "redirect:/blog/";
    }

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


    @PostMapping("/update")
    public Result update(@RequestBody Blog blog){
        blogService.update(blog);
        return new Result();
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        blogService.delete(id);
        return "redirect:/admin/blog";
    }

}
