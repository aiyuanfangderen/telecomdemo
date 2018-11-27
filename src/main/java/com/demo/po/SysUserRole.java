package com.demo.po;

import java.io.Serializable;
import java.sql.Timestamp;
//用户角色关联类
public class SysUserRole implements Serializable
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;//id
	private int user_id;//用户id
	private int role_id;//角色id
	private Timestamp create_time;//创建时间
	private Timestamp update_time;//更新时间
	private String create_oper;//创建人
	private String update_oper;//更新人
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
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
		return "SysUserRole [id=" + id + ", user_id=" + user_id + ", role_id=" + role_id + ", create_time="
				+ create_time + ", update_time=" + update_time + ", create_oper=" + create_oper + ", update_oper="
				+ update_oper + "]";
	}
	
	
	
	
	
	
	
	
	
}
