package com.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.UserPosition;
import com.demo.po.SysDepartLeader;
import com.demo.po.SysDepartment;
import com.demo.po.SysUser;
import com.demo.po.SysUserRole;
import com.demo.service.SysDepartmentService;
import com.demo.service.SysMenuService;
import com.demo.service.SysModuleService;
import com.demo.service.SysRoleService;
import com.demo.service.SysUserService;
import com.demo.until.Result;
import com.demo.until.ResultUtil;

//用户controller
@RestController
@RequestMapping("/user")
public class UserController
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
	   
	   
	   
	   //插入用户信息
	   @PostMapping("/insertUser")
	   public Result insertUser(@RequestBody SysUser sysUser)
	   {


		   Date date=new Date();
		   //System.out.println(date.getTime());
		   //参数为long型
		   Timestamp t = new Timestamp(date.getTime());
		   //System.out.println(t);
		   sysUser.setCreate_time(t);
		   // sysMenu.setUpdate_time(update_time);


		   sysUserService.insertUser(sysUser);
		   System.out.println("保存成功");

		   //判断用户是否有职位，如果有职位则去职位表插入部门对应的领导，再去领导表插入用户id和部门id
		   if(sysUser.getUser_position()!=null)
		   {



			   if(sysUser.getUser_position().equals("正职"))
			   {
				   //找到对应的部门
				   //通过插入的用户的名字找到用户的id
	// 用id去查找

				   SysUser sysUser2=sysUserService.selectUserById(sysUser.getId());
//			  SysUser sysUser2=sysUserService.selectUserByName(sysUser.getUser_name());

				   SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(sysUser.getDep_id());
				   UserPosition userPosition=new UserPosition();
				   userPosition.setId(sysDepartment.getId());
	//将用户id变成字符串
				   userPosition.setName(String.valueOf(sysUser.getId()));
				   //通过部门id去将正职人姓名修改到（插入）部门表的正职字段
				   sysDepartmentService.updatePrincipal(userPosition);


				   //将用户id和领导id插入领带表中
				   SysDepartLeader sysDepartLeader=new SysDepartLeader();
				   sysDepartLeader.setDep_id(sysDepartment.getId());
				   sysDepartLeader.setUser_id(sysUser2.getId());
				   sysDepartmentService.insertDepartmentLeader(sysDepartLeader);

			   }


			   if(sysUser.getUser_position().equals("副职"))
			   {
				   //找到对应的部门
				   //通过插入的用户的名字找到用户的id
	//用id去查找

				   SysUser sysUser2=sysUserService.selectUserById(sysUser.getId());
//				  SysUser sysUser2=sysUserService.selectUserByName(sysUser.getReal_name());
				   SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(sysUser2.getDep_id());
				   UserPosition userPosition=new UserPosition();
				   userPosition.setId(sysDepartment.getId());

				   //判断该部门是否已经存在一个副职了
				   if(sysDepartment.getDep_deputy()==null||sysDepartment.getDep_deputy().equals(""))
				   {
	//将用户id变成字符串
					   userPosition.setName(String.valueOf(sysUser.getId()));
				   }

				   else
				   {
	//将用户id变成字符串
					   userPosition.setName(sysDepartment.getDep_deputy()+","+String.valueOf(sysUser.getId()));
				   }


				   //通过部门id去将副职人姓名修改到（插入）部门表的副职字段
				   sysDepartmentService.updateDeputy(userPosition);
				   //将用户id和领导id插入领带表中
				   SysDepartLeader sysDepartLeader=new SysDepartLeader();
				   sysDepartLeader.setDep_id(sysDepartment.getId());
				   sysDepartLeader.setUser_id(sysUser2.getId());
				   sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
			   }
		   }

		   return ResultUtil.success();
	   }
	   
	   
	   
	   //通过人员账号查找用户
	   @RequestMapping("/selectUserByName")
	   public Result selectUserByName(String username)
	   {
//		String username="孙权";
		SysUser sysUser=sysUserService.selectUserByName(username);
		System.out.println(sysUser);
		
		
		
		
		   return ResultUtil.success(sysUser);
	   }
	   
	   
	   
	   
	   //通过人员名称查找用户
	   @RequestMapping("/selectUserByRealName")
	   public Result selectUserByRealName(String realname)
	   {
		   
		 
		SysUser sysUser=sysUserService.selectUserByRealName(realname);
		System.out.println(sysUser);
		   return ResultUtil.success(sysUser);
	   }
	   
	   
	   
	   
	   //删除人员
	   @PostMapping (value="/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)//传入用户id，部门id,职位信息
	   public Result deleteUser(@RequestBody List<SysUser> sysUsers)//传过来用户的id
	   {                    
		   
		  // List<SysUser> sysUsers=new ArrayList<SysUser>();
		   //删除人员只是将人员的状态变为否
//		 SysUser sysUser=new SysUser();
//		 sysUser.setId(33);
		   for(SysUser sysUser:sysUsers)
		   {
			   sysUser.setStauts(1);
			   sysUserService.updateUserState(sysUser);
//			   sysUsers.add(sysUser);
		   }
		   
		 
		 
		 for(SysUser sysUser3:sysUsers)
		 {
	//###查询一个判断是否有就行		 
			 
			//判断用户角色表是否存在，如果不存在则可以删除，否则不让删除
		   SysUserRole sysUserRole=sysUserService.selectUserRoleByUserIdReturnOne(sysUser3.getId());
		//List<SysUserRole> sysUserRoles=sysUserService.selectUserRoleByUserId(sysUser3.getId());
			 if(sysUserRole==null)
			 {
				 //将用户状态变为离职
				 sysUserService.updateUserState(sysUser3);
	             
			     //删除用户和部门的关系
				 sysUserService.updateUserDepId(sysUser3);


				 if(sysUser3.getUser_position()!=null)
				 {
					 //如果用户存在职位的话
					 if(sysUser3.getUser_position().equals("正职"))
					 {
						 //更新用户的职位为空
						 sysUserService.updateUserPosition(sysUser3.getId());
						 //删除部门正职或者副职的信息为空
						 //更新部门的正职属性为空
//						SysUser sysUser5=sysUserService.selectUserById(sysUser4.getId());
						 SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(sysUser3.getDep_id());

						 UserPosition userPosition=new UserPosition();
						 userPosition.setId(sysDepartment.getId());
						 userPosition.setName("");
						 sysDepartmentService.updatePrincipal(userPosition);


						 //删除领导表中的用户和职位的关系
						 SysDepartLeader sysDepartLeader=new SysDepartLeader();
						 sysDepartLeader.setDep_id(sysDepartment.getId());
						 sysDepartLeader.setUser_id(sysUser3.getId());
						 sysDepartmentService.deleteDepartmentLeader2(sysDepartLeader);

					 }

					 //删除人员为副职的情况要特殊处理
					 if(sysUser3.getUser_position().equals("副职"))
					 {
						 //更新用户的职位为空
						 sysUserService.updateUserPosition(sysUser3.getId());
						 //更新部门的正职属性，如果为一个，则直接置为空，存在多个，则将当前用户从里面减少
						 UserPosition userPosition=new UserPosition();
						 SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(sysUser3.getDep_id());
						 String[] NameStrings=sysDepartment.getDep_deputy().split(",");

						 List<String> namesList=new ArrayList<String>();

						 for(int i=0;i<NameStrings.length;i++)
						 {
							 if(!NameStrings[i].equals(String.valueOf(sysUser3.getId())))  //sysUser3.getReal_name()
							 {
								 namesList.add(NameStrings[i]);
							 }
						 }
						 if(namesList.isEmpty())
						 {
							 userPosition.setName("");
						 }
						 else {
							 userPosition.setName(namesList.get(0));
						 }

						 userPosition.setId(sysDepartment.getId());

						 sysDepartmentService.updateDeputy(userPosition);


						 //删除领导表中的用户和职位的关系
						 SysDepartLeader sysDepartLeader=new SysDepartLeader();
						 sysDepartLeader.setDep_id(sysDepartment.getId());
						 sysDepartLeader.setUser_id(sysUser3.getId());
						 sysDepartmentService.deleteDepartmentLeader2(sysDepartLeader);

					 }
				 }
				 								 			 			 			 			 
			 }		 
			 else 
			 {
				 return ResultUtil.error(-1, "存在用户和角色有关联，无法删除");
			 }			 
		 }		   
		   return ResultUtil.success();
	   }
	   
	   
	   
	   
	   
	   
	   //查询所有的用户
	   @RequestMapping("/selectUserNameId")
	  public Result selectUserNameId()
	  {
		 List<SysUser> sysUsers=sysUserService.selectAllUser();
		  
		   
		  return ResultUtil.success(sysUsers);
	  }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   @PostMapping(value="/selectUserByRealUsername")
	    public Result selectUserByRealUsername(@RequestBody SysUser name)
	    {
	  	  
	  	  SysUser sysUser2=sysUserService.selectUserByRealUsername(name);
	  	  System.out.println(sysUser2);
	  	  
	  	  return ResultUtil.success(sysUser2);
	    }

	   
	   
	   
	   
	   
	   @RequestMapping("/updateUser")
	    public Result updateUser(@RequestBody SysUser sysUser)//SysUser sysUser
	    {

	        //SysUser sysUser=new SysUser();
	        //sysUser.setId(38);
	        //sysUser.setUser_name("hhhh");
	        sysUserService.updateUser(sysUser);


	        return ResultUtil.success();
	    }
	   
	   
	   
	
}
