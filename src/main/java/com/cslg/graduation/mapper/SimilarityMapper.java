package com.cslg.graduation.mapper;

import com.cslg.graduation.dto.UserSimilarityDTO;
import com.cslg.graduation.entity.Similarity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 类描述 更新数据库中用户与用户之间的相似度
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 9:54
 */
public interface SimilarityMapper extends Mapper<Similarity> {

    /**
     * 功能描述: 新增用户相似度数据
     * @param userSimilarityDTO 1 用户相似度数据
     * @return : int 受影响到的行数 0 表示影响0行 1 表示影响1行
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:10
     */
    int saveUserSimilarity(Similarity similarity);

    /**
     * 功能描述:  更新用户相似度数据
     * @param userSimilarityDTO 1 用户相似度数据
     * @return : int 受影响到的行数
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:13
     */
    int updateUserSimilarity(Similarity similarity);

    /**
     * 功能描述:  判断两个用户之间的相似度是否已经存在
     * @param userSimilarityDTO 1 存储两个用户的id
     * @return : int 返回1 表示已经存在 返回0 表示不存在
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:14
     */
    int countUserSimilarity(Similarity similarity);

    /**
     * 功能描述: 查询某个用户与其他用户之间的相似度列表
     * @param userId 1 待查询的用户id
     * @return : 该用户与其他用户的相似度列表
     * @author : ruozhuliufeng
     * @date : 2020/4/12 10:16
     */
    List<Similarity> listUserSimilarityByUId(Integer userId);
}
