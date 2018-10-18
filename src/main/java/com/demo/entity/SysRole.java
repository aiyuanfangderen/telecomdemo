package com.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
//角色类
public class SysRole implements Serializable
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;//id
	private String role_code;//角色代码
	private String role_name;//角色名称
	private String role_desc;//角色描述
	private int is_builtin;//是否为系统内置角色
	private Timestamp create_time;//开始时间
	private Timestamp update_time;//更新时间
	private String create_oper;
	private String update_oper;
	
	
	
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
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getCreate_oper() {
		return create_oper;
	}
	public void setCreate_oper(String create_oper) {
		this.create_oper = create_oper;
	}
	public String getUpdate_oper() {
		return update_oper;
	}
	public void setUpdate_oper(String update_oper) {
		this.update_oper = update_oper;
	}

	@Override
	public String toString() {
		return "SysRole [id=" + id + ", role_code=" + role_code + ", role_name=" + role_name + ", role_desc="
				+ role_desc + ", is_builtin=" + is_builtin + ", create_time=" + create_time + ", update_time="
				+ update_time + ", create_oper=" + create_oper + ", update_oper=" + update_oper + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
