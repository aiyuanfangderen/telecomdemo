package com.demo.service;

import java.util.List;

import com.demo.dto.NamePassword;
import com.demo.dto.UserModule;
import com.demo.entity.SysRole;
import com.demo.entity.SysUser;
import com.demo.entity.SysUserRole;

public interface SysUserService1
{
	
	public void insertUser(SysUser sysUser);
	

	
	 public SysUser selectUserByRealUsername(SysUser sysUser);
	

	
	
	public void insertUserRole(SysUserRole sysUserRole);
	
	
	
	public List<UserModule> selectModule(int id);
	
	
	public SysUser selectUserByName(String username);
	
	
	
	public SysUser selectUserByRealName(String realname);
	
	
	
	 public void updateUserState(SysUser sysUser);
	
	 
	 
	 
	 public List<SysUser> selectUserByDepId(int dep_id);
	
	 
	 
	 public void updateUserDepId(SysUser sysUser);
	
	 
	 
	 public void deleteUserRole(int id);
	
	 
	 
	 public SysUser selectUserById(int id);
	
	 
	 
	
	 
	 public void updateUserPosition(int id);
	
	 
	 
	 
	 public void updateUserPosition2(SysUser sysUser);
	 
	 
	 
	 
	 
	 public List<SysUser> selectAllUser();
	
	 
	 
	 public SysUser selectUserByDepIdReturnOne(int dep_id);
	 
	 
	 
	 public List<SysUser> selectUserOnDepidNull();
	
	 
	 public List<SysUserRole> selectUserRoleByUserId(int user_id);
	
	 
	 public List<SysUserRole> selectUserRoleByRoleId(int role_id);
	 
	 
	 public SysUserRole selectUserRoleByUserIdReturnOne(int user_id);
	
	 
	 
	 
	 public SysUserRole selectUserRoleByRoleIdReturnOne(int role_id);
	 
	 
	 
	 
	 public void updateUserDepIdById(SysUser sysUser);
	
	 
	 
	 public SysUser selectUserByPasswordUsername(NamePassword namePassword);
	

	public void updateUser(SysUser sysUser);
	
	 public List<SysRole> selectRoleByUserName(String user_name);
	
	 
	 
	 public List<SysUser> selectUserNameId();
	
	 
	
	

}
