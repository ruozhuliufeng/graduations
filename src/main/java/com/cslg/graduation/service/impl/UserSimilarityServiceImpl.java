package com.cslg.graduation.service.impl;

import com.cslg.graduation.dto.UserSimilarityDTO;
import com.cslg.graduation.mapper.UserSimilarityMapper;
import com.cslg.graduation.service.UserSimilarityService;
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
public class UserSimilarityServiceImpl implements UserSimilarityService {
    @Autowired
    private UserSimilarityMapper userSimilarityMapper;
    @Override
    public boolean saveUserSimilarity(UserSimilarityDTO userSimilarityDTO) {
        boolean flag = false;
        int rows = userSimilarityMapper.saveUserSimilarity(userSimilarityDTO);
        if (rows>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateUserSimilarity(UserSimilarityDTO userSimilarityDTO) {
        boolean flag = false;
        int rows = userSimilarityMapper.updateUserSimilarity(userSimilarityDTO);
        if (rows>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean isExistUserSimilarity(UserSimilarityDTO userSimilarityDTO) {
        int count = userSimilarityMapper.countUserSimilarity(userSimilarityDTO);
        if (count>0){
            return true;
        }
        return false;
    }

    @Override
    public List<UserSimilarityDTO> listUserSimilarityByUId(Integer userId) {
        if (userId==null){
            return null;
        }
        List<UserSimilarityDTO> userSimilarityList = userSimilarityMapper.listUserSimilarityByUId(userId);
        return userSimilarityList;
    }
}
