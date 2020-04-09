package com.cslg.graduation.controller;

import com.cslg.graduation.entity.*;
import com.cslg.graduation.service.ContentService;
import com.cslg.graduation.service.HobbyService;
import com.cslg.graduation.service.StageService;
import com.cslg.graduation.util.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hobby")
public class HobbyController {

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private StageService stageService;
    @Autowired
    private ContentService contentService;

    @RequestMapping("/learn")
    public String learn(HttpSession httpSession){
        User currentUser = (User) httpSession.getAttribute("currentUser");
        Map<String,Object> searchMap = new HashMap<>(10);
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
    public String delete(Integer id){
        hobbyService.delete(id);
        return "redirect:/admin/hobby";
    }

}
