package com.cslg.graduation.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * user实体类
 * @author ruozhuliufeng
 *
 */
@Data
@Table(name="user")
public class User implements Serializable {

	@Id
	private Integer id;//用户id

	private String username;//用户名

	private String password;//密码

	private String email;//邮箱

	private String sex; //性别

	private Integer status;//激活状态：0 待激活 1 已激活

	private String hname;//兴趣名称

	private String sname;//兴趣阶段

	private Integer type;//用户类型：0 普通用户 1 管理员

}
