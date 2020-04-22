package com.cslg.graduation.service;

import com.cslg.graduation.dto.UserActiveDTO;
import com.cslg.graduation.entity.Active;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 类描述: 操作数据库中用户的行为数据的服务类
 * @author : ruozhuliufeng
 * @date : 2020/4/12 11:12
 */
public interface ActiveService {

    /**
     * 功能描述: 查询出所有的用户行为
     * @return : 用户的行为数据
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:12
     */
    List<Active> listAllUserActive();
    /**
     * 功能描述: 保存用户的浏览记录，如果用户的浏览记录存在则更新，不存在则添加
     * @param userActiveDTO  用户的行为数据
     * @return : boolean true 表示更新成功 false 表示更新失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:13
     */
    boolean saveUserActive(Active active);

    Integer getHitsByUserIdAndBlogId(Integer userId,Integer blogId);
    /**
     * 功能描述: 判断该用户是否为新用户
     * @param userId 需要判断的用户id
     * @return : boolean
     * @author : ruozhuliufeng
     * @date : 2020/4/19 1:15
     */
    boolean findNewsUser(Integer userId);
}
