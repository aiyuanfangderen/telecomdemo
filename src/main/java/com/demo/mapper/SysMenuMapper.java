package com.demo.mapper;
//菜单的mapper接口
import com.demo.entity.SysMenu;
import com.demo.entity.SysMenuModule;
import org.springframework.stereotype.Repository;

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
