package com.cslg.graduation.mapper;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.entity.Focus;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 类描述
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/15 10:22
 */
public interface FocusMapper extends Mapper<Focus> {


    /**
     * 功能描述: 查询出所有的用户行为
     * @return :
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:03
     */
    List<Focus> listAllFocus();

    /**
     * 功能描述:
     * 根据用户已有的行为信息获取他对某个博客的点击量
     * @param focus 1
     * @return : int 某个用户对某个博客的点击量
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:04
     */
    int getHistsByFocus(Focus focus);

    /**
     * 功能描述:
     * 统计某个用户的行为记录条数
     * @param focus 1 要查询的用户的行为记录的条件
     * @return : int 1 说明存在这个用户的行为，0 说明不存在
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:05
     */
    int countFocus(Focus focus);
    
}
