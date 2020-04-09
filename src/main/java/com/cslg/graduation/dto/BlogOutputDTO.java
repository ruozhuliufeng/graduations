package com.cslg.graduation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogOutputDTO {


    private Integer id;
    private Date publishTime;
    private Date modifyTime;
    private String title;
    private String content;
    private String categoryName;
    private String userName;
    private Integer good;
    private Integer top;
}
