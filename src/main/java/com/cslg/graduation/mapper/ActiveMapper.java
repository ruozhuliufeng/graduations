package com.cslg.graduation.mapper;

import com.cslg.graduation.entity.Active;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 9:27
 */
public interface ActiveMapper extends Mapper<Active> {

    /**
     * 功能描述: 查询出所有的用户行为
     * @return : java.util.List<com.cslg.graduation.recommendate.dto.UserActiveDTO>
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:03
     */
    List<Active> listAllUserActive();

    /**
     * 功能描述:
     * 根据用户已有的行为信息获取他对某个博客的点击量
     * @param userActiveDTO 1
     * @return : int 某个用户对某个博客的点击量
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:04
     */
    int getHistsByUserActiveInfo(Active active);

    /**
     * 功能描述:
     * 统计某个用户的行为记录条数
     * @param userActiveDTO 1 要查询的用户的行为记录的条件
     * @return : int 1 说明存在这个用户的行为，0 说明不存在
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:05
     */
    int countUserActive(Active active);

    /**
     * 功能描述:
     * 向用户行为表中添加一条用户的行为记录
     * @param userActive 1 用户的行为数据
     * @return : int 受影响的行数 1 表示插入成功 0 表示插入失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:07
     */
    int saveUserActive(Active active);

    /**
     * 功能描述:d
     * 更新用户对某篇博客的点击量
     * @param userActive 1 用户的浏览行为数据
     * @return : int 1 表示更新成功 0 表示更新失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:08
     */
    int updateUserActive(Active active);

    List<Active> findNewUser(Integer userId);
}
