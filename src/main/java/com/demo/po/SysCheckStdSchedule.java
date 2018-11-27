package com.demo.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class SysCheckStdSchedule implements Serializable{

	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int super_id;//主表id
	private int order_number;//序号
	private String check_proj_type;//检测项目类型（序号后的空列）
	private String check_sup_class;//检测大类(检测项目名称)
	private String check_class;//检测小类(检测项目名称)
	private String check_unit;//单位
	private String standard_request;//标准要求
	private String standard_day;//天数
	private String dev_name;//仪表名称
	private String dev_type;//仪表型号
	private String dev_code;//仪表编号
	private Timestamp create_time;//创建时间 
	private Timestamp update_time;//修改时间
	private String create_oper;//创建用户
	private String update_oper;//修改用户
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSuper_id() {
		return super_id;
	}
	public void setSuper_id(int super_id) {
		this.super_id = super_id;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public String getCheck_proj_type() {
		return check_proj_type;
	}
	public void setCheck_proj_type(String check_proj_type) {
		this.check_proj_type = check_proj_type;
	}
	public String getCheck_sup_class() {
		return check_sup_class;
	}
	public void setCheck_sup_class(String check_sup_class) {
		this.check_sup_class = check_sup_class;
	}
	public String getCheck_class() {
		return check_class;
	}
	public void setCheck_class(String check_class) {
		this.check_class = check_class;
	}
	public String getCheck_unit() {
		return check_unit;
	}
	public void setCheck_unit(String check_unit) {
		this.check_unit = check_unit;
	}
	public String getStandard_request() {
		return standard_request;
	}
	public void setStandard_request(String standard_request) {
		this.standard_request = standard_request;
	}
	public String getStandard_day() {
		return standard_day;
	}
	public void setStandard_day(String standard_day) {
		this.standard_day = standard_day;
	}
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public String getDev_type() {
		return dev_type;
	}
	public void setDev_type(String dev_type) {
		this.dev_type = dev_type;
	}
	public String getDev_code() {
		return dev_code;
	}
	public void setDev_code(String dev_code) {
		this.dev_code = dev_code;
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
		return "SysCheckStdSchedule [id=" + id + ", super_id=" + super_id + ", order_number=" + order_number
				+ ", check_proj_type=" + check_proj_type + ", check_sup_class=" + check_sup_class + ", check_class="
				+ check_class + ", check_unit=" + check_unit + ", standard_request=" + standard_request
				+ ", standard_day=" + standard_day + ", dev_name=" + dev_name + ", dev_type=" + dev_type + ", dev_code="
				+ dev_code + ", create_time=" + create_time + ", update_time=" + update_time + ", create_oper="
				+ create_oper + ", update_oper=" + update_oper + "]";
	}

	
	
	
}
