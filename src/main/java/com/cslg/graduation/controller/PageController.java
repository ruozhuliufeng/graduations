package com.cslg.graduation.controller;

import com.cslg.graduation.entity.Content;
import com.cslg.graduation.entity.Hobby;
import com.cslg.graduation.entity.Stage;
import com.cslg.graduation.entity.User;
import com.cslg.graduation.service.ContentService;
import com.cslg.graduation.service.HobbyService;
import com.cslg.graduation.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面跳转Controller
 */
@Controller
public class PageController {
    @Autowired
    private HobbyService hobbyService;
    @Autowired
    private StageService stageService;
    @Autowired
    private ContentService contentService;
    //介绍页
    @RequestMapping("/info")
    public String infoPage(){
        return "/page/info";
    }
    //个人中心页
    @RequestMapping("/center")
    public String centerPage(){
        return "/page/center";
    }
    //修改页
    @RequestMapping("/centerUpdate")
    public String centerUpdatePage(){
        return "/page/userUpdate";
    }


    //学习页
    @RequestMapping("/learn")
    public String learnPage(HttpSession httpSession){
        User currentUser = (User) httpSession.getAttribute("currentUser");
        Map<String,Object> searchMap = new HashMap<>();
        searchMap.put("name", currentUser.getHname());
        List<Hobby> list = hobbyService.findList(searchMap);
        String str = list.get(0).getSid();
        String[] strList = str.split(",");
        List<Stage> stageList = new ArrayList<>();
        for (int i = 0; i < strList.length; i++) {
            Stage stage = stageService.findById(Integer.valueOf(strList[i]));
            stageList.add(stage);
        }
        httpSession.setAttribute("stageList",stageList);
        Map<String,Object> map = new HashMap<>(5);
        map.put("sid",stageList.get(0).getId());
        List<Content> contentList = contentService.findList(map);
        httpSession.setAttribute("contentList",contentList);
        return "/page/learn";
    }
    //博客页
    @RequestMapping("/forum")
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
    public String addTopicPage(){
        return "/forum/topicAdd";
    }

    //后台管理页
    @RequestMapping("/admin")
    public String adminPage(){
        return "forum/main";
    }
    //兴趣管理页
    @RequestMapping("/adminLearnPage")
    public String addPage(){
        return "admin/learning";
    }
    //帖子管理页
    @RequestMapping("/adminTopicPage")
    public String adminTopicPage(){
        return "admin/topic";
    }
    //用户管理
    @RequestMapping("/adminUserPage")
    public String adminUserPage(){
        return "admin/user";
    }
    //板块管理页
    @RequestMapping("/adminZonePage")
    public String adminZonePage(){
        return "admin/zone";
    }

}
