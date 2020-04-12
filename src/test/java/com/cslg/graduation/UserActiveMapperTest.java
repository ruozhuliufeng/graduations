package com.cslg.graduation;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.entity.Active;
import com.cslg.graduation.mapper.UserActiveMapper;
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
public class UserActiveMapperTest {

    @Autowired
    private UserActiveMapper userActiveMapper;

    /**
     * 功能描述: 测试某个用户的浏览记录的条数
     * @author : ruozhuliufeng
     * @date : 2020/4/12 16:38
     */
    @Test
    public void testCountUserActive(){
        UserActiveDTO userActiveDTO = new UserActiveDTO();
        userActiveDTO.setUserId(1);
        userActiveDTO.setBlogId(2);
        userActiveDTO.setHits(5000);
        int rows = userActiveMapper.saveUserActive(userActiveDTO);
        if (rows>0){
            System.out.println("存在用户ID为："+userActiveDTO.getUserId()+",博客ID为："+userActiveDTO.getBlogId()+",点击量为："+userActiveDTO.getHits());
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
        UserActiveDTO userActiveDTO = new UserActiveDTO();
        Integer userId = 1;
        Integer blogId = 1;
        userActiveDTO.setUserId(userId);
        userActiveDTO.setBlogId(blogId);

        int hits = userActiveMapper.getHistsByUserActiveInfo(userActiveDTO);
        System.out.println("点击量为："+hits);
    }

    /**
     * 功能描述: 测试更新某个用户对某篇博客的点击量
     * @author : ruozhuliufeng
     * @date : 2020/4/12 16:43
     */
    @Test
    public void testUpdateHitsOfUser(){
        UserActiveDTO userActiveDTO = new UserActiveDTO();
        Integer userId = 1;
        Integer blogId = 1;
        Integer hits = 5000;
        userActiveDTO.setBlogId(blogId);
        userActiveDTO.setUserId(userId);
        userActiveDTO.setHits(hits);
        int rows = userActiveMapper.updateUserActive(userActiveDTO);
        if (rows>0){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }
    }

}
