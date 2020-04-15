package com.cslg.graduation.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * hobby实体类
 * @author Administrator
 *
 */
@Data
@Table(name="hobby")
public class Hobby implements Serializable {

	@Id
	private Integer id;//兴趣id

	private String name;//兴趣名称

	private String sid;//兴趣阶段

	private Integer cid;//所属分类

	private Integer hits;//点击量
	
}
