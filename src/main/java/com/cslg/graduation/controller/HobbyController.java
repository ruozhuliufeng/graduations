package com.cslg.graduation.controller;

import com.cslg.graduation.dto.HobbyDTO;
import com.cslg.graduation.entity.*;
import com.cslg.graduation.service.*;
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
        if (currentUser.getHname() == null){
            httpSession.setAttribute("learnmsg","尚未选择兴趣，请选择兴趣后访问此页面");
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
        //需要判断当前sid是否大于所选阶段的sid，
        if(sid==null){
            sid = stageList.get(0).getId();
        }
        Integer currentSid = getCurrentSid(currentUser,stageList);

        /* 设置完成状态
         * 将当前阶段的所有内容设置为未完成
         * 将低于当前阶段的所有内容设置为已完成
         * 不显示高于当前阶段的所有内容
         * 查找用户的所有博客，若用户对应的博客内容id中包含所学内容，将该内容设为已完成
         */
//        if (sid < currentSid) {//当前阶段低于所处阶段，所有内容设置为已完成
//            map.put("sid", sid);
//            contentList = contentService.findList(map);
//            for (Content content : contentList) {
//                content.setStatus(1);
//            }
//        } else if (sid.equals(currentSid)) {
//            //所选阶段就是当前阶段
//            map.put("sid", sid);
//            //当前阶段的所有兴趣内容
//            contentList = contentService.findList(map);
//            //这就是第一阶段，获取用户的所有博客，比对博客的hid与阶段内容是否对应，对应，则将该内容设为已完成
//            Map<String, Object> blogSearch = new HashMap<>();
//            blogSearch.put("user_id", currentUser.getId());
//            //获得当前用户的所有博客
//            List<Blog> searchBlogList = blogService.findList(blogSearch);
//            for (Content content : contentList) {
//                for (Blog blog : searchBlogList) {
//                    //如果博客的兴趣内容hid和兴趣内容id相对应，设为已完成，不然设为未完成
//                    if (blog.getHid().equals(content.getId())) {
//                        content.setStatus(1);
//                    } else {
//                        content.setStatus(0);
//                    }
//                }
//            }
//        }
        map.put("sid",sid);
        contentList = contentService.findList(map);
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
     * 功能描述: 修改兴趣
     *
     * @param id          待修改的兴趣id
     * @param httpSession 存放查找到的兴趣
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

    public Integer getCurrentSid(User user,List<Stage> stageList){
        for(Stage stage:stageList){
            Integer currentSid = null;
            if (stage.getName().equals(user.getSname())){
                currentSid =  stage.getId();
            }
            return currentSid;
        }
        return null;
    }

}
