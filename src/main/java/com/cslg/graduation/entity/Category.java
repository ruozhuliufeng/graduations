package com.cslg.graduation.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * category实体类
 * @author ruozhuliufeng
 *
 */
@Data
@Table(name="category")
public class Category implements Serializable {

	@Id
	private Integer id;//分类id

	private String name;//分类名称

	private Integer masterId;//版主id

	
}
