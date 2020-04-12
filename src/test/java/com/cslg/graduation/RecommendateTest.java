package com.cslg.graduation;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.service.UserActiveService;
import com.cslg.graduation.service.UserSimilarityService;
import com.cslg.graduation.util.RecommentUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类描述 测试推荐模块中的一些功能
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 16:54
 */
@SpringBootTest
public class RecommendateTest {

    @Autowired
    private UserActiveService userActiveService;
    @Autowired
    private UserSimilarityService userSimilarityService;

    /**
     * 功能描述: 测试列出所有用户的购买行为的方法
     * @author : ruozhuliufeng
     * @date : 2020/4/12 16:56
     */
    @Test
    public void testListAlluserActive(){
        //1.查询出所有用户对所有博客的浏览记录
        List<UserActiveDTO> userActiveDTOList = userActiveService.listAllUserActive();
        //2.输出浏览记录列表
        for (UserActiveDTO userActiveDTO : userActiveDTOList){
            System.out.println(userActiveDTO.getUserId()+" "+userActiveDTO.getBlogId()+" "+userActiveDTO.getHits());
        }
    }
    /**
     * 功能描述: 测试组装用户行为数据的方法
     * @author : ruozhuliufeng
     * @date : 2020/4/12 17:05
     */
    @Test
    public void testAssembleUserBehavior(){
        // 1.查询所有的用户浏览记录
        List<UserActiveDTO> userActiveDTOList = userActiveService.listAllUserActive();

        // 2.调用推荐模块工具类的方法组装城一个ConcurrentHashMap来存储每个用户以及其对应的博客的点击量
        ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,Integer>> activeMap = RecommentUtils.assembleUserBrower(userActiveDTOList);
        // 3.输出封装后的map的大小(也就是多少个用户的浏览记录)
        System.out.println(activeMap.size());
    }
}
