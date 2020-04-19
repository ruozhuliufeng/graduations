package com.cslg.graduation.entity;

import lombok.Data;

/**
 * 类描述 用户之间相似度的实体类
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 16:17
 */
@Data
public class Similarity {
    private Integer userId;
    private Integer userRefId;
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
    public int compareTo(Similarity o) {
        return o.getSimilarity().compareTo(this.getSimilarity());
    }
}
