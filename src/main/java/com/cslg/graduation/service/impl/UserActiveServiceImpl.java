package com.cslg.graduation.service.impl;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.mapper.UserActiveMapper;
import com.cslg.graduation.service.UserActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 类描述 用户的浏览行为的具体实现类
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 9:58
 */
@Service
public class UserActiveServiceImpl implements UserActiveService {
    @Autowired
    private UserActiveMapper userActiveMapper;
    @Override
    public List<UserActiveDTO> listAllUserActive() {
        return userActiveMapper.listAllUserActive();
    }

    @Override
    public boolean saveUserActive(UserActiveDTO userActiveDTO) {
        userActiveMapper.saveUserActive(userActiveDTO);
        return true;
//        boolean flag = false;
//        //先判断数据库中是否存在当前用户的浏览记录
//        int rows = userActiveMapper.countUserActive(userActiveDTO);
//        int saveRows = 0;
//        int updateRows = 0;
//        //2.不存在就添加
//        if (rows<1){ //不存在
//            userActiveDTO.setHits(1);//不存在记录的话肯定是第一次访问，点击量肯定是1
//            saveRows = userActiveMapper.saveUserActive(userActiveDTO);
//
//        }else {//已经存在
//            //3.存在则先把当前用户对当前博客的点击量取出来+1
//            Integer hits = userActiveMapper.getHistsByUserActiveInfo(userActiveDTO);
//            //4.然后在更新用户的浏览记录
//            hits++;
//            userActiveDTO.setHits(hits);
//            updateRows = userActiveMapper.updateUserActive(userActiveDTO);
//        }
//        if (saveRows>0 || updateRows>0){
//            flag = true;
//        }
//        return flag;
    }
}
