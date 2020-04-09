package com.cslg.graduation.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * advice实体类
 * @author Administrator
 *
 */
@Data
@Table(name="advice")
public class Advice implements Serializable {
	private Integer id;
	private String name;//姓名
	private String email;//邮箱
	private String advice;//建议
	
}
