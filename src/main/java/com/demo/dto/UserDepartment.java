package com.demo.dto;

import java.io.Serializable;

//用户部门包装类
public class UserDepartment implements Serializable 
{
	
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String user_name;// 用户账号
	private String real_name;// 用户名称
	private String dep_name;// 部门名称
	private String dep_area;// 部门地区

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getDep_area() {
		return dep_area;
	}

	public void setDep_area(String dep_area) {
		this.dep_area = dep_area;
	}

	@Override
	public String toString() {
		return "UserDepartment [user_name=" + user_name + ", real_name=" + real_name + ", dep_name=" + dep_name
				+ ", dep_area=" + dep_area + "]";
	}
	   
	   
	   
	   
	   
	   
	   
	   
	   
	

}
