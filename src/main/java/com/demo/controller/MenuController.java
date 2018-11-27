package com.demo.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.po.SysMenu;
import com.demo.po.SysMenuModule;
import com.demo.service.SysDepartmentService;
import com.demo.service.SysMenuService;
import com.demo.service.SysModuleService;
import com.demo.service.SysRoleService;
import com.demo.service.SysUserService;
import com.demo.until.Result;
import com.demo.until.ResultUtil;

//菜单controller
@RestController
@RequestMapping("/menu")
public class MenuController 
{

	        @Autowired
			private SysMenuService sysMenuService;
		   @Autowired
		   private SysModuleService sysModuleService;
		   @Autowired
		   private SysRoleService sysRoleService;
		   @Autowired
		   private SysDepartmentService sysDepartmentService;
		   @Autowired
		   private SysUserService sysUserService;   
		   
		   
		   //添加菜单
		   @PostMapping(value="/insertMenu")
		   public Result insertMenu(@RequestBody SysMenu sysMenu)//SysMenu sysMenu
		   {
			   
//			   SysMenu sysMenu=new SysMenu();
//			   sysMenu.setMenu_name("活动项目");
//			   
//			   sysMenu.setMenu_parent_id(0);
			   
				Date date=new Date();
				//System.out.println(date.getTime());
				//参数为long型
				Timestamp t = new Timestamp(date.getTime());
				//System.out.println(t);
			   sysMenu.setCreate_time(t);
			  // sysMenu.setUpdate_time(update_time);
			   
			   
			   sysMenuService.insertMenu(sysMenu);
			   System.out.println("保存成功");
			   
			   
			   return ResultUtil.success();
		   }
		   
		   
		   
		 //删除菜单
		   @RequestMapping(
		           value = "/deleteMenu2/{id}", 
		           method = RequestMethod.GET,
		           produces = MediaType.APPLICATION_JSON_VALUE
		           )
		    public Result deleteMenu2(@PathVariable int id)//int id
		    {
		 	   
		 	   
//		 	   int id=6;
		 	   List<SysMenu> sysMenus=sysModuleService.selectOtherMenu(id);
		 	   if(sysMenus.isEmpty())
		 	   {
		 		   //判断菜单和模块是否存在关联，如果存在则不让删除
		 		SysMenuModule sysMenuModule=sysMenuService.selectModuleMenuByMenuIdRuturnOne(id);
		 		   if(sysMenuModule==null)
		 		   {
		 			  sysModuleService.deleteMenu(id);
		 	 		   System.out.println("删除成功");
		 		   }
		 		   
		 		   else
		 		   {
		 			  return ResultUtil.error(-2, "菜单和模块有关联，无法删除");
			       }
		 		   
		 	   }
		 	   
		 	   else
		 	   {
		 		 System.out.println("存在子菜单，删除失败");
		 		 return ResultUtil.error(-2, "存在子菜单，删除失败");
		 	   }
		 	   

		 	   return ResultUtil.success();
		    }
		   
	
	
			//查询所有的菜单   
		    @RequestMapping("selectAllMenu")
		    public Result selectAllMenu()//SysMenu sysMenu
		    {
		 	   int menu_id=0;
		       
		 	   
		 	  JSONArray jsonArray=new JSONArray();
				jsonArray=this.getMenusByparentId(menu_id);
				return ResultUtil.success(jsonArray);
		    }
		    
		    
		    
		  //查询菜单详细内容
		   // @PostMapping(value="/selectMenuDetail")
		    @RequestMapping(
		            value = "/selectMenuDetail/{id}", 
		            method = RequestMethod.POST,
		            produces = MediaType.APPLICATION_JSON_VALUE
		            )
		    public Result selectMenuDetail(@PathVariable Integer id)
		    {
		 	 // int id=1;
		    	System.out.println(id);
		 	 SysMenu sysMenu=sysMenuService.selectMenuById(id);
		    	
				return ResultUtil.success(sysMenu);
		    	//return null;
		    }


		  //更新菜单     
		    @PostMapping("/updateMenu")
		    public Result updateMenu(@RequestBody SysMenu sysMenu)//SysMenu sysMenu
		    {
		    	sysMenuService.updateMenu(sysMenu);
		    	System.out.println("更新成功");
		    	
		    	
		    	return ResultUtil.success();
		    }
	
		   
		   
		   
			/*直接从数据库取菜单		
			/**
			 * 从数据库获取菜单
			 * @param parentId
			 * @return
			 * @throws JSONException 
			 * @throws NumberFormatException 
			 */
			private JSONArray getMenusByparentId(int parentId) throws NumberFormatException, JSONException{
				JSONArray jsonArray=this.getMenuByParentId(parentId);
				for(int i=0;i<jsonArray.size();i++){
					JSONObject jsonObject=jsonArray.getJSONObject(i);
//							jsonArray.getJSONObject(i);
					if (Integer.parseInt(jsonObject.getString("display"))!=1){
						continue;
					}else{
						jsonObject.put("children",getMenusByparentId(Integer.parseInt(jsonObject.getString("id"))));
					}
				}
				return jsonArray;
			}

	   
			
			
			 /**
				 * 从数据库获取子菜单
				 * @param parentId
				 * @return
			 * @throws JSONException 
				 */
				private JSONArray getMenuByParentId(int parentId) throws JSONException 
				{
					JSONArray jsonArray=new JSONArray();
				    List<SysMenu> auths=sysMenuService.selectSameMenu(parentId);
//		            List<SysDepartment> sysDepartments=sysDepartmentService.selectDepartmentByDepId(parentId);
				
					for (SysMenu a:auths){
						JSONObject jsObj=new JSONObject();
						String s=String.valueOf(a.getId());
						jsObj.put("id",s);
						jsObj.put("name",a.getMenu_name());
						jsObj.put("display",a.getIs_display());							
						jsonArray.add(jsObj);
					}
					
					return jsonArray;
				}

	
	
	
	
}
