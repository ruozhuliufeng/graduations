package com.cslg.graduation.service.impl;

import com.cslg.graduation.dto.UserSimilarityDTO;
import com.cslg.graduation.entity.Similarity;
import com.cslg.graduation.mapper.SimilarityMapper;
import com.cslg.graduation.service.SimilarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类描述 对用户之间的相似度进行操作的服务实现类
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 10:00
 */
@Service
public class SimilarityServiceImpl implements SimilarityService {
    @Autowired
    private SimilarityMapper similarityMapper;
    @Override
    public boolean saveUserSimilarity(Similarity similarity) {
        boolean flag = false;
        int rows = similarityMapper.saveUserSimilarity(similarity);
        if (rows>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateUserSimilarity(Similarity similarity) {
        boolean flag = false;
        int rows = similarityMapper.updateUserSimilarity(similarity);
        if (rows>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean isExistUserSimilarity(Similarity similarity) {
        int count = similarityMapper.countUserSimilarity(similarity);
        if (count>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Similarity> listUserSimilarityByUId(Integer userId) {
        if (userId==null){
            return null;
        }
        List<Similarity> similarityList = similarityMapper.listUserSimilarityByUId(userId);
        return similarityList;
    }
}
