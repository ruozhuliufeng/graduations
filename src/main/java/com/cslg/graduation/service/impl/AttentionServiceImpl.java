package com.cslg.graduation.service.impl;

import com.cslg.graduation.entity.Attention;
import com.cslg.graduation.mapper.AttentionMapper;
import com.cslg.graduation.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类描述
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/15 11:41
 */
@Service
public class AttentionServiceImpl implements AttentionService {

    @Autowired
    private AttentionMapper attentionMapper;

    /**
     * 功能描述: 新增用户相似度数据
     *
     * @param attention 1 用户相似度数据
     * @return : boolean true 则新增用户相似度成功 false则失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:27
     */
    @Override
    public boolean saveAttention(Attention attention) {
        boolean flag = false;
        int rows = attentionMapper.insert(attention);
        if (rows>0){
            flag = true;
        }
        return flag;
    }

    /**
     * 功能描述: 更新用户相似度数据
     *
     * @param attention 1 用户相似度数据
     * @return : boolean true 更新成功 false 更新失败
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:29
     */
    @Override
    public boolean updateAttention(Attention attention) {
        boolean flag = false;
        int rows = attentionMapper.updateByPrimaryKey(attention);
        if (rows>0){
            flag = true;
        }
        return flag;
    }

    /**
     * 功能描述: 判断两个用户之间的相似度是否已经存在
     *
     * @param attention 1 存储两个用户的id
     * @return : boolean true 表示已经存在 false 表示不存在
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:29
     */
    @Override
    public boolean isExistAttention(Attention attention) {
        int count = attentionMapper.countAttention(attention);
        if (count>0){
            return true;
        }
        return false;
    }

    /**
     * 功能描述: 查询某个用户与其他用户之间的相似度列表
     *
     * @param userId 1 待查询的用户id
     * @return : 该用户与其他用户的相似度列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 11:31
     */
    @Override
    public List<Attention> listAttentionByUId(Integer userId) {
        if (userId==null){
            return null;
        }
        List<Attention> attentionList = attentionMapper.listAttentionByUId(userId);
        return attentionList;
    }
}
