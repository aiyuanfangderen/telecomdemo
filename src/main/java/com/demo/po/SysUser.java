package com.demo.po;

import java.io.Serializable;
import java.sql.Timestamp;
//用户类
public class SysUser implements Comparable<Object>,Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;//id
	private String user_name;//用户账号
	private String user_password;//密码
	private String real_name;//用户真是姓名
	private String user_code;//员工工号
	private int user_level;//系统有5个级别，0职员，1，2，3，4
	private String user_position;//职位（级别部位0有职位，正职，副职）
	private int dep_id;//所属部门
	private int is_company_leader;//公司领导，1是，默认0
	private String order_number;//排序
	private String user_email;//邮件
	private int stauts;//状态是否有效，0有效，1离职
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public int getUser_level() {
		return user_level;
	}
	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}
	public String getUser_position() {
		return user_position;
	}
	public void setUser_position(String user_position) {
		this.user_position = user_position;
	}
	public int getDep_id() {
		return dep_id;
	}
	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}
	public int getIs_company_leader() {
		return is_company_leader;
	}
	public void setIs_company_leader(int is_company_leader) {
		this.is_company_leader = is_company_leader;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getStauts() {
		return stauts;
	}
	public void setStauts(int stauts) {
		this.stauts = stauts;
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
		return "SysUser [id=" + id + ", user_name=" + user_name + ", user_password=" + user_password + ", real_name="
				+ real_name + ", user_code=" + user_code + ", user_level=" + user_level + ", user_position="
				+ user_position + ", dep_id=" + dep_id + ", is_company_leader=" + is_company_leader + ", order_number="
				+ order_number + ", user_email=" + user_email + ", stauts=" + stauts + ", create_time=" + create_time
				+ ", update_time=" + update_time + ", create_oper=" + create_oper + ", update_oper=" + update_oper
				+ "]";
	}
	@Override
	public int compareTo(Object o) {
		SysUser sysUser=(SysUser)o;
		int result=id>sysUser.id?1:(id==sysUser.id?0:-1);
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
