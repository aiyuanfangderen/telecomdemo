package com.demo.dto;

import java.io.Serializable;
//用户包装类，包装部分属性
public class UserPosition implements Serializable
{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;// 用户id
	private String name;// 用户名

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserPosition [id=" + id + ", name=" + name + "]";
	}
	
	

}
