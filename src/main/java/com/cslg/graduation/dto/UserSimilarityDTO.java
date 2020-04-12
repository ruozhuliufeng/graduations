package com.cslg.graduation.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSimilarityDTO implements Serializable {
    //用户id
    private Integer userId;
    //进行比较的另一个用户的id
    private Integer userRefId;
    //userId与userRefId之间的相似度
    private Double similarity;
    /**
     * 功能描述:
     * 计算相似度
     *
     * @param o 1
     * @return : int
     * @author : ruozhuliufeng
     * @date : 2020/4/12 9:25
     */
    public int compareTo(UserSimilarityDTO o) {
        return o.getSimilarity().compareTo(this.getSimilarity());
    }
}
