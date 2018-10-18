package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.SysMenu;
import com.demo.entity.SysMenuModule;
import com.demo.entity.SysModule;
import com.demo.entity.SysModuleRole;
import com.demo.mapper.SysModuleMapper;
import com.demo.service.SysModuleService;
//模块实现类
@Service
public class SysModuleServiceImpl implements SysModuleService
{

	 @Autowired
	   private SysModuleMapper sysModuleMapper;
		
		public void insertModule(SysModule sysModule)
		{
			sysModuleMapper.insertModule(sysModule);
		}
		

		public void insertModuleRole(SysModuleRole sysModuleRole)
		{
			sysModuleMapper.insertModuleRole(sysModuleRole);
		}
		
		
		public List<SysMenu> selectMenu(int id)
		{
			return sysModuleMapper.selectMenu(id);
		}
		
	   public List<SysMenu> selectMenu2()
	   {
		   return sysModuleMapper.selectMenu2();
	   }

		public void updateModule(SysModule sysModule)
		{
			sysModuleMapper.updateModule(sysModule);
		}
		
		public List<SysMenu> selectOtherMenu(int id)
		{
			return sysModuleMapper.selectOtherMenu(id);
		}
		
		
		public void deleteMenu(int id)
		{
			sysModuleMapper.deleteMenu(id);
		}
		
		
		public void deleteModuleMenu(SysMenuModule sysMenuModules)
		{
			sysModuleMapper.deleteModuleMenu(sysMenuModules);
		}
		
		
		
		public List<SysMenuModule> selectSameModule(int id)
		{
			return sysModuleMapper.selectSameModule(id);
		}
		
		
		public void deleteModule(int id)
		{
			sysModuleMapper.deleteModule(id);
		}
		
		public void deleteModuleMenu2(int id)
		{
			sysModuleMapper.deleteModuleMenu2(id);
		}
		
		public void deleteModuleRole(int id)
		{
			sysModuleMapper.deleteModuleRole(id);
		}
		
		
		public SysModuleRole selectModuleRoleReturnOne(int id)
		{
			return sysModuleMapper.selectModuleRoleReturnOne(id);
		}
		
		
		
		public SysMenuModule selectModuleMenuReturnOne(int id)
		{
			return sysModuleMapper.selectModuleMenuReturnOne(id);
		}
		
		public List<SysModule> selectAllModule()
		{
			return sysModuleMapper.selectAllModule();
		}
		
		public SysModule selectModuleInformation(int id)
		{
			return sysModuleMapper.selectModuleInformation(id);
		}

}
