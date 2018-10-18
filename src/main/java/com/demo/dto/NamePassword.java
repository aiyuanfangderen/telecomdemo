package com.demo.dto;

import java.io.Serializable;

//用户名和密码包装类
public class NamePassword implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户名
	private String username;
	//密码
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "NamePassword [username=" + username + ", password=" + password + "]";
	}
	
	
	
	
	
	
	

}
