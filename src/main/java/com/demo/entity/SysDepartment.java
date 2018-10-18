package com.demo.entity;

import java.io.Serializable;

//import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.sql.Timestamp;
//部门实体类
public class SysDepartment implements Comparable<Object>,Serializable
{
 

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;// id
	private int dep_number;// 序号
	private String dep_name;// 部门名称
	private String dep_alias;// 部门别名
	private String dep_english_name;// 部门英语名称
	private String dep_english_alias;// 部门英语别名
	private String dep_principal;// 部门正职
	private String dep_deputy;// 部门副职
	private int is_display;// 是否显示
	private int parent_dep_id;// 上级部门
	private String dep_status;// 部门状态
	private String dep_transceiver;// 部门收发员
	private String dep_custom_id;// 部门id
	private String dep_unity_code;// 部门目录统一编号
	private String dep_description;// 部门描述
	private int is_same_look;// 是否正副职同时传阅
	private String dep_area;// 地址
	private int dep_type;// 部门级别
	private Timestamp create_time;// 开始时间
	private Timestamp update_time;// 更新时间
	private String create_oper;// 创建用户
	private String update_oper;// 修改用户

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDep_number() {
		return dep_number;
	}

	public void setDep_number(int dep_number) {
		this.dep_number = dep_number;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getDep_alias() {
		return dep_alias;
	}

	public void setDep_alias(String dep_alias) {
		this.dep_alias = dep_alias;
	}

	public String getDep_english_name() {
		return dep_english_name;
	}

	public void setDep_english_name(String dep_english_name) {
		this.dep_english_name = dep_english_name;
	}

	public String getDep_english_alias() {
		return dep_english_alias;
	}

	public void setDep_english_alias(String dep_english_alias) {
		this.dep_english_alias = dep_english_alias;
	}

	public String getDep_principal() {
		return dep_principal;
	}

	public void setDep_principal(String dep_principal) {
		this.dep_principal = dep_principal;
	}

	public String getDep_deputy() {
		return dep_deputy;
	}

	public void setDep_deputy(String dep_deputy) {
		this.dep_deputy = dep_deputy;
	}

	public int getIs_display() {
		return is_display;
	}

	public void setIs_display(int is_display) {
		this.is_display = is_display;
	}

	public int getParent_dep_id() {
		return parent_dep_id;
	}

	public void setParent_dep_id(int parent_dep_id) {
		this.parent_dep_id = parent_dep_id;
	}

	public String getDep_status() {
		return dep_status;
	}

	public void setDep_status(String dep_status) {
		this.dep_status = dep_status;
	}

	public String getDep_transceiver() {
		return dep_transceiver;
	}

	public void setDep_transceiver(String dep_transceiver) {
		this.dep_transceiver = dep_transceiver;
	}

	public String getDep_custom_id() {
		return dep_custom_id;
	}

	public void setDep_custom_id(String dep_custom_id) {
		this.dep_custom_id = dep_custom_id;
	}

	public String getDep_unity_code() {
		return dep_unity_code;
	}

	public void setDep_unity_code(String dep_unity_code) {
		this.dep_unity_code = dep_unity_code;
	}

	public String getDep_description() {
		return dep_description;
	}

	public void setDep_description(String dep_description) {
		this.dep_description = dep_description;
	}

	public int getIs_same_look() {
		return is_same_look;
	}

	public void setIs_same_look(int is_same_look) {
		this.is_same_look = is_same_look;
	}

	public String getDep_area() {
		return dep_area;
	}

	public void setDep_area(String dep_area) {
		this.dep_area = dep_area;
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

	public int getDep_type() {
		return dep_type;
	}

	public void setDep_type(int dep_type) {
		this.dep_type = dep_type;
	}


	@Override
	public String toString() {
		return "SysDepartment [id=" + id + ", dep_number=" + dep_number + ", dep_name=" + dep_name + ", dep_alias="
				+ dep_alias + ", dep_english_name=" + dep_english_name + ", dep_english_alias=" + dep_english_alias
				+ ", dep_principal=" + dep_principal + ", dep_deputy=" + dep_deputy + ", is_display=" + is_display
				+ ", parent_dep_id=" + parent_dep_id + ", dep_status=" + dep_status + ", dep_transceiver="
				+ dep_transceiver + ", dep_custom_id=" + dep_custom_id + ", dep_unity_code=" + dep_unity_code
				+ ", dep_description=" + dep_description + ", is_same_look=" + is_same_look + ", dep_area=" + dep_area
				+ ", dep_type=" + dep_type + ", create_time=" + create_time + ", update_time=" + update_time
				+ ", create_oper=" + create_oper + ", update_oper=" + update_oper + "]";
	}

	@Override
	public int compareTo(Object o) {
		SysDepartment sysDepartment = (SysDepartment) o;
		if (this.getDep_type() > sysDepartment.getDep_type()) {
			return 1;
		} else if (this.getDep_type() < sysDepartment.getDep_type()) {
			return -1;
		}
		return 0;
	
	
	
}
   
   
   
   
   
   
   
   
}
   
   




   
   
	

