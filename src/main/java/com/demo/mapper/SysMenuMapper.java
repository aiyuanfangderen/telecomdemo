package com.demo.mapper;
import org.springframework.stereotype.Repository;

import com.demo.po.SysMenu;
import com.demo.po.SysMenuModule;

import java.util.List;

public interface SysMenuMapper
{

    public void insertMenu(SysMenu sysMenu);
	
	
    void insertMenuModule(SysMenuModule sysMenuModule);
	
	void updateMenu(SysMenu sysMenu);
	
	SysMenu selectParentMenu(int id);
	
	
	
	List<SysMenu> selectSameMenu(int id);
	
	
	SysMenuModule selectIsModuleMenu(SysMenuModule sysMenuModule);
	
	SysMenu selectParentMenuId(int id);
	
	
	List<SysMenu> selectAllMenu();
	
	
	List<SysMenu> selectUserMenuList(int id);
	
	SysMenu selectMenuById(int id);
	
	
	
	SysMenuModule selectMenuInModuleMune(SysMenuModule sysMenuModule);
	
	
	SysMenuModule selectModuleMenuByMenuIdRuturnOne(int id);

}
