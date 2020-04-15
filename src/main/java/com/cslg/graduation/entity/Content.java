package com.cslg.graduation.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * content实体类
 * @author ruozhuliufeng
 *
 */
@Data
@Table(name="content")
public class Content implements Serializable {

	@Id
	private Integer id;//内容id

	private String name;//内容名称

	private Integer status;//是否完成 0 未完成 1 已完成

	private Integer sid;//所属阶段

	private String clink;//分享文件链接

	private String note;//笔记，指向本人博客
	
}
