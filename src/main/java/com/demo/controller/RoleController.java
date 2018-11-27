package com.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.UserDepartment;
import com.demo.po.SysDepartment;
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
//角色的controller
@RestController
@RequestMapping("/role")
public class RoleController
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
	   
	   
	   
	   //添加角色
	   @PostMapping("/insertRole")
	   public Result insertRole(@RequestBody SysRole sysRole)
	   {
		   	  
			Date date=new Date();
			//System.out.println(date.getTime());
			//参数为long型
			Timestamp t = new Timestamp(date.getTime());
			//System.out.println(t);
			sysRole.setCreate_time(t);
		  // sysMenu.setUpdate_time(update_time);
		   
		   
		   sysRoleService.insertRole(sysRole);
		   System.out.println("保存成功");
		   return ResultUtil.success();
	   }
	   
	   
	   
	 
	   //查找所有的在职且没有被当前角色关联的用户，供角色选择
	   @RequestMapping(
			   value="/selectAllUser/{id}", 
			   method = RequestMethod.POST,
	           produces = MediaType.APPLICATION_JSON_VALUE)
	   public Result selectAllUser(@PathVariable int id)
	   {
		   //传入角色id
//		   int id=1;
		List<SysUser> sysUsers=sysUserService.selectAllUser();
		//通过角色id去角色用户表查找该角色和哪些用户已经关联
		   List<SysUserRole> sysUserRoles=sysRoleService.selectRoleUser(id);
		   List<SysUser> sysUsers2=new ArrayList<SysUser>();
		for(SysUser sysUser:sysUsers)
		{
			
			for(SysUserRole sysUserRole:sysUserRoles)
			{
				if(sysUser.getId()==sysUserRole.getUser_id() && sysUser.getStauts()==0)
				{
					sysUsers2.add(sysUser);
				}
				
			}
		}
		sysUsers.removeAll(sysUsers2);
//		Set<SysUser> sysUsers3=new TreeSet<SysUser>(sysUsers2);	
		System.out.println(sysUsers2);


		   //上面已经关联，不需要再写
		   Map<String, Object> userMap=new HashMap<String, Object>();
		   userMap.put("selectUser",sysUsers2);
		   userMap.put("noselectUser",sysUsers);


		   return ResultUtil.success(userMap);
	   }
	   
	   
	   
	   //将角色和用户关联起来
	   @RequestMapping("/insertRoleUser")
	   public Result insertRoleUser(int id)
	   {
	///	   int id=1;
		   List<SysUser> sysUsers=new ArrayList<SysUser>();
			  
		   SysUser sysUser=new SysUser();
		   sysUser.setId(33);
	///	   sysUser.setReal_name("燕青");
		   sysUsers.add(sysUser);
		   	  	   
		   for(SysUser sysUser2:sysUsers)
		   {
			   SysUserRole sysUserRole=new SysUserRole();
			   sysUserRole.setRole_id(id);
			   sysUserRole.setUser_id(sysUser2.getId());
			   sysUserService.insertUserRole(sysUserRole);
		   }
		   return ResultUtil.success();
	   }
	   
	   
	   
	   
	   
	 
	 
	  //查找所有的角色id,和角色名称
	    @RequestMapping("/selectRoleConfigure1")
	    public Result selectRoleConfigure1()
	    {
	 	 List<SysRole> sysRoles=sysRoleService.selectAllRole();
//	 	List<RoleConfigure> roleConfigures=sysRoleService.selectRoleConfigure1();
	 	 System.out.println(sysRoles);
	 	   
	 	   return ResultUtil.success(sysRoles);
	    }

	   
	    
	    
	    //角色信息查询，通过id查找角色的详细信息以及用户和部门的部分信息
	      @RequestMapping("/selectRoleConfigure2")
	      public Result selectRoleConfigure2(int id)//int id
	      {
//	   	 int id=1;
	   	 //查询
	   	 //先查询角色信息
	   	SysRole sysRole=sysRoleService.selectRoleById(id);
	   	 //再查询角色的对应用户部门信息
	   	List<UserDepartment> userDepartments=new ArrayList<UserDepartment>();
	   	List<SysUserRole> sysUserRoles=sysUserService.selectUserRoleByRoleId(id);
	   	 for(SysUserRole sysUserRole:sysUserRoles)
	   	 {
	   		SysUser sysUser=sysUserService.selectUserById(sysUserRole.getUser_id());
	   	   SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(sysUser.getDep_id());
	   	   
	   	   UserDepartment userDepartment=new UserDepartment();
	   	   userDepartment.setUser_name(sysUser.getUser_name());
	   	   userDepartment.setReal_name(sysUser.getReal_name());
	   	   userDepartment.setDep_name(sysDepartment.getDep_name());
	   	   userDepartment.setDep_area(sysDepartment.getDep_area());
	   	   userDepartments.add(userDepartment);
	   	   
	   	 }
	   	System.out.println(sysRole);
	   	System.out.println(userDepartments);

	   	
	   	Map map=new HashMap();
	   	map.put("Role",sysRole);
	   	map.put("UserDepartment", userDepartments);
	   	
	   	
	   	 			   
	   	   return ResultUtil.success(map);
	      }
	      
	    
	    
	      
	      //修改角色信息
	        @PostMapping("/updateRole")
	        public Result updateRole(@RequestBody SysRole sysRole)
	        {

	     	  
	     		Date date=new Date();
	     		//System.out.println(date.getTime());
	     		//参数为long型
	     		Timestamp t = new Timestamp(date.getTime());
	     		//System.out.println(t);
	     		sysRole.setCreate_time(t);
	     	  // sysMenu.setUpdate_time(update_time);
	     	   
	     	   
	     	   sysRoleService.updateRole(sysRole);
	     	   System.out.println("保存成功");
	     	   
	     	   
	     	   return ResultUtil.success();
	        }

	    
	        
	        
	        
	      //修改角色用户关系
	        @RequestMapping("/updateRoleUser")
	        public Result updateRoleUser(int id, List<SysUser> sysUsers)//角色id和用户id的集合
	        {
	      
	     	   //将原来用户角色对应关系全部删除
	     	   sysRoleService.deleteRoleUserById(id);
	     	   
	     	   for(SysUser sysUser2:sysUsers)
	     	   {
	     		   SysUserRole sysUserRole=new SysUserRole();
	     		   sysUserRole.setRole_id(id);
	     		   sysUserRole.setUser_id(sysUser2.getId());
	     		   sysUserService.insertUserRole(sysUserRole);
	     	   }
	     	   return ResultUtil.success();
	        }
	    
	    
	    
	        
	        
	        //删除角色配置
	          @RequestMapping(
	                  value = "/deleteRole/{id}", 
	                  method = RequestMethod.GET,
	                  produces = MediaType.APPLICATION_JSON_VALUE
	                  )
	        public Result deleteRole(@PathVariable Integer id)
	          {
//	       	   int id=2;
	       	   //通过角色id去角色用户表查找该角色下是否还存在用户	   
	          
	       	  SysUserRole sysUserRoles=sysUserService.selectUserRoleByRoleIdReturnOne(id);
	       	//   List<SysUserRole> sysUserRoles=sysRoleService.selectRoleUser(id);
	       	   if(sysUserRoles==null)
	       	   {
	       		   //判断角色和模块是否存在关系，存在则不让删除，不存在则删除
	       		 SysModuleRole sysModuleRole=sysRoleService.selectRoleModuleByRoleReturnOne(id);
	       		   if(sysModuleRole==null)
	       		   {
	       			   //删除角色
	       			   sysRoleService.deleteRole(id);
	       		   }
	       		   else
	       		   {
	       			   return ResultUtil.error(-2, "角色和模块还有关联，不能删除");
	       		   }		 		   		   
	       	   }
	       	   
	       	   else {
	       		return ResultUtil.error(-2, "角色下还有用户，不能删除");
	       	}
	       	   return ResultUtil.success();
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
			    
	   

}
