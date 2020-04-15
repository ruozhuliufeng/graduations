package com.cslg.graduation.mapper;

import com.cslg.graduation.entity.Attention;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AttentionMapper extends Mapper<Attention> {

    /**
     * 功能描述:  判断两个用户之间的相似度是否已经存在
     * @param attention 1 存储两个用户的id
     * @return : int 返回1 表示已经存在 返回0 表示不存在
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:14
     */
    int countAttention(Attention attention);

    /**
     * 功能描述: 查询某个用户与其他用户之间的相似度列表
     * @param userId 1 待查询的用户id
     * @return : 该用户与其他用户的相似度列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:16
     */
    List<Attention> listAttentionByUId(Integer userId);
}
