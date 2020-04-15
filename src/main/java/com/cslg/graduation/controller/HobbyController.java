package com.cslg.graduation.controller;

import com.cslg.graduation.dto.HobbyDTO;
import com.cslg.graduation.entity.*;
import com.cslg.graduation.service.*;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    /**
     * 功能描述:  跳转到兴趣学习页面
     *
     * @param sid         当前阶段id
     * @param httpSession 存放值
     * @return : 学习页面
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:03
     */
    @GetMapping("/learn")
    public String learn(Integer sid, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        if (currentUser == null) {
            httpSession.setAttribute("learnmsg", "尚未登录，没有兴趣信息！");
            return "/page/learn";
        }
        Map<String, Object> searchMap = new HashMap<>(10);
        //设置搜索条件
        searchMap.put("name", currentUser.getHname());
        List<Hobby> list = hobbyService.findList(searchMap);
        //获得阶段id：1,2,3
        String str = list.get(0).getSid();
        //分割id，获得字符串数组
        String[] strList = str.split(",");
        List<Stage> stageList = new ArrayList<>();
        for (int i = 0; i < strList.length; i++) {
            //将字符转换为字符串，获得阶段名称
            Stage stage = stageService.findById(Integer.valueOf(strList[i]));
            stageList.add(stage);
        }
        httpSession.setAttribute("stageList", stageList);
        Map<String, Object> map = new HashMap<>(5);
        //获得兴趣内容列表
        List<Content> contentList = new ArrayList<>();
        if (sid == null) {
            map.put("sid", stageList.get(0).getId());
        } else {
            //需要判断当前sid是否大于所处阶段的sid，
            Map<String, Object> search = new HashMap<>();
            search.put("sname", currentUser.getSname());
            List<Stage> listStage = stageService.findList(search);
            Integer sidd = listStage.get(0).getId();
            //设置内容完成状态
            /**
             * 将当前阶段的所有内容设置为未完成
             * 将低于当前阶段的所有内容设置为已完成
             * 不显示高于当前阶段的所有内容
             * 查找用户的所有博客，若用户对应的博客内容id中包含所学内容，将该内容设为已完成
             */
            if (sid < sidd) {//当前阶段低于所处阶段，所有内容设置为已完成
                map.put("sid", sid);
                contentList = contentService.findList(map);
                for (Content content : contentList) {
                    content.setStatus(1);
                }
            } else if (sid.equals(sidd)) {
                //所选阶段就是当前阶段
                map.put("sid", sid);
                contentList = contentService.findList(map);
                //这就是第一阶段，获取用户的所有博客，比对博客的hid与阶段内容是否对应，对应，则将该内容设为已完成
                Map<String, Object> blogSearch = new HashMap<>();
                blogSearch.put("user_id", currentUser.getId());
                List<Blog> searchBlogList = blogService.findList(blogSearch);
                for (Content content : contentList) {
                    for (Blog blog : searchBlogList) {
                        if (blog.getHid().equals(content.getId())) {
                            content.setStatus(1);
                        } else {
                            content.setStatus(0);
                        }
                    }
                }
            }
        }
        httpSession.setAttribute("contentList", contentList);
        return "/page/learn";
    }


    /**
     * 功能描述: 兴趣删除
     *
     * @param id 待删除的id
     * @return : 返回后台兴趣列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:05
     */
    @GetMapping("/delete")
    public String delete(Integer id) {
        hobbyService.delete(id);
        return "redirect:/admin/hobby";
    }

    /**
     * 功能描述: 修改博客
     *
     * @param id          待修改的博客id
     * @param httpSession 存放查找到的博客
     * @return : 返回页面地址
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:05
     */
    @GetMapping("/findById")
    public String findById(Integer id, HttpSession httpSession) {
        Hobby hobby = hobbyService.findById(id);
        httpSession.setAttribute("hobby", hobby);
        List<Category> categoryList = categoryService.findAll();
        httpSession.setAttribute("categoryList", categoryList);
        return "/admin/hobbyUpdate";
    }


    /**
     * 功能描述: 添加兴趣
     *
     * @param hobbyDTO 添加兴趣数据
     * @return : 返回后台兴趣列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:06
     */
    @PostMapping("/add")
    public String add(HobbyDTO hobbyDTO) {
        Hobby hobby = new Hobby();
        hobby.setName(hobbyDTO.getName());
        hobby.setSid(hobbyDTO.getSid());
        Map<String, Object> map = new HashMap<>();
        map.put("name", hobbyDTO.getCname());
        hobby.setCid(categoryService.findList(map).get(0).getId());
        hobbyService.add(hobby);
        return "redirect:/admin/hobby";
    }

    /**
     * 功能描述: 修改兴趣
     *
     * @param hobbyDTO 待修改的兴趣数据
     * @return : 返回后台兴趣列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 18:07
     */
    @PostMapping("/update")
    public String update(HobbyDTO hobbyDTO) {
        Hobby hobby = new Hobby();
        hobby.setName(hobbyDTO.getName());
        hobby.setSid(hobbyDTO.getSid());
        Map<String, Object> map = new HashMap<>();
        map.put("name", hobbyDTO.getCname());
        hobby.setCid(categoryService.findList(map).get(0).getId());
        hobbyService.add(hobby);
        return "redirect:/admin/hobby";
    }


    @GetMapping("/findPage")
    public PageResult<Hobby> findPage(int page, int size) {
        return hobbyService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Hobby> findList(@RequestBody Map<String, Object> searchMap) {
        return hobbyService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Hobby> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return hobbyService.findPage(searchMap, page, size);
    }

}
