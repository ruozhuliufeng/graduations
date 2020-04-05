package com.cslg.graduation.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * stage实体类
 * @author ruozhuliufeng
 *
 */
@Data
@Table(name="stage")
public class Stage implements Serializable {

	@Id
	private Integer id;//阶段id

	private String name;//阶段名称



	
}
