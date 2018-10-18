package com.demo.mapper;
//角色的mapper接口
import com.demo.dto.RoleConfigure;
import com.demo.entity.SysModuleRole;
import com.demo.entity.SysRole;
import com.demo.entity.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SysRoleMapper
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
	
	
	public List<SysModuleRole> selectModuleRoleByModuleId(int module_id);
	
	
	public SysRole selectRoleById(int id);
	
	public SysModuleRole selectRoleModuleByRoleReturnOne(int role_id);
	
	
	
}
