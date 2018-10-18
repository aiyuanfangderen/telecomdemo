package com.demo.controller;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.entity.SysDepartLeader;
import com.demo.entity.SysDepartment;
import com.demo.entity.SysUser;
import com.demo.service.SysDepartmentService;
import com.demo.service.SysMenuService;
import com.demo.service.SysModuleService;
import com.demo.service.SysRoleService;
import com.demo.service.SysUserService;
import com.demo.until.Result;
import com.demo.until.ResultUtil;
//##############查找部门正副职所能选择的所有用户这两个好像没有修改过来
//部门的controller
@RestController
@RequestMapping("/department")
public class DepartmentController 
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

	   
	   
		//增加部门
		@PostMapping(value = "/insertDepartment")       
		public Result insertDepartment(@RequestBody SysDepartment sysDepartment)//@RequestBody SysDepartment sysDepartment
		{

			//添加部门表的数据
			Date date=new Date();
			Timestamp t = new Timestamp(date.getTime());
			sysDepartment.setCreate_time(t);


			//插入部门前，检测该部门是否已经存在，如果存在就报错
			SysDepartment sysDepartment2=sysDepartmentService.selectDepartmentInformation(sysDepartment.getDep_name());
			if(sysDepartment2!=null)
			{
				return ResultUtil.error(-2, "该部门已经存在，无法插入");
			}

			sysDepartmentService.insertDepartment(sysDepartment);

			return ResultUtil.success();
		}
		
		
		
		
		
		   
		   //删除部门
		   @GetMapping(value="/deleteDepart/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
		   public Result deleteDepart(@PathVariable List<Integer> ids)
		   {	   	   
			       for(Integer id:ids)
			       {
				      //查找该部门的下一级部门表
				      List<SysDepartment> sysDepartments=sysDepartmentService.seleceLowerDepartment(id);
				     //如果下级部门为空
				      if(sysDepartments.isEmpty())
				     {
					   //再去查找该部门下是否还存在用户	    	  
					   SysUser sysUsers=sysUserService.selectUserByDepIdReturnOne(id);
					   if(sysUsers==null)
					   {
						     //删除部门表的数据
							 sysDepartmentService.deleteDepartment(id);						 
							
					   }
					   else 
					   {
						   
						   return ResultUtil.error(-2, "删除失败，部门下面还有用户");
					   } 					   
			        }			    	   
				   else
				   {
					
					 return ResultUtil.error(-2, "存在子菜单，删除失败");
				   }
			   }
			   			  			   
			   return ResultUtil.success();						   		  
		   }
   
		   
		 
		   
		   //查询所有部门的name和id,并且以树形结构返回
		   @RequestMapping("/selectDepartmentAllId")
		   public Result selectDepartmentAllId()
		   {
			   
				int parentId=0;
				JSONArray jsonArray=new JSONArray();
				jsonArray=this.getDepartmentsByparentId(parentId);
				return ResultUtil.success(jsonArray);			   			   			   
		   }
		   
		   
		 //将部门的变成树形结构							
			 // 取子部门
			private JSONArray getDepartmentByParentId(int parentId) throws JSONException 
			{
				JSONArray jsonArray=new JSONArray();
	            List<SysDepartment> sysDepartments=sysDepartmentService.selectDepartmentByDepId(parentId);
			
				for (SysDepartment a:sysDepartments){
					JSONObject jsObj=new JSONObject();
					String s=String.valueOf(a.getId());
					jsObj.put("id",s);
					jsObj.put("name",a.getDep_name());
					jsObj.put("display",a.getIs_display());							
					jsonArray.add(jsObj);
				}
				
				return jsonArray;
			}
		   			
			 // 取部门		
			private JSONArray getDepartmentsByparentId(int parentId) throws NumberFormatException, JSONException{
				JSONArray jsonArray=this.getDepartmentByParentId(parentId);
				for(int i=0;i<jsonArray.size();i++){
					JSONObject jsonObject=jsonArray.getJSONObject(i);
					if (Integer.parseInt(jsonObject.getString("display"))!=0){
						continue;
					}else{
						jsonObject.put("children",getDepartmentsByparentId(Integer.parseInt(jsonObject.getString("id"))));
					}
				}
				return jsonArray;
			}
			
			
			
			
			
			  
			 //通过部门的名称查询部门详细信息
			   @GetMapping(value="/selectDepartmentInformation/{name}", produces= MediaType.APPLICATION_JSON_VALUE)
			   public Result selectDepartmentInformation(@PathVariable String name)
			   {
				   
				   //通过部门的名称查找部门信息
				   SysDepartment sysDepartment=sysDepartmentService.selectDepartmentInformation(name);
				   
				  
				   return ResultUtil.success(sysDepartment);
			   }
			   
			   
			   
			   
		
			   //通过部门自定义id查找部门详细信息
			   @RequestMapping("/selectDepartmentInformationById")
			   public Result selectDepartmentInformationById(String departmentid)
			   {
				
				//通过部门自定义id查找部门信息
				SysDepartment sysDepartment=sysDepartmentService.selectDepartmentInformationById(departmentid);
				
				   	   
				   return ResultUtil.success(sysDepartment);
			   }
		   
			   
			   
			   
			   
			   //通过部门编号查找部门详细信息
			   @RequestMapping("/selectDepartmentInformationByNumber")
			   public Result selectDepartmentInformationByNumber(String department_id)//String department_id
			   {

				 //通过部门编号查找部门信息
				SysDepartment sysDepartment=sysDepartmentService.selectDepartmentInformationByNumber(department_id);								   				   
				   return ResultUtil.success(sysDepartment);
			   }
			   
			   
			   
			   
			   //查找部门正职能选择的所有用户
			   @GetMapping("/selectPrincipal/{id}")
			   public Result selectPrincipal(@PathVariable Integer id)
			   {
				
				   //通过用户部门去查找该部门下面所有能选择的用户
				   List<SysUser> sysUsers=sysUserService.selectUserByDepId(id);
				   
				 List<SysUser> sysUsers2=new ArrayList<SysUser>();
				 //点击部门正职后面按钮时，展示所有能选择的用户信息，如果有些用户已经是该部门的副职时就不能选择了，正职只能单选
				 for(SysUser sysUser:sysUsers)
				 {
					 if(sysUser.getUser_position()==null||sysUser.getUser_position().equals("正职"))
					 {
						 sysUsers2.add(sysUser);
					 }
				 }
				 
				 
				   return ResultUtil.success(sysUsers2);
			   }
			   
			   
			   
			 
			   //查询部门副职所能选择的副职用户
			   @GetMapping("/selectDeputy/{id}")
			   public Result selectDeputy(@PathVariable Integer id)
			   {

				   //通过用户部门去查找该部门下面所有能选择的用户
				 List<SysUser> sysUsers=sysUserService.selectUserByDepId(id);
				   
				 List<SysUser> sysUsers2=new ArrayList<SysUser>();
				 //点击部门副职后面按钮时，展示所有能选择的用户信息，如果有些用户已经是该部门的正职时就不能选择了，副职可以多选，中间用逗号隔开
				 for(SysUser sysUser:sysUsers)
				 {
					 if(sysUser.getUser_position()==null||sysUser.getUser_position().equals("副职"))
					 {
						 sysUsers2.add(sysUser);
					 }
				 }
				 System.out.println(sysUsers2);
				 
				   return ResultUtil.success(sysUsers2);
			   }
			   
			   
			   
			   //修改部门信息
			   @RequestMapping("/updateDepartment")
			    public Result updateDepartment(@RequestBody SysDepartment sysDepartment)//SysDepartment sysDepartment
			    {





			        //用部门id找到部门所有的信息
			        SysDepartment sysDepartment4=sysDepartmentService.selectDepartmentById(sysDepartment.getId());

			        Date date=new Date();
			        Timestamp t = new Timestamp(date.getTime());
			        sysDepartment.setCreate_time(t);

			        //修改前部门无正职的情况
			        if(sysDepartment4.getDep_principal()==null||sysDepartment4.getDep_principal().equals(""))
			        {
			            //测完       	//修改后部门有正职的情况
			            if(sysDepartment.getDep_principal()!=null)
			            {

			                //新增部门和用户关系数据到领导表(正职)
			                SysDepartLeader sysDepartLeader=new SysDepartLeader();
			                sysDepartLeader.setDep_id(sysDepartment.getId());
			                //将字符串变为int，用id去查找
			                int user_id=Integer.parseInt(sysDepartment.getDep_principal());
			                sysDepartLeader.setUser_id(user_id);
			                sysDepartmentService.insertDepartmentLeader(sysDepartLeader);


			                //在用户表去添加用户表的职位字段情况（正职）
			                SysUser sysUser2=sysUserService.selectUserById(user_id);
			                sysUser2.setUser_position("正职");
			                sysUserService.updateUserPosition2(sysUser2);


			            }
			        }

			        //修改前部门有正职的情况
			        else
			        {
			//测完        	//修改后部门无正职的情况
			            if(sysDepartment.getDep_principal()==null||sysDepartment4.getDep_principal().equals(""))
			            {
			                //获取修改前部门正职用户的信息
			                int user_id=Integer.parseInt(sysDepartment4.getDep_principal());
			                SysUser sysUser2=sysUserService.selectUserById(user_id);


			                //将原来的正职对应的用户和部门的关联在领导表删除
			                sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());
			                //将原来的正职的名字对应的用户里面职位字段设置为空
			                SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
			                sysUser5.setUser_position("");
			                sysUserService.updateUserPosition2(sysUser5);


			            }

			//测完        	//修改后部门有正职的情况
			            else
			            {
			                //查找原来部门正职的用户id
			                int user_id=Integer.parseInt(sysDepartment4.getDep_principal());
			                SysUser sysUser1=sysUserService.selectUserById(user_id);


			                //查找关联表的id
			                SysDepartLeader sysDepartLeader1=sysDepartmentService.selectDepartmentLeaderId(sysUser1.getId());


			                //将原来的正职对应的用户和部门的关联在领导表删除
			                sysDepartmentService.deleteDepartmentLeader3(sysUser1.getId());
			                //将原来的正职的名字对应的用户里面职位字段设置为空
			                SysUser sysUser5=sysUserService.selectUserById(sysUser1.getId());
			                sysUser5.setUser_position("");
			                sysUserService.updateUserPosition2(sysUser5);



			                //将修改后的部门正职用户和部门建立



			                //再将用户id和领导部门id插入领导表中建立关系(人员和部门为一对一关系)
			                SysDepartLeader sysDepartLeader3=new SysDepartLeader();
			                // sysDepartLeader3.setId(sysDepartLeader1.getId());

			                int user_id2=Integer.parseInt(sysDepartment.getDep_principal());
			                SysUser sysUser2=sysUserService.selectUserById(user_id2);

			                sysDepartLeader3.setUser_id(sysUser2.getId());
			                sysDepartLeader3.setDep_id(sysDepartment.getId());
			                sysDepartmentService.insertDepartmentLeader(sysDepartLeader3);
			                //  	   updateDepartmentLeader(sysDepartLeader3);


			                //增加修改正职联系用户的职位信息
			                SysUser sysUser4=sysUserService.selectUserById(user_id2);
			                sysUser4.setUser_position("正职");
			                sysUserService.updateUserPosition2(sysUser4);
			            }

			        }


			        //修改前部门无副职
			        if(sysDepartment4.getDep_deputy()==null||sysDepartment4.getDep_deputy().equals(""))
			        {
			            //测完       	//修改后部门有副职
			            if(sysDepartment.getDep_deputy()!=null)
			            {
			                //新增部门和用户关系数据到领导表(副职)



			                String[] strName1=sysDepartment.getDep_deputy().split(",");
			                for(String str:strName1)
			                {
			                    // 将字符串变为int，用id去查找
			                    int user_id2=Integer.parseInt(str);

			                    SysDepartLeader sysDepartLeader2=new SysDepartLeader();
			                    sysDepartLeader2.setDep_id(sysDepartment.getId());
			                    sysDepartLeader2.setUser_id(user_id2);
			                    sysDepartmentService.insertDepartmentLeader(sysDepartLeader2);

			                    //在用户表去添加用户表的职位字段情况（副职）
			                    SysUser sysUser3=sysUserService.selectUserById(user_id2);
			                    sysUser3.setUser_position("副职");
			                    sysUserService.updateUserPosition2(sysUser3);

			                }

			            }

			        }
			        //修改前部门有副职
			        else
			        {
			//测完        	//修改后部门无副职
			            if(sysDepartment.getDep_deputy()==null||sysDepartment4.getDep_deputy().equals(""))
			            {

			                //副职（可能存在多个人的情况）
			                //将获得的副职人名的字符串进行分割为多个人名
			                String[] strName1=sysDepartment4.getDep_deputy().split(",");
			                for(int i=0;i<strName1.length;i++)
			                {

			                    //将字符串变为int，用id去查找
			                    int user_id=Integer.parseInt(strName1[i]);
			                    SysUser sysUser2=sysUserService.selectUserById(user_id);
			                    //将原来的副职对应的用户和部门的关联在领导表删除
			                    sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());

			                    //将原来的副职的名字对应的用户里面职位字段设置为空
			                    SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
			                    sysUser5.setUser_position("");
			                    sysUserService.updateUserPosition2(sysUser5);

			                }


			            }

			            //如果修改后有副职的情况
			            else
			            {
			                //将修改前部门用户关系解除
			                //将获得的副职人名的字符串进行分割为多个人名
			                String[] strName1=sysDepartment4.getDep_deputy().split(",");
			                for(int i=0;i<strName1.length;i++)
			                {

			                    //将字符串变为int，用id去查找
			                    int user_id=Integer.parseInt(strName1[i]);
			                    SysUser sysUser2=sysUserService.selectUserById(user_id);
			                    //将原来的副职对应的用户和部门的关联在领导表删除
			                    sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());

			                    //将原来的副职的名字对应的用户里面职位字段设置为空
			                    SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
			                    sysUser5.setUser_position("");
			                    sysUserService.updateUserPosition2(sysUser5);

			                }

			                //添加修改后部门副职用户和部门相关联以及添加用户的职位信息
			                String[] strName2=sysDepartment.getDep_deputy().split(",");
			                for(String str:strName2)
			                {
			                    // 将字符串变为int，用id去查找
			                    int user_id2=Integer.parseInt(str);



			                    SysDepartLeader sysDepartLeader2=new SysDepartLeader();
			                    sysDepartLeader2.setDep_id(sysDepartment.getId());
			                    sysDepartLeader2.setUser_id(user_id2);
			                    sysDepartmentService.insertDepartmentLeader(sysDepartLeader2);


			                    //将原来的副职的名字对应的用户里面职位字段设置为空
			                    SysUser sysUser6=sysUserService.selectUserById(user_id2);
			                    sysUser6.setUser_position("副职");
			                    sysUserService.updateUserPosition2(sysUser6);

			                }
			            }
			        }

			        //将修改的更新到数据库
			        sysDepartmentService.updateDepartment(sysDepartment);
			        return ResultUtil.success();
			    }
			   
			   
			   

	
}
