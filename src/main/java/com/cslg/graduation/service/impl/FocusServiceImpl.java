package com.cslg.graduation.service.impl;

import com.cslg.graduation.entity.Focus;
import com.cslg.graduation.mapper.FocusMapper;
import com.cslg.graduation.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类描述
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/15 11:42
 */
@Service
public class FocusServiceImpl implements FocusService {

    @Autowired
    private FocusMapper focusMapper;
    /**
     * 功能描述: 查询出所有的用户行为
     *
     * @return : 用户的行为数据
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:12
     */
    @Override
    public List<Focus> listAllFocus() {
        return focusMapper.listAllFocus();
    }

    /**
     * 功能描述: 保存用户的浏览记录，如果用户的浏览记录存在则更新，不存在则添加
     *
     * @param focus 用户的行为数据
     * @return : boolean true 表示更新成功 false 表示更新失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:13
     */
    @Override
    public boolean saveFocus(Focus focus) {
        boolean flag = false;
        //先判断数据库中是否存在当前用户的浏览记录
        int rows = focusMapper.countFocus(focus);
        int saveRows = 0;
        int updateRows = 0;
        //2.不存在就添加
        if (rows<1){ //不存在
            focus.setHits(1);//不存在记录的话肯定是第一次访问，点击量肯定是1
            saveRows = focusMapper.insert(focus);

        }else {//已经存在
            //3.存在则先把当前用户对当前博客的点击量取出来+1
            Integer hits = focusMapper.getHistsByFocus(focus);
            //4.然后在更新用户的浏览记录
            hits++;
            focus.setHits(hits);
            updateRows = focusMapper.updateByPrimaryKey(focus);
        }
        if (saveRows>0 || updateRows>0){
            flag = true;
        }
        return flag;
    }
}
