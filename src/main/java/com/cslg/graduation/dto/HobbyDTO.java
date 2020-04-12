package com.cslg.graduation.dto;

import lombok.Data;

@Data
public class HobbyDTO {
    private Integer id;
    private String name; //兴趣名称
    private String sid; //阶段id
    private String cname;//分类名称
}
