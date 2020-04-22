package com.cslg.graduation.task;

import com.cslg.graduation.entity.Active;
import com.cslg.graduation.entity.Attention;
import com.cslg.graduation.entity.Focus;
import com.cslg.graduation.entity.Similarity;
import com.cslg.graduation.service.ActiveService;
import com.cslg.graduation.service.AttentionService;
import com.cslg.graduation.service.FocusService;
import com.cslg.graduation.service.SimilarityService;
import com.cslg.graduation.util.HobbyRecommentUtils;
import com.cslg.graduation.util.RecommentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类描述 自动更新推荐列表和用户相似度
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/20 19:15
 */
@Configuration    //主要用于标记配置类，兼备Component的效果
@EnableScheduling //开启定时任务
public class ScheduleTask {

    @Autowired
    private ActiveService activeService;
    @Autowired
    private SimilarityService similarityService;
    @Autowired
    private FocusService focusService;
    @Autowired
    private AttentionService attentionService;
    /**
     * 功能描述: 定时任务
     * 每天中午12点更新相似度
     * @author : ruozhuliufeng
     * @date : 2020/4/20 19:19
     */
    @Scheduled(cron = "0 0 12 * * ?")
    private void configureTasks() {
        //更新博客相似度
        updateSimilarity();
        //更新兴趣相似度
        updateAttention();
        System.out.println("执行静态定时任务时间：" + LocalDateTime.now());
    }


    //博客更新相似度
    public void updateSimilarity() {
        //1.查询所有的用户浏览记录
        List<Active> userActiveDTOList = activeService.listAllUserActive();
        //2.调用推荐模块工具类的方法组装城一个ConcurrentHashMap来存储每个用户以及其对应的博客的点击量
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Integer>> activeMap = RecommentUtils.assembleUserBrower(userActiveDTOList);
        //3.调用推荐模块工具类的方法计算用户与用户之间的相似度
        List<Similarity> similarityList = RecommentUtils.calcSimilarityBetweenUsers(activeMap);
        //4.输出计算好的用户之间的相似度
        for (Similarity usim : similarityList) {
            System.out.println(usim.getUserId() + "\t" + usim.getUserRefId() + "\t" + usim.getSimilarity());
            //5.如果用户之间的 相似度已经存在于数据库就修改，不存在就添加
            if (similarityService.isExistUserSimilarity(usim)) {//修改
                boolean flag = similarityService.updateUserSimilarity(usim);
                if (flag) {
                    System.out.println("修改数据成功");
                } else {
                    System.out.println("修改数据失败");
                }
            } else {//新增
                boolean flag = similarityService.saveUserSimilarity(usim);
                if (flag) {
                    System.out.println("插入数据成功");
                } else {
                    System.out.println("插入数据失败");
                }
            }
        }
    }
    //兴趣更新相似度
    public void updateAttention() {
        //1.查询所有的用户浏览记录
        List<Focus> focusList = focusService.listAllFocus();
        //2.调用推荐模块工具类的方法组装城一个ConcurrentHashMap来存储每个用户以及其对应的博客的点击量
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Integer>> activeMap = HobbyRecommentUtils.assembleUserBrower(focusList);
        //3.调用推荐模块工具类的方法计算用户与用户之间的相似度
        List<Attention> attentionList = HobbyRecommentUtils.calcSimilarityBetweenUsers(activeMap);
        //4.输出计算好的用户之间的相似度
        for (Attention attention : attentionList) {
            //5.如果用户之间的 相似度已经存在于数据库就修改，不存在就添加
            if (attentionService.isExistAttention(attention)) {//修改
                boolean flag = attentionService.updateAttention(attention);
                if (flag) {
                    System.out.println("修改数据成功");
                } else {
                    System.out.println("修改数据失败");
                }
            } else {//新增
                boolean flag = attentionService.saveAttention(attention);
                if (flag) {
                    System.out.println("插入数据成功");
                } else {
                    System.out.println("插入数据失败");
                }
            }
        }
    }
}
