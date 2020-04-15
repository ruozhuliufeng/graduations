package com.cslg.graduation;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.entity.Focus;
import com.cslg.graduation.service.FocusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.soap.Addressing;

/**
 * 类描述 用户与兴趣点击量
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/15 11:48
 */
@SpringBootTest
public class FocusTest {

    @Autowired
    private FocusService focusService;

    /**
     * 功能描述: 生成用户与兴趣点击量
     * @author : ruozhuliufeng
     * @date : 2020/4/15 11:49
     */
    @Test
    public void genFocusTest(){
        for (int i = 5; i < 105; i++) {
            for (int j = 1; j < 10; j++) {
                int random = (int) (Math.random() * 100 + 5);
                Focus focus = new Focus(i, j, random);
                focusService.saveFocus(focus);
            }
        }
    }
}
