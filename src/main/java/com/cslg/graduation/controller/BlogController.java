package com.cslg.graduation.controller;

import com.cslg.graduation.dto.BlogInputDTO;
import com.cslg.graduation.dto.BlogOutputDTO;
import com.cslg.graduation.dto.CategoryOutputDTO;
import com.cslg.graduation.entity.Blog;
import com.cslg.graduation.entity.Category;
import com.cslg.graduation.entity.Comment;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.service.BlogService;
import com.cslg.graduation.service.CategoryService;
import com.cslg.graduation.service.CommentService;
import com.cslg.graduation.service.UserService;
import com.cslg.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    //博客页
    @GetMapping("/")
    public String forumPage(Integer id,HttpSession httpSession){
        List<Category> categoryList = categoryService.findAll();
        httpSession.setAttribute("categoryList",categoryList);
        List<BlogOutputDTO> blogList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>(5);
        if (id==null){
            map.put("cid",1);
        }else{
            map.put("cid",id);
        }
        List<Blog> list = blogService.findList(map);
        if(list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Blog blog = list.get(i);
                BlogOutputDTO blogOutputDTO = new BlogOutputDTO();
                blogOutputDTO.setUserName(userService.findById(blog.getUserId()).getUsername());
                blogOutputDTO.setPublishTime(blog.getPublishTime());
                blogOutputDTO.setModifyTime(blog.getModifyTime());
                blogOutputDTO.setTitle(blog.getTitle());
                blogOutputDTO.setId(blog.getId());
                blogOutputDTO.setTop(blog.getTop());
                blogOutputDTO.setGood(blog.getGood());
                blogList.add(blogOutputDTO);
            }
        }
        httpSession.setAttribute("blogList",blogList);
        httpSession.setAttribute("msg","当前分类暂无博客，请查看其它分类！");
        return "/forum/main";

    }
    //帖子详情页
    @GetMapping("/detail")
    public String detailPage(Integer id,HttpSession httpSession){
        Blog blog = blogService.findById(id);
        BlogOutputDTO blogOutputDTO = new BlogOutputDTO();
        //设置输出值
        blogOutputDTO.setUserName(userService.findById(blog.getUserId()).getUsername());
        blogOutputDTO.setEmail(userService.findById(blog.getUserId()).getEmail());
        blogOutputDTO.setType(userService.findById(blog.getUserId()).getType());
        blogOutputDTO.setTitle(blog.getTitle());
        blogOutputDTO.setPublishTime(blog.getPublishTime());
        blogOutputDTO.setModifyTime(blog.getModifyTime());
        blogOutputDTO.setContent(blog.getContent());
        blogOutputDTO.setUserId(blog.getUserId());
        blogOutputDTO.setId(blog.getId());
        httpSession.setAttribute("blog",blogOutputDTO);
        Map<String,Object> map = new HashMap<>(5);
        map.put("top_id",blog.getId());
        List<Comment> commentList = commentService.findList(map);
        if (commentList!=null && commentList.size()>0){
            httpSession.setAttribute("commentList",commentList);
        }else {
            httpSession.setAttribute("msg","还没有人评论这篇博文");
        }
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
