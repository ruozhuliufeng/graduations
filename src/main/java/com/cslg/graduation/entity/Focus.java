package com.cslg.graduation.entity;

import lombok.Data;

/**
 * 类描述 用户与兴趣的关注度
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/15 10:19
 */
@Data
public class Focus {
    private Integer userId;
    private Integer hobbyId;
    private Integer hits;
}
