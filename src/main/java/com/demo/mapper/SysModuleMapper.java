package com.demo.mapper;
import org.springframework.stereotype.Repository;

import com.demo.po.SysMenu;
import com.demo.po.SysMenuModule;
import com.demo.po.SysModule;
import com.demo.po.SysModuleRole;

import java.util.List;

public interface SysModuleMapper
{

	public void insertModule(SysModule sysModule);
	
	
	
	
	public void insertModuleRole(SysModuleRole sysModuleRole);
	
	
	public List<SysMenu> selectMenu(int id);
	
    public List<SysMenu> selectMenu2();


	public void updateModule(SysModule sysModule);
	
	public List<SysMenu> selectOtherMenu(int id);
	
	
	public void deleteMenu(int id);
	
	
	
	public void deleteModuleMenu(SysMenuModule sysMenuModules);
	
	
	public List<SysMenuModule> selectSameModule(int id);
	
	
	
	public void deleteModule(int id);
	
	public void deleteModuleMenu2(int id);
	
	public void deleteModuleRole(int id);
	
	
	public SysModuleRole selectModuleRoleReturnOne(int id);
	
	
	public SysMenuModule selectModuleMenuReturnOne(int id);
	
	
	public List<SysModule> selectAllModule();
	
	public SysModule selectModuleInformation(int id);

	
	
}
