package com.cslg.graduation.dto;

import lombok.Data;

/**
 * @author ruozhuliufeng
 */
@Data
public class BlogInputDTO {
    //分页参数

    private String title;
    private String content;
    private String cname;
    private Integer userId;
    private Integer hid;
}
