package com.cslg.graduation;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.dto.UserSimilarityDTO;
import com.cslg.graduation.entity.Attention;
import com.cslg.graduation.entity.Blog;
import com.cslg.graduation.entity.Focus;
import com.cslg.graduation.entity.Hobby;
import com.cslg.graduation.service.AttentionService;
import com.cslg.graduation.service.FocusService;
import com.cslg.graduation.service.HobbyService;
import com.cslg.graduation.util.HobbyRecommentUtils;
import com.cslg.graduation.util.RecommentUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类描述 测试推荐兴趣
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/15 11:58
 */
@SpringBootTest
public class HobbyRecommendTest {

    @Autowired
    private FocusService focusService;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private HobbyService hobbyService;


    /**
     * 功能描述: 测试计算用户的相似度
     * @author : ruozhuliufeng
     * @date : 2020/4/12 21:34
     */
    @Test
    public void testCalcSimilarityBetweenUser(){
        //1.查询所有的用户浏览记录
        List<Focus> focusList = focusService.listAllFocus();
        //2.调用推荐模块工具类的方法组装城一个ConcurrentHashMap来存储每个用户以及其对应的博客的点击量
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer,Integer>> activeMap = HobbyRecommentUtils.assembleUserBrower(focusList);
        //3.调用推荐模块工具类的方法计算用户与用户之间的相似度
        List<Attention> attentionList = HobbyRecommentUtils.calcSimilarityBetweenUsers(activeMap);
        //4.输出计算好的用户之间的相似度
        for (Attention attention:attentionList){
            //5.如果用户之间的 相似度已经存在于数据库就修改，不存在就添加
            if (attentionService.isExistAttention(attention)){//修改
                boolean flag = attentionService.updateAttention(attention);
                if (flag){
                    System.out.println("修改数据成功");
                }else {
                    System.out.println("修改数据失败");
                }
            }else {//新增
                boolean flag = attentionService.saveAttention(attention);
                if (flag){
                    System.out.println("插入数据成功");
                }else {
                    System.out.println("插入数据失败");
                }
            }
        }
    }




    /**
     * 功能描述: 测试获取被推荐的兴趣（从被推荐的博客列表中找出点击量最大的博客作为推荐的博客）
     * @author : ruozhuliufeng
     * @date : 2020/4/12 22:27
     */
    @Test
    public void testGetRecommendateBlog(){
        // 1.查询出某个用户与其他用户的相似度列表
        List<Attention> attentionList = attentionService.listAttentionByUId(8);
        // 2.获得所有的用户的浏览记录
        List<Focus> focusList = focusService.listAllFocus();
        for (Attention attention:attentionList){
            System.out.println(attention.getUserId()+"\t"
                    +attention.getUserRefId()+"\t"
                    +attention.getAttention());
        }
        // 3.找出与id为8的用户浏览行为最相似的前3个用户
        List<Integer> userIds = HobbyRecommentUtils.getSimilarityBetweenUsers(15,attentionList,5);
        // 打印输出
        System.out.println("与"+15+"号用户最相似的前5个用户是：");
        for (Integer userRefId:userIds){
            System.out.println(userRefId);
        }
        // 4.获得应该推荐给8号用户的博客列表
        List<Integer> recommendateHobby = HobbyRecommentUtils.getRecommendateHobby(15,userIds,focusList);
        for (Integer hobbyId:recommendateHobby){
            System.out.println("被推荐的博客id是："+hobbyId);
        }
        // 5.找出博客列表中的所有博客
        List<Hobby> hobbyList = new ArrayList<>();
        for(Integer hobbyId:recommendateHobby){
            Hobby hobby= hobbyService.findById(hobbyId);
            hobbyList.add(hobby);
        }
        Hobby maxHitsHobby = HobbyRecommentUtils.findMaxHitsHobby(hobbyList);
        List<Hobby> recommendatesHobby = new ArrayList<>();
        recommendatesHobby.add(maxHitsHobby);
        // 6.打印输出
        for (Hobby hobby:recommendatesHobby){
            System.out.println("被推荐的博客："+hobby);
        }
    }
}
