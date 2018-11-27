package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.NamePassword;
import com.demo.dto.UserModule;
import com.demo.mapper.SysUserMapper;
import com.demo.po.SysRole;
import com.demo.po.SysUser;
import com.demo.po.SysUserRole;
import com.demo.service.SysUserService;
//用户实现类
@Service
public class SysUserServiceImpl implements SysUserService
{

	@Autowired
	private SysUserMapper sysUserMapper;
	
	public void insertUser(SysUser sysUser)
	{
		sysUserMapper.insertUser(sysUser);
	}

	
	 public SysUser selectUserByRealUsername(SysUser sysUser)
	 {
		 return sysUserMapper.selectUserByRealUsername(sysUser);
	 }

	
	
	public void insertUserRole(SysUserRole sysUserRole)
	{
		sysUserMapper.insertUserRole(sysUserRole);
	}
	
	
	public List<UserModule> selectModule(int id)
	{
		
		List<UserModule> userModules= sysUserMapper.selectModule(id);
		//System.out.println(userModules);
		
		
	
		return userModules;
	}
	
	public SysUser selectUserByName(String username)
	{
		return sysUserMapper.selectUserByName(username);
	}
	
	
	public SysUser selectUserByRealName(String realname)
	{
		return sysUserMapper.selectUserByRealName(realname);
	}
	
	
	 public void updateUserState(SysUser sysUser)
	 {
		  sysUserMapper.updateUserState(sysUser);
	 }
	 
	 
	 
	 public List<SysUser> selectUserByDepId(int dep_id)
	 {
		 return sysUserMapper.selectUserByDepId(dep_id);
	 }
	 
	 
	 public void updateUserDepId(SysUser sysUser)
	 {
		 sysUserMapper.updateUserDepId(sysUser);
	 }
	 
	 
	 public void deleteUserRole(int id)
	 {
		 sysUserMapper.deleteUserRole(id);
	 }
	 
	 
	 public SysUser selectUserById(int id)
	 {
		 return sysUserMapper.selectUserById(id);
	 }
	 
	 
	
	 
	 public void updateUserPosition(int id)
	 {
		 sysUserMapper.updateUserPosition(id);
	 }
	 
	 
	 
	 public void updateUserPosition2(SysUser sysUser)
	 {
		 sysUserMapper.updateUserPosition2(sysUser);
	 }
	 
	 
	 
	 
	 public List<SysUser> selectAllUser()
	 {
		 return sysUserMapper.selectAllUser();
	 }
	 
	 
	 
	 public SysUser selectUserByDepIdReturnOne(int dep_id)
	 {
		 return sysUserMapper.selectUserByDepIdReturnOne(dep_id);
	 }
	 
	 
	 public List<SysUser> selectUserOnDepidNull()
	 {
		 return sysUserMapper.selectUserOnDepidNull();
	 }
	 
	 public List<SysUserRole> selectUserRoleByUserId(int user_id)
	 {
		 return sysUserMapper.selectUserRoleByUserId(user_id);
	 }
	 
	 public List<SysUserRole> selectUserRoleByRoleId(int role_id)
	 {
		 return sysUserMapper.selectUserRoleByRoleId(role_id);
	 }
	 
	 
	 public SysUserRole selectUserRoleByUserIdReturnOne(int user_id)
	 {
		 return sysUserMapper.selectUserRoleByUserIdReturnOne(user_id);
	 }
	 
	 
	 
	 public SysUserRole selectUserRoleByRoleIdReturnOne(int role_id)
	 {
		 return sysUserMapper.selectUserRoleByRoleIdReturnOne(role_id);
	 }
	 
	 
	 
	 public void updateUserDepIdById(SysUser sysUser)
	 {
		  sysUserMapper.updateUserDepIdById(sysUser);
	 }
	 
	 
	 public SysUser selectUserByPasswordUsername(NamePassword namePassword)
	 {
		 return sysUserMapper.selectUserByPasswordUsername(namePassword);
	 }

	public void updateUser(SysUser sysUser)
	{
		sysUserMapper.updateUser(sysUser);
	}
	 public List<SysRole> selectRoleByUserName(String user_name)
	 {
		 return sysUserMapper.selectRoleByUserName(user_name);
	 }
	 
	 
	 public List<SysUser> selectUserNameId()
	 {
		 return sysUserMapper.selectAllUser();
	 }
}
