package com.demo.entity;

import java.sql.Timestamp;

public class SysParameterType {
	
	
	//id
	private Integer id;
	//参数类型名
	private String parm_type_name;
	//参数类型名中文名称
	private String parm_type_name_cn;
	//创建时间
	private Timestamp create_time;
	//更新时间
	private Timestamp update_time;
	//创建用户
	private String create_oper;
	//更新用户
	private String update_oper;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParm_type_name() {
		return parm_type_name;
	}
	public void setParm_type_name(String parm_type_name) {
		this.parm_type_name = parm_type_name;
	}
	
	public String getParm_type_name_cn() {
		return parm_type_name_cn;
	}
	public void setParm_type_name_cn(String parm_type_name_cn) {
		this.parm_type_name_cn = parm_type_name_cn;
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
		return "SysParameterType [id=" + id + ", parm_type_name=" + parm_type_name + ", parm_type_name_cn="
				+ parm_type_name_cn + ", create_time=" + create_time + ", update_time=" + update_time + ", create_oper="
				+ create_oper + ", update_oper=" + update_oper + "]";
	}
	
	
	
	
	
	
	
	
	
}
