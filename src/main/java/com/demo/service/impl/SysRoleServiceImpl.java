package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.RoleConfigure;
import com.demo.mapper.SysRoleMapper;
import com.demo.po.SysModuleRole;
import com.demo.po.SysRole;
import com.demo.po.SysUserRole;
import com.demo.service.SysRoleService;
//角色实现类
@Service
public class SysRoleServiceImpl implements SysRoleService
{

	@Autowired
	private SysRoleMapper sysRoleMapper;
    
	public void insertRole(SysRole sysRole)
	{
		sysRoleMapper.insertRole(sysRole);
		
	}
	
	
	public List<RoleConfigure> selectRoleConfigure1()
	{
		return sysRoleMapper.selectRoleConfigure1();
	}
	
	
	public List<RoleConfigure> selectRoleConfigure2(int id)
	{
		return sysRoleMapper.selectRoleConfigure2(id);
	}
	
	
	
	public void deleteUserRole(SysUserRole sysUserRole)
	{
		sysRoleMapper.deleteUserRole(sysUserRole);
	}
	
	public void deleteRoleModule(SysModuleRole sysModuleRole)
	{
		sysRoleMapper.deleteRoleModule(sysModuleRole);
	}
	
	
	
	public SysRole selectRoleByName(String role_name)
	{
		return sysRoleMapper.selectRoleByName(role_name);
	}
	
	public void updateRole(SysRole sysRole)
	{
		sysRoleMapper.updateRole(sysRole);
	}
	
	public void deleteRoleUserById(int id)
	{
		sysRoleMapper.deleteRoleUserById(id);
	}
	
	
	public List<SysUserRole> selectRoleUser(int id)
	{
		return sysRoleMapper.selectRoleUser(id);
	}
	
	
	public void deleteRole(int id)
	{
		sysRoleMapper.deleteRole(id);
	}
	
	
	public void insertModuleRole(SysModuleRole sysModuleRole)
	{
		sysRoleMapper.insertModuleRole(sysModuleRole);
	}
	
	
	public void deleteRoleModule2(int role_id)
	{
		sysRoleMapper.deleteRoleModule2(role_id);
	}
	
	public List<SysRole> selectAllRole()
	{
		return sysRoleMapper.selectAllRole();
	}
	
	
	public List<SysModuleRole> selectModuleRoleByModuleId(int id)
	{
		return sysRoleMapper.selectModuleRoleByModuleId(id);
	}
	
	
	
	public SysRole selectRoleById(int id)
	{
		return sysRoleMapper.selectRoleById(id);
	}
	
	public SysModuleRole selectRoleModuleByRoleReturnOne(int role_id)
	{
		return sysRoleMapper.selectRoleModuleByRoleReturnOne(role_id);
	}
	
}
