package com.cslg.graduation.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * blog实体类
 * @author ruozhuliufeng
 *
 */
@Data
@Table(name="blog")
public class Blog implements Serializable {

	@Id
	private Integer id;//博文id

	private Date publishTime;//发布时间

	private Date modifyTime;//修改时间

	private String title;//博文标题

	private String content;//博文内容

	private Integer cid;//所属分类

	private Integer userId;//用户id

	private Integer good;//是否精华：0 不精华 1 精华

	private Integer top;//是否置顶：0 不置顶 1 置顶

	private Integer hits;//点击量

	private Integer hid;//兴趣内容id， 该兴趣内容下的所有相关博客，默认为1，为1说明是管理员发表
}
