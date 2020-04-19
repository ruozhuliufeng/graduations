package com.cslg.graduation.controller;

import com.cslg.graduation.dto.ContentDTO;
import com.cslg.graduation.entity.Content;
import com.cslg.graduation.entity.Hobby;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.entity.Stage;
import com.cslg.graduation.service.ContentService;
import com.cslg.graduation.service.HobbyService;
import com.cslg.graduation.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private StageService stageService;
    @Autowired
    private HobbyService hobbyService;

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
        content.setName(contentDTO.getName());
        content.setStatus(0);
        content.setSid(getSidFromStage(contentDTO.getName(),contentDTO.getSname()));
        content.setClink(contentDTO.getClink());
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

    /**
     * 功能描述: 获取阶段id
     * @param hname 兴趣名称
     * @param sname 阶段名称
     * @return : java.lang.Integer
     * @author : ruozhuliufeng
     * @date : 2020/4/15 17:18
     */
    public Integer getSidFromStage(String hname,String sname){
        Integer sid = null;
        Map<String,Object> map = new HashMap<>();
        map.put("name",hname);
        Hobby hobby = hobbyService.findList(map).get(0);
        String[] strs = hobby.getSid().split(",");
        for (int i = 0; i < strs.length; i++) {
            Stage stage = stageService.findById(Integer.valueOf(strs[i]));
            if (sname.equals(stage.getName())){
                return Integer.valueOf(strs[i]);
            }
        }
        return null;
    }
}
