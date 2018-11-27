package com.demo.po;

import java.io.Serializable;
import java.sql.Timestamp;
//菜单实体类
public class SysMenu implements Comparable<Object>,Serializable
{
   

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;// id
	private String menu_name;// 中文名称
	private String menu_english_name;// 英文名称
	private String menu_url;// 链接地址
	private int order_number;// 顺序号
	private int is_auth_check;// 是否权限控制，0不控制，1控制
	private int is_display;// 是否显示，0不显示，1显示
	private int menu_parent_id;// 上级菜单id,顶级为0
	private int menu_type;// 菜单级别
	private String menu_icon;// 菜单图标
	private Timestamp create_time;// 开始时间
	private Timestamp update_time;// 更新时间
	private String create_oper;// 创建用户
	private String update_oper;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_english_name() {
		return menu_english_name;
	}

	public void setMenu_english_name(String menu_english_name) {
		this.menu_english_name = menu_english_name;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public int getIs_auth_check() {
		return is_auth_check;
	}

	public void setIs_auth_check(int is_auth_check) {
		this.is_auth_check = is_auth_check;
	}

	public int getIs_display() {
		return is_display;
	}

	public void setIs_display(int is_display) {
		this.is_display = is_display;
	}

	public int getMenu_parent_id() {
		return menu_parent_id;
	}

	public void setMenu_parent_id(int menu_parent_id) {
		this.menu_parent_id = menu_parent_id;
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

	public int getMenu_type() {
		return menu_type;
	}

	public void setMenu_type(int menu_type) {
		this.menu_type = menu_type;
	}

	public String getMenu_icon() {
		return menu_icon;
	}

	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}

	@Override
	public String toString() {
		return "SysMenu [id=" + id + ", menu_name=" + menu_name + ", menu_english_name=" + menu_english_name
				+ ", menu_url=" + menu_url + ", order_number=" + order_number + ", is_auth_check=" + is_auth_check
				+ ", is_display=" + is_display + ", menu_parent_id=" + menu_parent_id + ", menu_type=" + menu_type
				+ ", menu_icon=" + menu_icon + ", create_time=" + create_time + ", update_time=" + update_time
				+ ", create_oper=" + create_oper + ", update_oper=" + update_oper + "]";
	}

	@Override
	public int compareTo(Object o) {
		SysMenu sysMenu = (SysMenu) o;
		if (this.getMenu_type() > sysMenu.getMenu_type()) {
			return 1;
		} else if (this.getMenu_type() < sysMenu.getMenu_type()) {
			return -1;
		}
		return 0;
		
		
		
	}
	
    
    
    
    
	
    
    
    
}
