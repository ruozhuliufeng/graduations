package com.cslg.graduation.controller;

import com.cslg.graduation.dto.BlogInputDTO;
import com.cslg.graduation.dto.BlogOutputDTO;
import com.cslg.graduation.dto.CategoryOutputDTO;
import com.cslg.graduation.dto.CommentOutPutDTO;
import com.cslg.graduation.entity.*;
import com.cslg.graduation.service.*;
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

    /**
     * 功能描述: 博客页
     * @param id 分类id
     * @param httpSession 存放分类数据、博客数据
     * @return : 返回博客列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:25
     */
    @GetMapping("/")
    public String forumPage(Integer id, HttpSession httpSession) {
        List<Category> categoryList = categoryService.findAll();
        httpSession.setAttribute("categoryList", categoryList);
        List<BlogOutputDTO> blogList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(5);
        if (id == null) {
            map.put("cid", 1);
        } else {
            map.put("cid", id);
        }
        List<Blog> list = blogService.findList(map);
        if (list != null && list.size() > 0) {
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
        } else {
            httpSession.setAttribute("categorymsg", "当前分类下没有博客");
        }
        httpSession.setAttribute("blogList", blogList);
        return "/forum/main";

    }

    /**
     * 功能描述: 博客详情页
     * @param id 博客id
     * @param httpSession 存放博客数据
     * @return : 博客详情页地址
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:24
     */
    @GetMapping("/detail")
    public String detailPage(Integer id, HttpSession httpSession) {
        Blog blog = blogService.findById(id);
        //点击量+1
        Integer hits = blog.getHits();
        if (hits==null){
            hits = 1 ;
        }else {
            hits++;
        }
        blog.setHits(hits);
        blogService.update(blog);

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
        httpSession.setAttribute("blog", blogOutputDTO);
        Map<String, Object> map = new HashMap<>(5);
        map.put("top_id", blog.getId());
        //当前博客的评论
        List<Comment> comments = commentService.findList(map);
        List<CommentOutPutDTO> commentList = new ArrayList<>();
        if (comments != null && comments.size() > 0) {
            for (Comment comment : comments) {
                String userName = userService.findById(comment.getUserId()).getUsername();
                CommentOutPutDTO commentOutPutDTO = new CommentOutPutDTO();
                commentOutPutDTO.setTitle(comment.getTitle());
                commentOutPutDTO.setContent(comment.getContent());
                commentOutPutDTO.setUserName(userName);
                commentList.add(commentOutPutDTO);
            }
            httpSession.setAttribute("commentList", commentList);
        }


        return "/forum/topicDetails";
    }

    /**
     * 功能描述: 添加博客页
     * @param httpSession 存放相关数据
     * @return : 博客添加地址
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:11
     */
    @RequestMapping("/addTopic")
    public String addTopicPage(Integer hid,HttpSession httpSession) {
        //获取兴趣内容id，1 为管理员发布，不可占用
        if (hid==null){
            hid = 1;
        }
        httpSession.setAttribute("hid",hid);
        List<Category> categoryList = categoryService.findAll();
        httpSession.setAttribute("categoryList", categoryList);
        return "/forum/topicAdd";
    }

    /**
     * 功能描述: 添加博客
     * @param blogInputDTO 待添加的博客数据
     * @return : 返回博客列表页
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:19
     */
    @PostMapping("/add")
    public String add(BlogInputDTO blogInputDTO) {
        Date publishTime = new Date();
        Date modifyTime = new Date();

        Map<String, Object> map = new HashMap<>();
        map.put("name", blogInputDTO.getCname());
        Blog blog = new Blog();
        blog.setPublishTime(publishTime);
        blog.setModifyTime(modifyTime);
        blog.setCid(categoryService.findList(map).get(0).getId());
        blog.setTitle(blogInputDTO.getTitle());
        blog.setContent(blogInputDTO.getContent());
        blog.setUserId(blogInputDTO.getUserId());
        blog.setHid(blogInputDTO.getHid());
        blog.setGood(0);
        blog.setTop(0);
        blog.setHits(0);
        if (blogInputDTO.getHid()!=1){//1 :管理员博客 其他：兴趣内容id
            //TODO
        }
        blogService.add(blog);
        return "redirect:/blog/";
    }

    /**
     * 功能描述: 删除博客
     * @param id 待删除的博客id
     * @return : 返回后台博客列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:23
     */
    @GetMapping("/delete")
    public String delete(Integer id) {
        blogService.delete(id);
        return "redirect:/admin/blog";
    }


    @GetMapping("/findAll")
    public List<Blog> findAll() {
        return blogService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Blog> findPage(int page, int size) {
        return blogService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Blog> findList(@RequestBody Map<String, Object> searchMap) {
        return blogService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Blog> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return blogService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Blog findById(Integer id) {
        return blogService.findById(id);
    }


    @PostMapping("/update")
    public Result update(@RequestBody Blog blog) {
        blogService.update(blog);
        return new Result();
    }


}
