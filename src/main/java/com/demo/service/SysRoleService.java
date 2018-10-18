package com.demo.service;

import java.util.List;

import com.demo.dto.RoleConfigure;
import com.demo.entity.SysModuleRole;
import com.demo.entity.SysRole;
import com.demo.entity.SysUserRole;
//角色接口
public interface SysRoleService 
{
	public void insertRole(SysRole sysRole);
	
	
	
	public List<RoleConfigure> selectRoleConfigure1();
	
	
	
	public List<RoleConfigure> selectRoleConfigure2(int id);
	
	
	
	
	public void deleteUserRole(SysUserRole sysUserRole);
	
	
	public void deleteRoleModule(SysModuleRole sysModuleRole);
	
	
	
	
	public SysRole selectRoleByName(String role_name);
	
	
	public void updateRole(SysRole sysRole);
	
	
	public void deleteRoleUserById(int id);
	
	
	
	public List<SysUserRole> selectRoleUser(int id);
	
	
	
	public void deleteRole(int id);
	
	
	
	public void insertModuleRole(SysModuleRole sysModuleRole);
	
	
	
	public void deleteRoleModule2(int role_id);
	
	
	public List<SysRole> selectAllRole();
	
	
	
	public List<SysModuleRole> selectModuleRoleByModuleId(int id);
	
	
	
	
	public SysRole selectRoleById(int id);
	
	
	public SysModuleRole selectRoleModuleByRoleReturnOne(int role_id);
	
}
