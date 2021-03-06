package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mapper.SysMenuMapper;
import com.demo.po.SysMenu;
import com.demo.po.SysMenuModule;
import com.demo.service.SysMenuService;
//菜单实现类
@Service
public class SysMenuServiceImpl implements SysMenuService
{
	  @Autowired
		private SysMenuMapper sysMenuMapper;
	    
		public void insertMenu(SysMenu sysMenu)
		{
			sysMenuMapper.insertMenu(sysMenu);
			
			
		}
		
		
		
		public void insertMenuModule(SysMenuModule sysMenuModule)
		{
			sysMenuMapper.insertMenuModule(sysMenuModule);
		}
		
		
		
		public SysMenu selectParentMenu(int id)
		{
			return sysMenuMapper.selectParentMenu(id);
		}
		
		
		public List<SysMenu> selectSameMenu(int id)
		{
			return sysMenuMapper.selectSameMenu(id);
		}
		
		
		public SysMenuModule selectIsModuleMenu(SysMenuModule sysMenuModule)
		{
			return sysMenuMapper.selectIsModuleMenu(sysMenuModule);
		}
		
		
		public SysMenu selectParentMenuId(int id)
		{
			return sysMenuMapper.selectParentMenuId(id);
		}
		
		
		public List<SysMenu> selectAllMenu()
		{
			return sysMenuMapper.selectAllMenu();
		}
		
		
		public List<SysMenu> selectUserMenuList(int id)
		{
			return sysMenuMapper.selectUserMenuList(id);
		}
		
		
		public SysMenu selectMenuById(int id)
		{
			return sysMenuMapper.selectMenuById(id);
		}
		
		
		public SysMenuModule selectMenuInModuleMune(SysMenuModule sysMenuModule)
		{
			return sysMenuMapper.selectIsModuleMenu(sysMenuModule);
		}
		
		public SysMenuModule selectModuleMenuByMenuIdRuturnOne(int id)
		{
			return sysMenuMapper.selectModuleMenuByMenuIdRuturnOne(id);
		}
		
		public void updateMenu(SysMenu sysMenu)
		{
			sysMenuMapper.updateMenu(sysMenu);
		}

}
