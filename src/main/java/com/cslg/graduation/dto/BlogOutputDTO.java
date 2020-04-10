package com.cslg.graduation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogOutputDTO {
    private Integer userId;
    private Integer id;
    private Date publishTime;
    private Date modifyTime;
    private String title;
    private String content;
    private String categoryName;
    private String userName;
    private String email;
    private Integer type;
    private Integer good;
    private Integer top;


    public BlogOutputDTO(Integer id, Date publishTime, Date modifyTime, String title, String content, String categoryName, String userName, Integer good, Integer top) {
        this.id = id;
        this.publishTime = publishTime;
        this.modifyTime = modifyTime;
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
        this.userName = userName;
        this.good = good;
        this.top = top;
    }
}
