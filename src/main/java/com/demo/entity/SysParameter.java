package com.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
//参数实体
public class SysParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//参数id
	private String parm_type;//参数类型
	private String parm_value;//参数值
	private String parm_name;//参数名
	private String parm_name_en;
	private String is_show;//是否显示
	private Timestamp create_time;//创建时间
	private Timestamp update_time;//更新时间
	private String create_oper;//创建人
	private String update_oper;//更新人
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParm_type() {
		return parm_type;
	}
	public void setParm_type(String parm_type) {
		this.parm_type = parm_type;
	}
	public String getParm_value() {
		return parm_value;
	}
	public void setParm_value(String parm_value) {
		this.parm_value = parm_value;
	}
	public String getParm_name() {
		return parm_name;
	}
	public void setParm_name(String parm_name) {
		this.parm_name = parm_name;
	}
	public String getParm_name_en() {
		return parm_name_en;
	}
	public void setParm_name_en(String parm_name_en) {
		this.parm_name_en = parm_name_en;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
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
		return "SysParameter [id=" + id + ", parm_type=" + parm_type + ", parm_value=" + parm_value + ", parm_name="
				+ parm_name + ", parm_name_en=" + parm_name_en + ", is_show=" + is_show + ", create_time=" + create_time
				+ ", update_time=" + update_time + ", create_oper=" + create_oper + ", update_oper=" + update_oper
				+ "]";
	}

	
	
	
}
