package com.demo.po;

import java.io.Serializable;
import java.sql.Timestamp;

//部门领导实体
public class SysDepartLeader implements Serializable
{

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//id
	private int id;
	//部门id
	private int dep_id;
	//分管领导id
	private int user_id;
	//开始时间
	private Timestamp create_time;
	//更新时间
	private Timestamp update_time;
	
	//创建用户
	private String create_oper;
	
	//修改用户
	private String update_oper;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDep_id() {
		return dep_id;
	}

	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
		return "SysDepartLeader [id=" + id + ", dep_id=" + dep_id + ", user_id=" + user_id + ", create_time="
				+ create_time + ", update_time=" + update_time + ", create_oper=" + create_oper + ", update_oper="
				+ update_oper + "]";
	}
	
	

	
	
	
	
}
