package com.cslg.graduation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentOutPutDTO {

    private Integer id;
    private Date publishTime;
    private Date modifyTime;
    private String topicName;
    private String userName;
    private String title;
    private String content;

    public CommentOutPutDTO(Integer id, Date publishTime, Date modifyTime, String userName, String title, String content) {
        this.id = id;
        this.publishTime = publishTime;
        this.modifyTime = modifyTime;
        this.userName = userName;
        this.title = title;
        this.content = content;
    }
}
