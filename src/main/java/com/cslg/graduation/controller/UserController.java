package com.cslg.graduation.controller;

import com.cslg.graduation.dto.UserDTO;
import com.cslg.graduation.entity.*;
import com.cslg.graduation.service.*;
import com.cslg.graduation.util.HobbyRecommentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private FocusService focusService;
    @Autowired
    private HobbyService hobbyService;
    @Autowired
    private StageService stageService;
    @Autowired
    private ActiveService activeService;
    //登录
    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession httpSession){
        User currentUser = userService.login(userDTO);
        if (currentUser==null){
            httpSession.setAttribute("msg","用户名或密码错误");
            return "redirect:/err";
        }
        httpSession.setAttribute("currentUser",currentUser);
        return "redirect:/";

    }

    //注册
    @PostMapping("/register")
    public String register(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setSex(userDTO.getSex());
        user.setType(0);
        user.setStatus(0);
        userService.add(user);
        return "redirect:/login";
    }

    /**
     *  退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
    /**
     * 管理员登录
     */
    @PostMapping("/adminLogin")
    public String adminLogin(UserDTO userDTO,HttpSession httpSession){
        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getPassword());
        User currentUser = userService.login(userDTO);
        if (currentUser.getType()!=1){
            httpSession.setAttribute("msg","登录失败，请检查输入是否正确或是否为管理员");
            return "redirect:/admin/loginPage";
        }
        httpSession.setAttribute("currentUser",currentUser);
        return "redirect:/admin";
    }

    //个人中心页
    @RequestMapping("/center")
    public String centerPage(HttpSession httpSession){
        //从数据库中获取最新数据
        User currentUser = (User) httpSession.getAttribute("currentUser");
        currentUser = userService.findById(currentUser.getId());
        httpSession.setAttribute("currentUser",currentUser);
        return "/page/center";
    }
    //修改页
    @RequestMapping("/centerUpdate")
    public String centerUpdatePage(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("currentUser");
        boolean flag = activeService.findNewsUser(user.getId());
        Hobby recommendHobby = new Hobby();
        if (flag){//是新用户，推荐点击量最高的
            recommendHobby = hobbyService.findMaxHobby();
        }else {
            recommendHobby(user.getId(), 10);
        }
        httpSession.setAttribute("recommendHobby",recommendHobby);

        return "/page/userUpdate";
    }

    @GetMapping("/findById")
    public String findById(Integer id,HttpSession httpSession){
        User user = userService.findById(id);
        httpSession.setAttribute("user",user);
        Map<String,Object> map = new HashMap<>();
        map.put("name",user.getSname());
        List<Stage> stageList = stageService.findList(map);
        httpSession.setAttribute("user",user);
        httpSession.setAttribute("stageList",stageList);
        return "/admin/userUpdate";
    }

    @PostMapping("/update")
    public String update(UserDTO userDTO){
        User user = userService.findById(userDTO.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        int type;
        if ("管理员".equals(userDTO.getTname())){
            type=1;
        }else {
            type=0;
        }
        user.setType(type);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        userService.update(user);
        return "redirect:/admin/user";
    }

    /**
     * 个人修改
     * @param userDTO
     * @return
     */
    @PostMapping("/mupdate")
    public String mupdate(UserDTO userDTO){
        User user = userService.findById(userDTO.getId());
        if (user==null){
            //返回错误页面
            return "/err";
        }
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setHname(userDTO.getHname());
        user.setSname("第一阶段");
        user.setSex(userDTO.getSex());
        userService.update(user);
        return "redirect:/user/center";
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        userService.delete(id);
        return "redirect:/admin/user";
    }



    /**
     * 功能描述: 根据用户id获得推荐的兴趣列表
     * @param userId 用户id
     * @param topN 与用户最相近的前topN个用户
     * @return : 推荐的兴趣列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 23:08
     */
    public List<Hobby> recommendHobbyList(Integer userId,Integer topN){
        List<Hobby> hobbyList = new ArrayList<>();
        // 1.查询出某个用户与其他用户的相似度列表
        List<Attention> userAttentionList = attentionService.listAttentionByUId(userId);
        // 2.获得所有的用户的浏览记录
        List<Focus> focusList = focusService.listAllFocus();

        // 3.找出与id为userId的用户浏览行为最相似的前topN个用户
        List<Integer> userIds = HobbyRecommentUtils.getSimilarityBetweenUsers(userId,userAttentionList,topN);
        //去除自己
//        userIds.remove(0);
        // 4.获得应该推荐给userId号用户的博客列表
        List<Integer> list = HobbyRecommentUtils.getRecommendateHobby(userId,userIds,focusList);
        // 列表去重
        List<Integer> recommendatesHobby = list.stream().distinct().collect(Collectors.toList());
        for (Integer hobbyId:recommendatesHobby){
            hobbyList.add(hobbyService.findById(hobbyId));
        }
        return hobbyList;
    }


    /**
     * 功能描述: 推荐的列表中点击量最高的兴趣
     * @param userId 用户id
     * @param topN 与用户ID最相似的topN个用户
     * @return : 点击量最高的博客
     * @author : ruozhuliufeng
     * @date : 2020/4/12 23:21
     */
    public Hobby recommendHobby(Integer userId,Integer topN){
        // 5.调用推荐模块工具类获得最高点击量的博客
        return HobbyRecommentUtils.findMaxHitsHobby(recommendHobbyList(userId,topN));
    }
}
