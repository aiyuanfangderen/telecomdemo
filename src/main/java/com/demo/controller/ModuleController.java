package com.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.demo.po.SysModule;
import com.demo.po.SysModuleRole;
import com.demo.po.SysRole;
import com.demo.po.SysUser;
import com.demo.po.SysUserRole;
import com.demo.service.SysDepartmentService;
import com.demo.service.SysMenuService;
import com.demo.service.SysModuleService;
import com.demo.service.SysRoleService;
import com.demo.service.SysUserService;
import com.demo.until.Result;
import com.demo.until.ResultUtil;

//模块的controller
@RestController
@RequestMapping("module")
public class ModuleController
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
	   
	   
	   
	   
	   
	   //添加模块
	   @PostMapping("/insertModule")
	   public Result insertModule(@RequestBody SysModule sysModule)//SysModule sysModule
	   {		  
			Date date=new Date();
			//System.out.println(date.getTime());
			//参数为long型
			Timestamp t = new Timestamp(date.getTime());
			//System.out.println(t);
			sysModule.setCreate_time(t);
		  // sysMenu.setUpdate_time(update_time);
		   
		   
		   sysModuleService.insertModule(sysModule);
		   System.out.println("保存成功");
		   
		   
		   return ResultUtil.success();
	   }
	
	
	  
	   //插入模块角色关联
	   @RequestMapping(value="/insertModuleRole/{id}",
	  		 method = RequestMethod.POST,
	           produces = MediaType.APPLICATION_JSON_VALUE)
	   public Result insertModuleRole(@PathVariable int id, @RequestBody List<SysRole> sysRoles)//SysModuleRole sysModuleRole
	   {
	  	

	  	 for(SysRole sysRole:sysRoles)
	  	 {
	  		 SysModuleRole sysModuleRole=new SysModuleRole();
	  		 sysModuleRole.setModule_id(id);
	  		 sysModuleRole.setRole_id(sysRole.getId());
	  		 System.out.println(sysRole);
	  		 sysRoleService.insertModuleRole(sysModuleRole);
	  	 }
	  	 
	  	 
	  	   System.out.println("保存成功");
	  	   
	  	   
	  	   return ResultUtil.success();
	   }
	
	   
	   
	   
	    //查询模块角色：单独查询模块角色
		@RequestMapping(value = "/selectRole/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Result selectRole(@PathVariable Integer id)
		{

			//传入模块id
			//int id=1;
			//查找所有的角色
			List<SysRole> sysRoles=sysRoleService.selectAllRole();
			//查找出已经和该模块绑定的角色
			List<SysModuleRole> sysModuleRoles=sysRoleService.selectModuleRoleByModuleId(id);
			List<SysRole> sysRoles2=new ArrayList<SysRole>();


			//找出没有和模块绑定的角色
			for(SysRole sysRole:sysRoles)
			{
				for(SysModuleRole sysModuleRole:sysModuleRoles)
				{
					if(sysRole.getId()==sysModuleRole.getRole_id())
					{

						sysRoles2.add(sysRole);
					}

				}
			}


			return ResultUtil.success(sysRoles2);
		}

	   
	   
		//查找所有该模块还没关联的角色和关联的角色
		@GetMapping(value = "/selectAllRole/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Result selectAllRole(@PathVariable Integer id)//int id
		{
			//传入模块id
			//int id=1;
			//查找所有的角色
			List<SysRole> sysRoles=sysRoleService.selectAllRole();
			//查找出已经和该模块绑定的角色
			List<SysModuleRole> sysModuleRoles=sysRoleService.selectModuleRoleByModuleId(id);
			List<SysRole> sysRoles2=new ArrayList<SysRole>();


			//找出没有和模块绑定的角色
			for(SysRole sysRole:sysRoles)
			{
				for(SysModuleRole sysModuleRole:sysModuleRoles)
				{
					if(sysRole.getId()==sysModuleRole.getRole_id())
					{
						//sysRoles.remove(sysRole);
						sysRoles2.add(sysRole);
					}


				}
			}

			sysRoles.removeAll(sysRoles2);
			System.out.println(sysRoles);


			Map<String, Object> mapRole=new HashMap<String, Object>();
			mapRole.put("selectRole", sysRoles2);
			mapRole.put("noselectRole", sysRoles);




			return ResultUtil.success(mapRole);
		}

		
		
		
		
		
		
		
		

		//删除模块角色关联
		@PostMapping(value = "/deleteModuleRole/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Result updateModuleRole(@PathVariable Integer id, @RequestBody List<SysRole> sysRoles) //int id,List<SysRole> sysRoles
		{

			//传入模块
			//int id=1;
			//传入角色的集合
			//List<SysRole> sysRoles=new ArrayList<SysRole>();

			SysRole sysRole1=new SysRole();
			SysRole sysRole2=new SysRole();

			sysRole1.setId(5);
			sysRole2.setId(6);
			sysRoles.add(sysRole1);
			sysRoles.add(sysRole2);

			for(SysRole sysRole:sysRoles)
			{
				SysModuleRole sysModuleRole=new SysModuleRole();
				sysModuleRole.setModule_id(id);
				sysModuleRole.setRole_id(sysRole.getId());

				sysRoleService.deleteRoleModule(sysModuleRole);
			}
			System.out.println("删除成功");
			return ResultUtil.success();
		}

		
		
		
		
		
		
		
		   //删除模块
		   @RequestMapping(value="/deleteModule/{id}",
				   method= RequestMethod.GET,
				   produces= MediaType.APPLICATION_JSON_VALUE)
		   public Result deleteModule(@PathVariable Integer id)
		   {
//			   int id=1;	   
			   //判断模块和角色是否关联，关联则不让删除
			SysModuleRole sysModuleRole=sysModuleService.selectModuleRoleReturnOne(id);
			if(sysModuleRole==null)
			{
				//判断模块和菜单是否关联，关联则不让删除
				
				SysMenuModule sysMenuModule=sysModuleService.selectModuleMenuReturnOne(id);
				if(sysMenuModule==null)
				{
					 //删除模块
					   sysModuleService.deleteModule(id);
				}
				
				else
				{
					 return ResultUtil.error(-1, "模块和菜单关联不让删除");
				}
				
			}
			
			else
			{
				return ResultUtil.error(-1, "模块和角色关联不让删除");
			}
			
			
			   	   	   	   	   	   	  	   	 	   
			  return ResultUtil.success();
		   }
		   		
		
		
	   
	   
	   
		   
			//删除模块和菜单对应表的数据
			@RequestMapping(value = "/deleteModuleMenuDate/{id2}",produces = MediaType.APPLICATION_JSON_VALUE)
			public Result deleteModuleMenuDate(@PathVariable Integer id2, @RequestBody List<Integer> ids )//SysMenuModule sysMenuModule int id2,List<Integer> ids
			{

				//int id2=2;
				//List<Integer> ids=new ArrayList<Integer>();
				//ids.add(2);
				//ids.add(3);



				for(Integer id1:ids)
				{

					//传入要删除的菜单id和模块id;
					SysMenuModule sysMenuModule=new SysMenuModule();
					sysMenuModule.setModule_id(id2);
					sysMenuModule.setMenu_id(id1);




					SysMenuModule sysMenuModule2=new SysMenuModule();

					//将点击菜单从菜单模块表删除
					sysModuleService.deleteModuleMenu(sysMenuModule);

					//判断是不是叶子节点
					//用传进来的菜单id去查找它的所有子菜单
					List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();

					int id=sysMenuModule.getMenu_id();
					sysOtherMenus=fun2(id,sysOtherMenus);
					//如果不是叶子节点，再递归找到该菜单的子菜单，将子菜单随着点击菜单一起从模块菜单表删除
					if(!sysOtherMenus.isEmpty())
					{
						for(SysMenu sysMenu:sysOtherMenus)
						{
							sysMenuModule2.setModule_id(sysMenuModule.getModule_id());
							sysMenuModule2.setMenu_id(sysMenu.getId());
							//将点击菜单的子菜单从模块菜单关联表删除
							sysModuleService.deleteModuleMenu(sysMenuModule2);
						}
					}
					//如果是叶子节点，不做处理

					//再判断该菜单是不是顶级
					//再用传进来的模块id,去查找它的上一级菜单属性
					SysMenu sysMenu=sysMenuService.selectParentMenu(sysMenuModule.getMenu_id());
					System.out.println(sysMenu);

//				  		    Boolean flag=false;
					//如果不是顶级菜单，再查找该菜单的同级菜单，如果该菜单的同级菜单的其他菜单都已经被删除了，则在删除该菜单时，将点击菜单的上一级菜单也删除
					if(sysMenu.getMenu_parent_id()!=0)
					{

						System.out.println(sysMenu.getMenu_parent_id());
						fun6(sysMenu.getMenu_parent_id(),sysMenuModule.getModule_id());
					}


				}



				return ResultUtil.success();
			}
		   
		   
		   
		   
			 //递归函数
			   public List<SysMenu> fun2(int id, List<SysMenu> sysOtherMenus)
			   {
				  List<SysMenu> sysMenus=sysModuleService.selectOtherMenu(id);
				  sysOtherMenus.addAll(sysMenus);
				  if(sysMenus!=null)
				   {
					   for(SysMenu sysMenu:sysMenus)
					   {
						  int id1=sysMenu.getId();
						  fun2(id1, sysOtherMenus);
						  
						    
					   }
					   
					   
					   
				   }
				  
				 return sysOtherMenus;    
			   }
		   
		   
		   
			   
			   public void fun6(int parent_id,int module_id)
			   {
				   if(parent_id!=0)
				   {
					   //判断同级是否全部删除，全部删除则将上级删除，再判断上 级的同级是否全部被删除，直到顶级
						List<SysMenu> sysMenus2=sysMenuService.selectSameMenu(parent_id);
						  //判断同级是否为空，不为空再递归上一级是否为空
						boolean flag=true;
						for(SysMenu sysMenu:sysMenus2)
						{
							SysMenuModule sysMenuModule=new SysMenuModule();
							sysMenuModule.setMenu_id(sysMenu.getId());
							sysMenuModule.setModule_id(module_id);
							SysMenuModule sysMenuModule2=sysMenuService.selectIsModuleMenu(sysMenuModule);
							if(sysMenuModule2!=null)
							{
								flag=false;
							}
						}
						
						
						 if(flag)
						 {
							 //查找上一级菜单
							SysMenu sysMenu=sysMenuService.selectParentMenuId(parent_id);
							SysMenuModule sysMenuModule=new SysMenuModule();
							sysMenuModule.setModule_id(module_id);
							sysMenuModule.setMenu_id(sysMenu.getId());
							sysModuleService.deleteModuleMenu(sysMenuModule);
							if(sysMenu.getMenu_parent_id()!=0)
							{
								SysMenu sysMenu2=sysMenuService.selectMenuById(sysMenu.getMenu_parent_id());
								
								
								fun6(sysMenu2.getMenu_parent_id(), module_id);
							}
							
						 }
				   }
				      
						 
			   }

			   
			   
			   
			   
			   
			   
			   
			 //查询已选择的菜单，和没选择的菜单列表
				@GetMapping(value = "/selectMenuList/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
				public Result selectMenuList(@PathVariable Integer id)//int id
				{

					//传入模块id
					//int id=1;


					//先在菜单表中，查询所有的菜单
					List<SysMenu> sysMenus=sysMenuService.selectAllMenu();

					//再在模块菜单表中查询该模块已经和菜单关联的菜单id
					List<SysMenuModule> SysMenuModule=sysModuleService.selectSameModule(id);

					List<SysMenu> list=new ArrayList<SysMenu>();

					//然后获得没有关联的菜单
					for(SysMenu sysMenu:sysMenus)
					{
						for(SysMenuModule sysMenuModule2:SysMenuModule)
						{
							if(sysMenu.getId()==sysMenuModule2.getMenu_id())
							{
								//将相同的添加到list集合
								list.add(sysMenu);
							}
						}
					}
					//将相同的去除
					sysMenus.removeAll(list);


					//未选择的
					System.out.println(sysMenus);

					System.out.println("********"+sysMenus);
					//已经选择的
					System.out.println(list);


					//已选按照属性结构输出
					int authId=0;
					JSONArray jsonArray=new JSONArray();
					jsonArray=this.getAuthsByparentId2(authId,list);





					//向上递归查找菜单，因为有些菜单的上级菜单可能不在选择菜单，但是列表选择时要选择出来
					List<SysMenu> sysMenus3=new ArrayList<SysMenu>();
					for(SysMenu sysMenu:sysMenus)
					{
						List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();
						sysOtherMenus=fun5(sysMenu.getMenu_parent_id(), sysOtherMenus);
						sysMenus3.addAll(sysOtherMenus);
					}

					sysMenus3.addAll(sysMenus);



					//去除sysMenus3中重复的菜单
					List<SysMenu> sysMenus5=new ArrayList<SysMenu>();
					for(SysMenu sysMenus4:sysMenus3)
					{
						boolean flag=true;
						if(sysMenus5.isEmpty())
						{
							sysMenus5.add(sysMenus4);
						}
						else
						{
							for(SysMenu sysMenus6:sysMenus5)
							{
								if(sysMenus6.getId()==sysMenus4.getId())
								{
									flag=false;
								}
							}

							if(flag)
							{
								sysMenus5.add(sysMenus4);
							}

						}
					}





					JSONArray jsonArray2=new JSONArray();
					jsonArray2=this.getAuthsByparentId2(authId,sysMenus5);









					System.out.println(jsonArray2);


					Map<String, Object> menu=new HashMap<String, Object>();
					menu.put("select", jsonArray);
					menu.put("noselect", jsonArray2);

					return ResultUtil.success(menu);


				}
			   
			   
			   
			    public List<SysMenu> fun5(int id, List<SysMenu> sysOtherMenus)
			    {
			 	 
			    	SysMenu sysMenus=sysMenuService.selectParentMenuId(id);
			 	  
			 	  if(sysMenus!=null)
			 	   {
			 		   sysOtherMenus.add(sysMenus);
			 	       int id1=sysMenus.getMenu_parent_id();
			 		   fun5(id1, sysOtherMenus);
			 			   			 		   				   
			 	   }
			 	  
			 	 return sysOtherMenus;   
			 	  
			    }
			   
			   
			   
			  //将已选择菜单的变成树形菜单
				/**
				 * 取菜单
				 * @param parentId
				 * @return
				 * @throws JSONException 
				 * @throws NumberFormatException 
				 */
				private JSONArray getAuthsByparentId2(int parentId,List<SysMenu> sysMenus) throws NumberFormatException, JSONException{
					JSONArray jsonArray=this.getAuthByParentId2(parentId,sysMenus);
					for(int i=0;i<jsonArray.size();i++){
						JSONObject jsonObject=jsonArray.getJSONObject(i);
//								jsonArray.getJSONObject(i);
						if (Integer.parseInt(jsonObject.getString("display"))!=1)
						{

							continue;
						}
						else
						{
							
							
							jsonObject.put("children",getAuthsByparentId2(Integer.parseInt(jsonObject.getString("id")),sysMenus));
						}
					}
					return jsonArray;
				}
			   
			   
			   
			   
			   
				  /**
					 * 取子菜单
					 * @param parentId
					 * @return
				 * @throws JSONException 
					 */
					private JSONArray getAuthByParentId2(int parentId,List<SysMenu> sysMenus) throws JSONException
					{
						JSONArray jsonArray=new JSONArray();
						List<SysMenu> sysMenus2=new ArrayList<SysMenu>();
			            for(SysMenu sysMenu:sysMenus)
			            {
			            	if(sysMenu.getMenu_parent_id()==parentId)
			            	{
			            		sysMenus2.add(sysMenu);
			            	}
			            }
					
						for (SysMenu a:sysMenus2){
							JSONObject jsObj=new JSONObject();
							//String s=String.valueOf(a.getId());
							jsObj.put("id",a.getId());
							jsObj.put("name",a.getMenu_name());
							jsObj.put("display",a.getIs_display());
							jsObj.put("icon", a.getMenu_icon());
							jsObj.put("url", a.getMenu_url());
							jsObj.put("menu_parent_id", a.getMenu_parent_id());
//							JSONObject attrObj= new JSONObject();	
//							jsObj.put("attributes", attrObj);
							jsonArray.add(jsObj);
//							add(jsObj);
						}
						
						return jsonArray;
					}
	   
	
					
					
					
					
					
					
					  //添加角色用户的关系
				    @PostMapping(value = "/insertRoleUser2/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
				    public Result insertRoleUser2(@PathVariable int id, @RequestBody List<SysUser> sysUsers)//角色id和用户id的集合int id,List<SysUser> sysUsers
				    {
				 	   //System.out.println("输出");
				 	   //int id=3;
				 	   //List<SysUser> sysUsers=new ArrayList<SysUser>();
				 	   //SysUser sysUser=new SysUser();
				 	  // sysUser.setId(2);
				 	   //sysUsers.add(sysUser);
				 	   
				 	   
				 	   for(SysUser sysUser2:sysUsers)
				 	   {
				 		SysUserRole sysUserRole=new SysUserRole();
				 		sysUserRole.setRole_id(id);
				 		sysUserRole.setUser_id(sysUser2.getId());
				 		sysUserService.insertUserRole(sysUserRole);
				 	   }
				 	  
				 	  

				 	   return ResultUtil.success();
				    }
				    
				    
				  //删除角色用户的关系
				    @RequestMapping(value = "/delectRoleUser/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
				    public Result delectRoleUser(@PathVariable int id, @RequestBody List<SysUser> sysUsers)//角色id和用户id的集合int id,List<SysUser> sysUsers
				    {
				 	   //int id=3;
				 	   //List<SysUser> sysUsers=new ArrayList<SysUser>();
				 	   //SysUser sysUser=new SysUser();
				 	   //sysUser.setId(2);
				 	   //sysUsers.add(sysUser);
				 	   
				 	   
				 	   for(SysUser sysUser2:sysUsers)
				 	   {
				 		SysUserRole sysUserRole=new SysUserRole();
				 		sysUserRole.setRole_id(id);
				 		sysUserRole.setUser_id(sysUser2.getId());
				 		sysRoleService.deleteUserRole(sysUserRole);
				 	   }
				 	  
				 	  

				 	   return ResultUtil.success();
				    }
				    
				    
				    
				    
				    
				    
				    
				    @GetMapping(value="/selectModuleInformation/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
				    public Result selectModuleInformation(@PathVariable Integer id)
				    {
				 	
				 	SysModule sysModule=sysModuleService.selectModuleInformation(id);
				 	 
				 	   System.out.println(sysModule);
				 	   return ResultUtil.success(sysModule);
				    }
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
					@PostMapping (value = "/insertMenuModule/{id2}", produces = MediaType.APPLICATION_JSON_VALUE)
					public Result insertMenuModule( @PathVariable Integer id2, @RequestBody List<Integer> ids)//SysMenuModule sysMenuModule
					{

//					int id2=2;
//					List<Integer> ids=new ArrayList<Integer>();
//					ids.add(2);
//					ids.add(3);

						for(Integer id1:ids)
						{
							SysMenuModule sysMenuModule=new SysMenuModule();
							sysMenuModule.setModule_id(id2);
							sysMenuModule.setMenu_id(id1);
							SysMenuModule sysMenuModule5=sysMenuService.selectIsModuleMenu(sysMenuModule);

							if(sysMenuModule5==null)
							{
								//将点击菜单插入关联表
								sysMenuService.insertMenuModule(sysMenuModule);
							}

							Date date=new Date();
							Timestamp t = new Timestamp(date.getTime());
							sysMenuModule.setCreate_time(t);

							//用传进来的菜单id去查找它的所有子菜单，将所有子菜单和这个模块id相关联，插入模块菜单关联表
							List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();

							int id=sysMenuModule.getMenu_id();
							sysOtherMenus=fun2(id,sysOtherMenus);

							if(!sysOtherMenus.isEmpty())
							{
								for(SysMenu sysMenu:sysOtherMenus)
								{
									SysMenuModule sysMenuModule2=new SysMenuModule();
									sysMenuModule2.setModule_id(sysMenuModule.getModule_id());
									sysMenuModule2.setMenu_id(sysMenu.getId());
									//判断这个菜单是否被关联
									SysMenuModule sysMenuModule6=sysMenuService.selectIsModuleMenu(sysMenuModule2);
									if(sysMenuModule6==null)
									{
										sysMenuService.insertMenuModule(sysMenuModule2);
									}



								}
							}


							//用传进来的菜单id去查找他的所有上级


							//用传进来的菜单id去查找它的上级菜单id属性
							SysMenu sysMenu=sysMenuService.selectParentMenu(sysMenuModule.getMenu_id());
							sysOtherMenus=fun5(sysMenu.getMenu_parent_id(), sysOtherMenus);

							//插入上级菜单没有和模块关联的菜单
							for(SysMenu sysMenu2:sysOtherMenus)
							{
								//判断该模块和id是否在模块菜单表已经关联

								SysMenuModule sysMenuModule3=new SysMenuModule();
								sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
								sysMenuModule3.setMenu_id(sysMenu2.getId());
								SysMenuModule sysMenuModule4=sysMenuService.selectIsModuleMenu(sysMenuModule3);
								//如果没有关联则插入
								if(sysMenuModule4==null)
								{
									sysMenuService.insertMenuModule(sysMenuModule3);
								}

								//如果已经关联则不用插入
							}











						}



//						//再用传进来的模块id,去查找它的上一级菜单属性
//						SysMenu sysMenu=sysMenuService.selectParentMenu(sysMenuModule.getMenu_id());
				//
				//
				//
				//
//						Boolean flag=false;
//						//如果上级菜单属性不为0，则查找与传入菜单同级的菜单，再判断同级其他菜单是否全部关联，如果全部关联，则将传入进来菜单的上一级菜单也与当前模块进行关联，即也
//						//将其模块id和菜单id相关联
//						if(sysMenu.getMenu_parent_id()!=0)
//						{
//							//查找同级id,包括点击的菜单id
//							List<SysMenu> sysMenus2=sysMenuService.selectSameMenu(sysMenu.getMenu_parent_id());
//							System.out.println(sysMenus2);
				//
//							for(SysMenu sysMenu2:sysMenus2)
//							{
//								SysMenuModule sysMenuModule3=new SysMenuModule();
//								sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
//								sysMenuModule3.setMenu_id(sysMenu2.getId());
//								SysMenuModule sysMenuModule4=sysMenuService.selectIsModuleMenu(sysMenuModule3);
//								if(sysMenuModule4==null)
//								{
//									flag=true;
//								}
				//
//							}
				//
				//
//							if(!flag)
//							{
//								//都在，则将父级添加到已选的菜单
//								SysMenu sysMenu2=sysMenuService.selectParentMenuId(sysMenu.getMenu_parent_id());
//								SysMenuModule sysMenuModule3=new SysMenuModule();
//								sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
//								sysMenuModule3.setMenu_id(sysMenu2.getId());
//								sysMenuService.insertMenuModule(sysMenuModule3);
				//
//							}
				//
				//
				//
				//
//						}
				//
				//
				//
				//
				//
//					//  sysMenuService.insertMenuModule(sysMenuModule);
						System.out.println("保存成功");


						return ResultUtil.success();
					}

				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
					
					
					@PostMapping(value = "/updateModule", produces = MediaType.APPLICATION_JSON_VALUE)
					public Result updateModule(SysModule sysModule)
					{
						//SysModule sysModule=new SysModule();
						//sysModule.setId(1);
						//sysModule.setModule_name("模块名");
						sysModuleService.updateModule(sysModule);
						return ResultUtil.success();
					}
					
					
					
					
					
					
					

}
