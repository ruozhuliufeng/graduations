package com.cslg.graduation.controller.admin;

import com.cslg.graduation.dto.*;
import com.cslg.graduation.entity.*;
import com.cslg.graduation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理的Controller
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;
    @Autowired
    private AdviceService adviceService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HobbyService hobbyService;
    @Autowired
    private StageService stageService;
    //后台登录页
    @RequestMapping("/loginPage")
    public String loginPage(){
        return "/admin/login";
    }
    //后台登录
    @RequestMapping("/login")
    public String login(UserDTO userDTO,HttpSession httpSession){
        User adminUser = userService.login(userDTO);
        if (adminUser!=null){
            httpSession.setAttribute("adminUser",adminUser);
            return "redirect:/admin/indexPage";
        }
        return "/admin/login";

    }
    //后台主页
    @RequestMapping("/indexPage")
    public String indexPage(HttpSession httpSession){
        User adminUser = (User) httpSession.getAttribute("adminUser");
        if (adminUser==null){
            //用户未登录
            return "/admin/login";
        }
        return "/admin/index";
    }
    //用户管理
    @RequestMapping("/user")
    public String userPage(HttpSession httpSession){
        List<User> userList = userService.findAll();
        httpSession.setAttribute("userList",userList);
        return "/admin/user";
    }
    //用户修改
    @RequestMapping("/userUpdate")
    public String userUpdate(Integer id,HttpSession httpSession){
        return "/admin/userUpdate";
    }
    //建议管理
    @RequestMapping("/advice")
    public String advice(HttpSession httpSession){
        List<Advice> adviceList = adviceService.findAll();
        httpSession.setAttribute("adviceList",adviceList);
        return "/admin/advice";
    }
    //评论管理
    @RequestMapping("/comment")
    public String comment(HttpSession httpSession){
        List<Comment> comments = commentService.findAll();
        List<CommentOutPutDTO> commentList = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            CommentOutPutDTO commentOutPutDTO;
            Comment comment = comments.get(i);
            String nickName = userService.findById(comment.getUserId()).getUsername();
            String topicName = blogService.findById(comment.getTopicId()).getTitle();
            commentOutPutDTO = new CommentOutPutDTO(comment.getId(),comment.getPublishTime(),comment.getModifyTime(),topicName,nickName,comment.getTitle(),comment.getContent());
            commentList.add(commentOutPutDTO);
        }
        httpSession.setAttribute("commentList",commentList);
        return "/admin/comment";
    }
    //帖子管理
    @RequestMapping("/topic")
    public String topic(HttpSession httpSession){
        List<Blog> blogs = blogService.findAll();
        List<BlogOutputDTO> blogList = new ArrayList<>();
        for (int i = 0; i < blogs.size(); i++) {
            Blog blog = blogs.get(i);
            String categoryName = categoryService.findById(blog.getCid()).getName();
            String userName = userService.findById(blog.getUserId()).getUsername();
            BlogOutputDTO blogOutputDTO = new BlogOutputDTO(blog.getId(),blog.getPublishTime(),blog.getModifyTime(),blog.getTitle(),blog.getContent(),categoryName,userName,blog.getGood(),blog.getTop());
            blogList.add(blogOutputDTO);
        }
        httpSession.setAttribute("blogList",blogList);
        return "/admin/topic";
    }
    //修改帖子
    //兴趣管理
    @RequestMapping("/hobby")
    public String hobby(HttpSession httpSession){
        List<Hobby> hobbies = hobbyService.findAll();
        List<HobbyOutputDTO> hobbyList = new ArrayList<>();
        for (int i = 0; i < hobbies.size(); i++) {
            Hobby hobby = hobbies.get(i);
            String[] strings = hobby.getSid().split(",");
            String sname = stageService.findById(Integer.valueOf(strings[strings.length-1])).getName();
            HobbyOutputDTO hobbyOutputDTO = new HobbyOutputDTO(hobby.getId(),hobby.getName(),sname);
            hobbyList.add(hobbyOutputDTO);
        }
        httpSession.setAttribute("hobbyList",hobbyList);
        return "/admin/hobby";
    }
    //修改兴趣
    //分类管理
    @RequestMapping("/category")
    public String category(HttpSession httpSession){
        List<Category> categories = categoryService.findAll();
        List<CategoryOutputDTO> categoryList = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            String userName = userService.findById(category.getMasterId()).getUsername();
            CategoryOutputDTO categoryOutputDTO = new CategoryOutputDTO(category.getId(),category.getName(),userName);
            categoryList.add(categoryOutputDTO);
        }
        httpSession.setAttribute("categoryList",categoryList);
        return "/admin/category";
    }
    //内容管理
    @RequestMapping("/content")
    public String content(HttpSession httpSession){

        return "/admin/content";
    }
    //阶段管理
    @RequestMapping("/stage")
    public String stage(HttpSession httpSession){
        List<Stage> stageList =  stageService.findAll();
        httpSession.setAttribute("stageList",stageList);
        return "/admin/stage";
    }

}
