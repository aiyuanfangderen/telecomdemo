package com.demo.dto;

import java.io.Serializable;

//用户角色包装类
public class UserRole implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;//用户名
    private String rolename;//角色名
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public String toString() {
		return "UserRole [username=" + username + ", rolename=" + rolename + "]";
	}
    
    
    
	
	
}
