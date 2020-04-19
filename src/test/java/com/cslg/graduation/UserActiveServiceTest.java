package com.cslg.graduation;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.entity.Active;
import com.cslg.graduation.service.ActiveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 类描述 用于测试UserActiveService接口的一些方法
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 16:48
 */
@SpringBootTest
public class UserActiveServiceTest {

    @Autowired
    private ActiveService activeService;

    /**
     * 功能描述: 测试查询所有的用户行为
     * @author : ruozhuliufeng
     * @date : 2020/4/12 16:50
     */
    @Test
    public void testListAllUserActive(){
        List<Active> list = activeService.listAllUserActive();
        System.out.println(list.size());
    }

    /**
     * 功能描述: 测试更新用户行为数据
     * @author : ruozhuliufeng
     * @date : 2020/4/12 16:51
     */
    @Test
    public void testSaveUserActive(){
        Active userActiveDTO = new Active();
        userActiveDTO.setUserId(1);
        userActiveDTO.setBlogId(2);
        userActiveDTO.setHits(6000);
        boolean flag = activeService.saveUserActive(userActiveDTO);
        if (flag){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }
    }
}
