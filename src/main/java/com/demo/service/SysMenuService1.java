package com.demo.service;

import java.util.List;

import com.demo.entity.SysMenu;
import com.demo.entity.SysMenuModule;

public interface SysMenuService1 
{

	public void insertMenu(SysMenu sysMenu);
	
	
	
	
	public void insertMenuModule(SysMenuModule sysMenuModule);
	
	
	
	
	public SysMenu selectParentMenu(int id);
	
	
	
	public List<SysMenu> selectSameMenu(int id);
	
	
	
	public SysMenuModule selectIsModuleMenu(SysMenuModule sysMenuModule);
	
	
	
	public SysMenu selectParentMenuId(int id);
	
	
	
	public List<SysMenu> selectAllMenu();
	
	
	
	public List<SysMenu> selectUserMenuList(int id);
	
	
	
	public SysMenu selectMenuById(int id);
	
	
	
	public SysMenuModule selectMenuInModuleMune(SysMenuModule sysMenuModule);
	
	
	public SysMenuModule selectModuleMenuByMenuIdRuturnOne(int id);
	
	
	public void updateMenu(SysMenu sysMenu);
	
}
