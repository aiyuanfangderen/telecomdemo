package com.demo.po;

import java.io.Serializable;
import java.sql.Timestamp;
//检测标准
public class SysCheckStandard implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private	String  sample_client;//客户
	private	String business_type;
	private	String sample_sup_class;//样品大类
	private	String sample_class;//样品名称
	private	String sample_type;//规格型号
	private String check_norm;//检测标准
	private String check_period;//检测周期(计算规则)
	private Timestamp create_time; //创建时间
	private Timestamp update_time;//修改时间
	private String create_oper; //创建用户
	private String update_oper;//修改用户
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSample_client() {
		return sample_client;
	}
	public void setSample_client(String sample_client) {
		this.sample_client = sample_client;
	}
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
	public String getSample_sup_class() {
		return sample_sup_class;
	}
	public void setSample_sup_class(String sample_sup_class) {
		this.sample_sup_class = sample_sup_class;
	}
	public String getSample_class() {
		return sample_class;
	}
	public void setSample_class(String sample_class) {
		this.sample_class = sample_class;
	}
	public String getSample_type() {
		return sample_type;
	}
	public void setSample_type(String sample_type) {
		this.sample_type = sample_type;
	}
	public String getCheck_norm() {
		return check_norm;
	}
	public void setCheck_norm(String check_norm) {
		this.check_norm = check_norm;
	}
	public String getCheck_period() {
		return check_period;
	}
	public void setCheck_period(String check_period) {
		this.check_period = check_period;
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
		return "SysCheckStandard [id=" + id + ", sample_client=" + sample_client + ", business_type=" + business_type
				+ ", sample_sup_class=" + sample_sup_class + ", sample_class=" + sample_class + ", sample_type="
				+ sample_type + ", check_norm=" + check_norm + ", check_period=" + check_period + ", create_time="
				+ create_time + ", update_time=" + update_time + ", create_oper=" + create_oper + ", update_oper="
				+ update_oper + "]";
	}

    
	
	
	
}
