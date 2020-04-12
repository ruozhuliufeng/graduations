package com.cslg.graduation.dto;

import lombok.Data;

@Data
public class CommentInputDTO {
    private String title;
    private String content;
    private Integer topicId;
    private Integer userId;
}
