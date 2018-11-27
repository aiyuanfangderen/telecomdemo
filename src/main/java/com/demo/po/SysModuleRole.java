package com.demo.po;

import java.io.Serializable;
import java.sql.Timestamp;
//模块角色类
public class SysModuleRole implements Serializable
{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;//id
	private int role_id;//角色id
	private int module_id;//模块id
	private String query_scope;//查询范围
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
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public int getModule_id() {
		return module_id;
	}
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}
	public String getQuery_scope() {
		return query_scope;
	}
	public void setQuery_scope(String query_scope) {
		this.query_scope = query_scope;
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
		return "SysModuleRole [id=" + id + ", role_id=" + role_id + ", module_id=" + module_id + ", query_scope="
				+ query_scope + ", create_time=" + create_time + ", update_time=" + update_time + ", create_oper="
				+ create_oper + ", update_oper=" + update_oper + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
