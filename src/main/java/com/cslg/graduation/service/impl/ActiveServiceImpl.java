package com.cslg.graduation.service.impl;

import com.cslg.graduation.entity.Active;
import com.cslg.graduation.mapper.ActiveMapper;
import com.cslg.graduation.service.ActiveService;
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
public class ActiveServiceImpl implements ActiveService {
    @Autowired
    private ActiveMapper activeMapper;
    @Override
    public List<Active> listAllUserActive() {
        return activeMapper.listAllUserActive();
    }

    @Override
    public boolean saveUserActive(Active active) {
        boolean flag = false;
        //先判断数据库中是否存在当前用户的浏览记录
        int rows = activeMapper.countUserActive(active);
        int saveRows = 0;
        int updateRows = 0;
        //2.不存在就添加
        if (rows<1){ //不存在
            active.setHits(1);//不存在记录的话肯定是第一次访问，点击量肯定是1
            saveRows = activeMapper.saveUserActive(active);

        }else {//已经存在
            //3.存在则先把当前用户对当前博客的点击量取出来+1
            Integer hits = activeMapper.getHistsByUserActiveInfo(active);
            //4.然后在更新用户的浏览记录
            hits++;
            active.setHits(hits);
            updateRows = activeMapper.updateUserActive(active);
        }
        if (saveRows>0 || updateRows>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Integer getHitsByUserIdAndBlogId(Integer userId, Integer blogId) {
        Active active = new Active();
        active.setUserId(userId);
        active.setBlogId(blogId);
        return activeMapper.getHistsByUserActiveInfo(active);
    }

    /**
     * 功能描述: 判断该用户是否为新用户
     *
     * @param userId 需要判断的用户id
     * @return : boolean
     * @author : ruozhuliufeng
     * @date : 2020/4/19 1:15
     */
    @Override
    public boolean findNewsUser(Integer userId) {
        List<Active> activeList = activeMapper.findNewUser(userId);
        if (activeList==null){
            System.out.println("没有数据");
            return false;
        }
        int count=0;
        for (Active active:activeList){
            System.out.println(active.getBlogId());
            count += active.getHits();
        }
        return count <= 300;
    }
}
