package com.demo.dto;

import java.io.Serializable;

//角色用户部门包装类
public class RoleConfigure implements Serializable
{
	
   

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	public int getIs_builtin() {
		return is_builtin;
	}
	public void setIs_builtin(int is_builtin) {
		this.is_builtin = is_builtin;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	private int id;//角色id
    private String role_code;//角色代码
    private String role_name;//角色名称
    private String role_desc;//角色描述
    private int is_builtin;//是否为内置角色
    private String user_id;//用户id
    private String user_name;//用户名称
    private String dep_name;//部门名称
    private String dep_area;//部门地区
	@Override
	public String toString() {
		return "RoleConfigure [id=" + id + ", role_code=" + role_code + ", role_name=" + role_name + ", role_desc="
				+ role_desc + ", is_builtin=" + is_builtin + ", user_id=" + user_id + ", user_name=" + user_name
				+ ", dep_name=" + dep_name + ", dep_area=" + dep_area + "]";
	}
	
    
	
}
