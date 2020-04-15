package com.cslg.graduation.entity;

import lombok.Data;

/**
 * 类描述 用户与用户之间的相似度
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/15 10:20
 */
@Data
public class attention {
    private Integer userId;
    private Integer userRefId;
    private Double attention;
}
