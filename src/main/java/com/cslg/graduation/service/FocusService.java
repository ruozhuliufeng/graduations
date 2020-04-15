package com.cslg.graduation.service;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.entity.Focus;

import java.util.List;

public interface FocusService {

    /**
     * 功能描述: 查询出所有的用户行为
     * @return : 用户的行为数据
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:12
     */
    List<Focus> listAllFocus();
    /**
     * 功能描述: 保存用户的浏览记录，如果用户的浏览记录存在则更新，不存在则添加
     * @param focus  用户的行为数据
     * @return : boolean true 表示更新成功 false 表示更新失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:13
     */
    boolean saveFocus(Focus focus);
}
