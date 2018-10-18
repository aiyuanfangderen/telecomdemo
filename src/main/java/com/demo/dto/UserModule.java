package com.demo.dto;

import java.io.Serializable;

//用户模块包装类
public class UserModule implements Serializable
{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//模块id
	private int id;
	//模块名称
	private String module_name;

	//模块代码
    private String module_code;
	//默认查询范围
	private String default_query_scope;
	//默认的权限管理
	private String default_auth_type;
	//角色编码
	private String role_code;
	//角色名称
	private String role_name;
	//角色描述
	private String role_desc;
	
	
	
	
	
	
	
	

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getDefault_query_scope() {
		return default_query_scope;
	}

	public void setDefault_query_scope(String default_query_scope) {
		this.default_query_scope = default_query_scope;
	}

	public String getDefault_auth_type() {
		return default_auth_type;
	}

	public void setDefault_auth_type(String default_auth_type) {
		this.default_auth_type = default_auth_type;
	}

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	@Override
	public String toString() {
		return "UserModule [id=" + id + ", module_name=" + module_name + ", module_code=" + module_code
				+ ", default_query_scope=" + default_query_scope + ", default_auth_type=" + default_auth_type
				+ ", role_code=" + role_code + ", role_name=" + role_name + ", role_desc=" + role_desc + "]";
	}

	
	
	
	
	
	
}
