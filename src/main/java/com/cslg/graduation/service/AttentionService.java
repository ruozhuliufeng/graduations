package com.cslg.graduation.service;

import com.cslg.graduation.dto.UserSimilarityDTO;
import com.cslg.graduation.entity.Attention;

import java.util.List;

public interface AttentionService {


    /**
     * 功能描述: 新增用户相似度数据
     * @param attention 1 用户相似度数据
     * @return : boolean true 则新增用户相似度成功 false则失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:27
     */
    boolean saveAttention(Attention attention);

    /**
     * 功能描述: 更新用户相似度数据
     * @param attention 1 用户相似度数据
     * @return : boolean true 更新成功 false 更新失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:29
     */
    boolean updateAttention(Attention attention);

    /**
     * 功能描述: 判断两个用户之间的相似度是否已经存在
     * @param attention 1 存储两个用户的id
     * @return : boolean true 表示已经存在 false 表示不存在
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:29
     */
    boolean isExistAttention(Attention attention);

    /**
     * 功能描述: 查询某个用户与其他用户之间的相似度列表
     * @param userId 1 待查询的用户id
     * @return : 该用户与其他用户的相似度列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:31
     */
    List<Attention> listAttentionByUId(Integer userId);

}
