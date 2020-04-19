package com.cslg.graduation;

import com.cslg.graduation.entity.Active;
import com.cslg.graduation.mapper.ActiveMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 类描述 用户行为的测试类
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 16:09
 */
@SpringBootTest
public class ActiveMapperTest {

    @Autowired
    private ActiveMapper activeMapper;

    /**
     * 功能描述: 测试某个用户的浏览记录的条数
     * @author : ruozhuliufeng
     * @date : 2020/4/12 16:38
     */
    @Test
    public void testCountUserActive(){
        Active active = new Active();

        active.setUserId(1);
        active.setBlogId(2);
        active.setHits(5000);
        int rows = activeMapper.saveUserActive(active);
        if (rows>0){
            System.out.println("存在用户ID为："+active.getUserId()+",博客ID为："+active.getBlogId()+",点击量为："+active.getHits());
        }else{
            System.out.println("添加失败！");
        }
    }

    /**
     * 功能描述: 测试统计某个用户的浏览记录的条数
     * @author : ruozhuliufeng
     * @date : 2020/4/12 16:39
     */
    @Test
    public void testGetHitsOfUser(){
        Active active = new Active();
        Integer userId = 1;
        Integer blogId = 1;
        active.setUserId(userId);
        active.setBlogId(blogId);

        int hits = activeMapper.getHistsByUserActiveInfo(active);
        System.out.println("点击量为："+hits);
    }

    /**
     * 功能描述: 测试更新某个用户对某篇博客的点击量
     * @author : ruozhuliufeng
     * @date : 2020/4/12 16:43
     */
    @Test
    public void testUpdateHitsOfUser(){
        Active active = new Active();
        Integer userId = 1;
        Integer blogId = 1;
        Integer hits = 5000;
        active.setBlogId(blogId);
        active.setUserId(userId);
        active.setHits(hits);
        int rows = activeMapper.updateUserActive(active);
        if (rows>0){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }
    }

}
