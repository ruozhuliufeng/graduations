package com.cslg.graduation.dto;

import lombok.Data;

/**
 * @author ruozhuliufeng
 */
@Data
public class BlogInputDTO {
    private String title;
    private String content;
    private String cname;
    private Integer userId;
    private Integer hid;
}
