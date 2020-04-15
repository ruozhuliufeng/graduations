package com.cslg.graduation.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * comment实体类
 * @author ruozhuliufeng
 *
 */
@Data
@Table(name="comment")
public class Comment implements Serializable {

	@Id
	private Integer id;// 评论id

	private java.util.Date publishTime;//法布时间

	private java.util.Date modifyTime;//修改时间

	private Integer topicId;//博客id

	private Integer userId;//用户id

	private String content;//评论内容

	private String title;//评论标题


}
