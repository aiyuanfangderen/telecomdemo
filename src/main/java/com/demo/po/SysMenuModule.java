package com.demo.po;

import java.io.Serializable;
import java.sql.Timestamp;
//菜单模块关联类
public class SysMenuModule implements Serializable
{
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;//id
	private int module_id;//模块id
	private int menu_id;//顶级菜单id
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
	public int getModule_id() {
		return module_id;
	}
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
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
		return "SysMenuModule [id=" + id + ", module_id=" + module_id + ", menu_id=" + menu_id + ", create_time="
				+ create_time + ", update_time=" + update_time + ", create_oper=" + create_oper + ", update_oper="
				+ update_oper + "]";
	}
	
	
	
	
	
}
