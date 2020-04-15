package com.cslg.graduation;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.entity.User;
import com.cslg.graduation.service.UserActiveService;
import com.cslg.graduation.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 类描述 生成100个测试用户
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 20:32
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserActiveService userActiveService;

    /**
     * 功能描述: 生成100个测试用户，并选择相关的兴趣
     *
     * @author : ruozhuliufeng
     * @date : 2020/4/12 20:45
     */
    @Test
    public void genUserTest() {
        //50 男 50 女
        for (int i = 90; i < 100; i++) {
            String usernname = "testUser" + i;
            String password = "testUser" + i;
            String email = "testUser" + i + "@test.com";
            String sex = "女";
            Integer status = 1;
            String hname = "按摩";
            String sname = "第一阶段";
            Integer type = 0;
            User user = new User(usernname, password, email, sex, status, hname, sname, type);
            userService.add(user);
        }
    }

    /**
     * 功能描述: 生成用户与博客之间的点击量
     *
     * @author : ruozhuliufeng
     * @date : 2020/4/12 20:46
     */
    @Test
    public void genActiveTest() {
        // 兴趣是java的，生成500-7000随机点击量
        for (int i = 5; i < 14; i++) {
            for (int j = 5; j < 11; j++) {
                int random = (int) (Math.random() * 7000 + 500);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
        //兴趣是绘画的，生成600-5000随机点击量
        for (int i = 15; i < 24; i++) {
            for (int j = 41; j < 45; j++) {
                int random = (int) (Math.random() * 5000 + 600);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
        //兴趣是厨房的，生成400-4500随机点击量
        for (int i = 25; i < 34; i++) {
            for (int j = 27; j < 30; j++) {
                int random = (int) (Math.random() * 4500 + 400);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
        //兴趣是女士形象的，生成700-6000随机点击量
        for (int i = 35; i < 44; i++) {
            for (int j = 22; j < 26; j++) {
                int random = (int) (Math.random() * 6000 + 700);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
        //兴趣是男士形象的，生成400-4000随机点击量
        for (int i = 45; i < 54; i++) {
            for (int j = 17; j < 21; j++) {
                int random = (int) (Math.random() * 4000 + 400);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
        //兴趣是笛子的，生成500-4500随机点击量
        for (int i = 55; i < 64; i++) {
            for (int j = 36; j < 40; j++) {
                int random = (int) (Math.random() * 4500 + 500);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
        //兴趣是C#的，生成600-2000随机点击量
        for (int i = 65; i < 74; i++) {
            for (int j = 15; j < 16; j++) {
                int random = (int) (Math.random() * 2000 + 600);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
        //兴趣是vue的，生成300-3000随机点击量
        for (int i = 75; i < 84; i++) {
            for (int j = 12; j < 14; j++) {
                int random = (int) (Math.random() * 3000 + 300);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
        //兴趣是笛子的，生成500-3000随机点击量
        for (int i = 85; i < 94; i++) {
            for (int j = 36; j < 40; j++) {
                int random = (int) (Math.random() * 3000 + 500);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
        //兴趣是按摩的，生成400-5000随机点击量
        for (int i = 95; i < 104; i++) {
            for (int j = 31; j < 35; j++) {
                int random = (int) (Math.random() * 5000 + 400);
                UserActiveDTO userActiveDTO = new UserActiveDTO(i, j, random);
                userActiveService.saveUserActive(userActiveDTO);
            }
        }
    }

    /**
     * 功能描述: 生成用户与兴趣之间的点击量
     * @author : ruozhuliufeng
     * @date : 2020/4/15 9:28
     */
    @Test
    public void genFocusTest(){}
}
