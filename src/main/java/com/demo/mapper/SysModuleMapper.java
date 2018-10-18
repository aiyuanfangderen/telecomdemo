package com.demo.mapper;
//模块的mapper接口
import com.demo.entity.SysMenu;
import com.demo.entity.SysMenuModule;
import com.demo.entity.SysModule;
import com.demo.entity.SysModuleRole;
import org.springframework.stereotype.Repository;

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
