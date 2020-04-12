package com.cslg.graduation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 功能描述:
 * 保存用户点击量
 * @author : ruozhuliufeng
 * @date : 2020/4/12 9:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActiveDTO implements Serializable {
    //用户id
    private Integer userId;
    //博客id
    private Integer blogId;
    //用户对该博客的点击量
    private Integer hits;

}
