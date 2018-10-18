package com.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.dto.UserDepartment;
import com.demo.dto.UserPosition;
import com.demo.entity.*;
import com.demo.service.*;
import com.demo.until.Result;
import com.demo.until.ResultUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.*;


@RestController
public class SysController
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





//	//增加部门
	@PostMapping(value = "/insertDepartment")       //在数据库将一些字段设置为唯一,可以解决很多问题
	public Result insertDepartment(@RequestBody SysDepartment sysDepartment)//@RequestBody SysDepartment sysDepartment
	{
//		SysDepartment sysDepartment=new SysDepartment();
//		sysDepartment.setDep_name("某某某");


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







	//#########插入部门中。在接口的传入json时要加一个部门级别字段
 //1部门操作  
   //接口：SysDepartment对象
   //添加部门信息         //当正职和副职为空时传递什么过来（为空传递正职id为0，副职id集合为空）
//   @PostMapping(value = "/insertDepartment/{id1}",
//		   produces= MediaType.APPLICATION_JSON_VALUE)       //在数据库将一些字段设置为唯一,可以解决很多问题
//   public Result insertDepartment(@RequestBody SysDepartment sysDepartment, @PathVariable int id1, @RequestBody List<Integer> idsList)
//   {
//
//	 //添加部门表的数据
//    Date date=new Date();
//	Timestamp t = new Timestamp(date.getTime());
//	sysDepartment.setCreate_time(t);
//
//
//	//插入部门前，检测该部门是否已经存在，如果存在就报错
//	SysDepartment sysDepartment2=sysDepartmentService.selectDepartmentInformation(sysDepartment.getDep_name());
//	if(sysDepartment2!=null)
//	 {
//		 return ResultUtil.error(-2, "该部门已经存在，无法插入");
//	 }
//
//	   sysDepartmentService.insertDepartment(sysDepartment);
//
//	   //用部门名称再查询出该部门
//	 SysDepartment sysDepartment3=sysDepartmentService.selectDepartmentInformation(sysDepartment.getDep_name());
//
//
//
//	   //添加分管领导的配置
//           //新增时，只有正部门
//	       if(id1!=0&&idsList.isEmpty())
//	       {
//	    	 //新增部门和用户关系数据到领导表(正职)
//       		SysDepartLeader sysDepartLeader=new SysDepartLeader();
//       		sysDepartLeader.setDep_id(sysDepartment3.getId());
//       		sysDepartLeader.setUser_id(id1);
//       	    sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
//
//       	  //在用户表去添加用户表的职位字段情况（正职）
//       		SysUser sysUser2=sysUserService.selectUserById(id1);
//	    	    sysUser2.setUser_position("正职");
//	    	   sysUserService.updateUserPosition2(sysUser2);
//
//
//	    //将部门id添加到用户的dep_id字段中
//	    	   //先查询出该用户
//	    	SysUser sysUser=sysUserService.selectUserById(id1);
//	    	   //再将部门id设置进去
//	    	   sysUser.setDep_id(sysDepartment3.getId());
//
//	    	   //保存到数据库
//	    	   sysUserService.updateUserDepIdById(sysUser);
//	       }
//	       //新增时只有副职
//	     if(id1==0&&(!idsList.isEmpty()))
//	     {
//
//	    	  //新增部门和用户关系数据到领导表(副职)
//       	   for(Integer id2:idsList)
//       	   {
//       		   SysDepartLeader sysDepartLeader2=new SysDepartLeader();
//       		   sysDepartLeader2.setDep_id(sysDepartment3.getId());
//       		   sysDepartLeader2.setUser_id(id2);
//       		   sysDepartmentService.insertDepartmentLeader(sysDepartLeader2);
//       	   }
//
//
//	    	    for(Integer id2:idsList)
//	    	    {
//	    	    	 //在用户表去添加用户表的职位字段情况（副职）
//	    	    	SysUser sysUser3=sysUserService.selectUserById(id2);
//	    	    	sysUser3.setUser_position("副职");
//	    	    	sysUserService.updateUserPosition2(sysUser3);
//
//	    	    	 //将部门id添加到用户的dep_id字段中
//	 	    	   //先查询出该用户
//	 	    	SysUser sysUser=sysUserService.selectUserById(id2);
//	 	    	   //再将部门id设置进去
//	 	    	   sysUser.setDep_id(sysDepartment3.getId());
//
//	 	    	   //保存到数据库
//	 	    	   sysUserService.updateUserDepIdById(sysUser);
//
//
//	    	    }
//
//
//
//
//
//	     }
//
//    	   //新增时正副职都有
//	     if(id1!=0&&(!idsList.isEmpty()))
//	     {
//	   	  //新增部门和用户关系数据到领导表(正职)
//     		SysDepartLeader sysDepartLeader=new SysDepartLeader();
//     		sysDepartLeader.setDep_id(sysDepartment3.getId());
//     		sysDepartLeader.setUser_id(id1);
//     	    sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
//
//          //新增部门和用户关系数据到领导表(副职)
//     	   for(Integer id2:idsList)
//     	   {
//     		   SysDepartLeader sysDepartLeader2=new SysDepartLeader();
//     		   sysDepartLeader2.setDep_id(sysDepartment3.getId());
//     		   sysDepartLeader2.setUser_id(id2);
//     		   sysDepartmentService.insertDepartmentLeader(sysDepartLeader2);
//
//
//
//     	   }
//
//
//
//
//     	  //在用户表去添加用户表的职位字段情况（正职）
//     		SysUser sysUser2=sysUserService.selectUserById(id1);
//	    	    sysUser2.setUser_position("正职");
//	    	   sysUserService.updateUserPosition2(sysUser2);
//
//	    	   //将部门id添加到用户的dep_id字段中
//	    	   //先查询出该用户
//	    	SysUser sysUser=sysUserService.selectUserById(id1);
//	    	   //再将部门id设置进去
//	    	   sysUser.setDep_id(sysDepartment3.getId());
//
//	    	   //保存到数据库
//	    	   sysUserService.updateUserDepIdById(sysUser);
//
//
//
//
//
//	    	 //在用户表去添加用户表的职位字段情况（副职）
//	    	    for(Integer id2:idsList)
//	    	    {
//	    	    	SysUser sysUser3=sysUserService.selectUserById(id2);
//	    	    	sysUser3.setUser_position("副职");
//	    	    	sysUserService.updateUserPosition2(sysUser3);
//
//	    	    	 //将部门id添加到用户的dep_id字段中
//		 	    	   //先查询出该用户
//		 	    	SysUser sysUser4=sysUserService.selectUserById(id2);
//		 	    	   //再将部门id设置进去
//		 	    	   sysUser4.setDep_id(sysDepartment3.getId());
//
//		 	    	   //保存到数据库
//		 	    	   sysUserService.updateUserDepIdById(sysUser4);
//
//
//
//
//	    	    }
//	     }
//	   System.out.println("保存成功");
//
//	   return ResultUtil.success();
//   }
//
   
 
   //查找插入时能选择的正职用户
//   @RequestMapping("/selectPrincipalDep")
//   public Result selectPrincipalDep()
//   {
//
//	   //通过用户部门去查找该部门下面所有能选择的用户(用户的部门信息未空的用户)
//	   List<SysUser> sysUsers=sysUserService.selectUserOnDepidNull();
//
//	 List<SysUser> sysUsers2=new ArrayList<SysUser>();
//	 //点击部门正职后面按钮时，展示所有能选择的用户信息，如果有些用户已经是该部门的副职时就不能选择了，正职只能单选
//	 for(SysUser sysUser:sysUsers)
//	 {
//		 if(sysUser.getStauts()==0&&sysUser.getDep_id()==0)//用户状态为存在并且该用户不属于任何部门
//		 {
//			 sysUsers2.add(sysUser);
//		 }
//	 }
//	 System.out.println(sysUsers2);
//
//	   return ResultUtil.success(sysUsers2);
//   }
   
 
   //查找插入时能选择的副职用户
//   @RequestMapping("/selectDeputyDep")
//   public Result selectDeputyDep()
//   {
//
//	   //通过用户部门去查找该部门下面所有能选择的用户
//	 List<SysUser> sysUsers=sysUserService.selectUserOnDepidNull();
//
//	 List<SysUser> sysUsers2=new ArrayList<SysUser>();
//	 //点击部门副职后面按钮时，展示所有能选择的用户信息，如果有些用户已经是该部门的正职时就不能选择了，副职可以多选，中间用逗号隔开
//	 for(SysUser sysUser:sysUsers)
//	 {
//		 if(sysUser.getStauts()==0&&sysUser.getDep_id()==0)//用户状态为存在并且该用户不属于任何部门
//		 {
//			 sysUsers2.add(sysUser);
//		 }
//	 }
//	 System.out.println(sysUsers2);
//
//	   return ResultUtil.success(sysUsers2);
//   }
//
   
   
   
   
   
   
   
   
   
   //接口：部门id的list集合
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
				   System.out.println("删除失败，部门下面还有用户");
				   return ResultUtil.error(-2, "删除失败，部门下面还有用户");
			   } 
			   
	        }
	    	   
		   else
		   {
			 System.out.println("存在子菜单，删除失败");
			 return ResultUtil.error(-2, "存在子菜单，删除失败");
		   }
	   }
	   
	  
	   

	   return ResultUtil.success();
	
	   
	  
   }
   
   
   
    //部门查询

//   //查询顶级部门
//   @RequestMapping("/selectDepartmentZero")
//   public Result selectDepartmentZero()
//   {
//	   //返回的部门所有信息
//	 List<SysDepartment> departments=sysDepartmentService.selectDepartmentZero();
//	   System.out.println(departments);
//	   return ResultUtil.success(departments);
//   }
   
//########将部门全部搜索出来，并且进行了排序
   //接口：不用参数
   //查询所有部门的信息
   @RequestMapping("/selectDepartmentAllId")
   public Result selectDepartmentAllId()
   {
	   
		int parentId=0;
		JSONArray jsonArray=new JSONArray();
		jsonArray=this.getDepartmentsByparentId(parentId);

		return ResultUtil.success(jsonArray);
	   
	   
	   
//	   //查询所有顶级部门表的数据
//	   List<SysDepartment> departments=sysDepartmentService.selectDepartmentZero();
//	   
//	 
//	  List<List<SysDepartment>> sysDepartments2=new ArrayList<List<SysDepartment>>();
//	  
//	  
//	  for(SysDepartment department:departments)
//	  {
//		  //要传一种树状结构给前台	 
//		   //查询出来每个顶级菜单下面的菜单，将它们放到一个list集合中，将list集合中的对象根据部门级别从小到大
//		   //排序，再将排序后的结果再放到另一个list集合中，每个顶级菜单都是如此操作。知道装到一个大的list集合中
//		  
//		  
//		  
//		  //list集合
//		 List<SysDepartment> sysDepartments=new ArrayList<SysDepartment>();
//		 //得到顶级部门id
//		 int id=department.getId();
//		 //该函数返回该id的顶级部门下面的所有部门表，最终将所有的非顶级部门加入到sysDepartments集合中
//		 sysDepartments=fun4(id,sysDepartments);
//		 sysDepartments.add(department);
//		 Collections.sort(sysDepartments);//对对象进行排序，需要这个对象实现Comparable接口。还有一种是在外面继承接口
//		 sysDepartments2.add(sysDepartments);
//		
//	  }
//	 
//		   	  
//	 System.out.println(sysDepartments2);
//	 
//	
//	 
//	   return ResultUtil.success(sysDepartments2);
   }
   
   
   //递归函数,通过顶级部门id获取该id下的所有部门
   public List<SysDepartment> fun4(int id, List<SysDepartment> sysDepartments)
   {
	  List<SysDepartment> sysDepartments2=sysDepartmentService.seleceLowerDepartment(id);
	  sysDepartments.addAll(sysDepartments2);
	  if(sysDepartments2!=null)
	   {
		   for(SysDepartment sysDepartment2:sysDepartments2)
		   {
			  int id1=sysDepartment2.getId();
			  fun4(id1, sysDepartments);
		   }
	   }
	  
	 return sysDepartments;   
	  
   }
   
   
   
   
   //接口：部门的名称name
 //查询部门所有的信息，通过部门的名称查询
   @GetMapping(value="/selectDepartmentInformation/{name}", produces= MediaType.APPLICATION_JSON_VALUE)
   public Result selectDepartmentInformation(@PathVariable String name)
   {
	   
	   //通过部门的名称查找部门信息
	   SysDepartment sysDepartment=sysDepartmentService.selectDepartmentInformation(name);
	   System.out.println(sysDepartment);
	  
	   return ResultUtil.success(sysDepartment);
   }
   
   
   //接口：部门自定义名称
   //通过部门自定义id查找部门信息
   @RequestMapping("/selectDepartmentInformationById")
   public Result selectDepartmentInformationById(String departmentid)
   {
	
	//通过部门自定义id查找部门信息
	SysDepartment sysDepartment=sysDepartmentService.selectDepartmentInformationById(departmentid);
	System.out.println(sysDepartment);
	   	   
	   return ResultUtil.success(sysDepartment);
   }
   
   
   //接口：部门编号department_id
   //通过部门编号查找部门信息
   @RequestMapping("/selectDepartmentInformationByNumber")
   public Result selectDepartmentInformationByNumber(String department_id)//String department_id
   {

	 //通过部门编号查找部门信息
	SysDepartment sysDepartment=sysDepartmentService.selectDepartmentInformationByNumber(department_id);
	System.out.println(sysDepartment);
	   

	   
	   return ResultUtil.success(sysDepartment);
   }
   
   
   
   //接口：文件的路径
   //批量导入部门
   @RequestMapping("/MangImportDepartment")
   public Result MangImportDepartment(@RequestParam("items_pic") MultipartFile items_pic)
   {
	   
//	   String filePath="E:\\Department.xlsx";
	   String filePath=items_pic.getOriginalFilename();
	   SysDepartment sysDepartment=new SysDepartment();
	 //  SysUserRole sysUserRole=new SysUserRole();
	   if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx")){
           System.out.println("文件不是excel类型");
       }
	   FileInputStream fis =null;
       Workbook wookbook = null;
       int lineNum = 0;
       Sheet sheet = null;
       try{
           //获取一个绝对地址的流
           fis = new FileInputStream(filePath);
           //2003版本的excel，用.xls结尾, 2007版本以.xlsx
           if(filePath.endsWith(".xls")){
               wookbook = new HSSFWorkbook(fis);//得到工作簿
           }else{
               wookbook = new XSSFWorkbook(fis);//得到工作簿
           }
           //得到一个工作表
           sheet = wookbook.getSheetAt(0);
           //获得表头
           Row rowHead = sheet.getRow(0);
           //列数
           int rows = rowHead.getPhysicalNumberOfCells();
           //行数
           lineNum = sheet.getLastRowNum();
           if(0 == lineNum){
               System.out.println("Excel内没有数据！");
           }
           getData1(sheet, lineNum, rows, sysDepartment,sysDepartmentService);
       } catch (Exception e){
           e.printStackTrace();
//           throw new MyException(-2, "导入错误");
           return ResultUtil.error(-2, "导入错误");
       }
	   
	   
	   
	   
	   return ResultUtil.success();
   }
   
   
   public static  void  getData1(Sheet sheet, int lineNum, int rowNum, SysDepartment sysDepartment, SysDepartmentService sysDepartmentService)
   {
       //获得所有数据
       for(int i = 1; i <= lineNum; i++){
           //获得第i行对象
           Row row = sheet.getRow(i);
           List<String> list = new ArrayList<>();
           for(int j=0; j<rowNum; j++){
             
           Cell cell=row.getCell(j);
               cell.setCellType(CellType.STRING);
             String str =cell.getStringCellValue();
               
               list.add(str);
           }
          // sysUserRole.setRole_id(Integer.valueOf(list.get(0)));    
          // sysUserRole.setUser_id(Integer.valueOf(list.get(1)));          
          sysDepartment.setDep_number(Integer.valueOf(list.get(0)));
          sysDepartment.setDep_name(list.get(1));       // setName();
          sysDepartment.setDep_alias(list.get(2));//Alias();
          sysDepartment.setDep_english_name(list.get(3));//English_name();
          sysDepartment.setDep_english_alias(list.get(4));   //English_alias();
          sysDepartment.setDep_principal(list.get(5));    //Principal();
          sysDepartment.setDep_deputy(list.get(6));//Deputy();
          sysDepartment.setIs_display(Integer.valueOf(list.get(7)));
          sysDepartment.setParent_dep_id(Integer.valueOf(list.get(8)));
          sysDepartment.setDep_status(list.get(9));
          sysDepartment.setDep_transceiver(list.get(10));
          sysDepartment.setDep_custom_id(list.get(11));
          sysDepartment.setDep_unity_code(list.get(12));
          sysDepartment.setDep_description(list.get(13));
          sysDepartment.setIs_same_look(Integer.valueOf(list.get(14)));
          sysDepartment.setDep_area(list.get(15));
          sysDepartment.setDep_type(Integer.valueOf(list.get(14)));//部门等级
          
          
          
      	Date date=new Date();
		
		//参数为long型
		Timestamp t = new Timestamp(date.getTime());
		sysDepartment.setCreate_time(t);
          
        
  
          System.out.println(sysDepartment);
         sysDepartmentService.insertDepartment(sysDepartment);
 
  
          
       }
   }
   
   //这一部分先不需要，前台已经又这些数据了
   //接口：部门的id
   //修改部门信息第一部分，显示
//   @RequestMapping("/updateDepartment")
//   public Result updateDepartment()
//   {
//	   
//	   int departmentid=16;
//	 //查询部门数据表信息
//	   SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(departmentid);
//	   System.out.println(sysDepartment);
//	   	    
//	   return ResultUtil.success();
//   }
   
   //接口;部门id
   //点击部门正职时查询所有能选择得用户
   @GetMapping("/selectPrincipal/{id}")
   public Result selectPrincipal(@PathVariable Integer id)
   {
	//   =23;
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
	 System.out.println(sysUsers2);
	 
	   return ResultUtil.success(sysUsers2);
   }
   
   
   //接口:部门id
   //点击部门副职时查询所有能选择的用户
   @GetMapping("/selectDeputy/{id}")
   public Result selectDeputy(@PathVariable Integer id)
   {
//	   int id=23;
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




    @RequestMapping("/updateDepartment")
    public Result updateDepartment(@RequestBody SysDepartment sysDepartment)//SysDepartment sysDepartment
    {

//        SysDepartment sysDepartment=new SysDepartment();
//        sysDepartment.setId(25);
//        sysDepartment.setDep_deputy("38");
        // sysDepartment.setDep_deputy("");



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
   
//#######在接口中的传入json中增加一个部门等级字段   。
   
   //接口：SysDepartment对象，//##########在里面还要将部门的正副值从字符串转变为int型，从而获得传进来的正副职id
//修改部门信息第二部分。

//  修改部门信息第二部分 @RequestMapping(value="/updateDepartment2", produces= MediaType.APPLICATION_JSON_VALUE)
//   public Result updateDepartment2( Integer id,  Integer id1, @RequestParam(required = false, value = "ids[]") List<Integer> ids, SysDepartment sysDepartment)
//   {
//
//	   //获得部门领导表id,用于后面的修改
//	       //传过来的部门id
////	       int id=11;
//	       //用部门去查找领导正职，再查用户id,再查领导id
//	          //用部门id找到部门所有的信息
//	          SysDepartment sysDepartment4=sysDepartmentService.selectDepartmentById(id);
//	          //用部门里面的正副领导名称去查找用户id,原来部门的职位段没有数据的情况
////1	          //原来的正副职都为空
//	          if((sysDepartment4.getDep_principal()==null||sysDepartment4.getDep_principal().equals(""))&&
//	        	 (sysDepartment4.getDep_deputy()==null||sysDepartment4.getDep_deputy().equals("")))
//	          {
//
////	        		 //修改部门表的数据
////	        		SysDepartment sysDepartment=new SysDepartment();
////	        		sysDepartment.setId(id);
////	        		sysDepartment.setDep_number(1);
////	        		sysDepartment.setDep_name("管理部门1");
////	        	    sysDepartment.setDep_principal("1");//这里直接从传过来的用户信息中获取就行
////	        		sysDepartment.setDep_deputy("2");//这里直接从传过来的用户信息中获取就行，可能包含多个名字，用逗号隔开
//	        		Date date=new Date();
//	        		Timestamp t = new Timestamp(date.getTime());
//	        		sysDepartment.setCreate_time(t);
//
//	        		sysDepartmentService.updateDepartment(sysDepartment);
//
////1.1
//	        		//修改后的正副职位都在的情况
//	        		if((!sysDepartment.getDep_principal().equals(""))&&(!sysDepartment.getDep_deputy().equals("")))
//	        		{
//	        			 //传入正分管领导的id
////		        		int id1=19;//这里也是直接从传过来的用户信息获取就行
////		        		//这个ids是传过来的副职所有的id
////		        	  List<Integer> ids=new ArrayList<Integer>();
////		        	  ids.add(20);
////		        	  ids.add(21);
//
//		        	  //新增部门和用户关系数据到领导表(正职)
//		        		SysDepartLeader sysDepartLeader=new SysDepartLeader();
//		        		sysDepartLeader.setDep_id(id);
//		        		sysDepartLeader.setUser_id(id1);
//		        	    sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
//
//		             //新增部门和用户关系数据到领导表(副职)
//		        	   for(Integer id2:ids)
//		        	   {
//		        		   SysDepartLeader sysDepartLeader2=new SysDepartLeader();
//		        		   sysDepartLeader2.setDep_id(id);
//		        		   sysDepartLeader2.setUser_id(id2);
//		        		   sysDepartmentService.insertDepartmentLeader(sysDepartLeader2);
//		        	   }
//
//
//
//
//		        	  //在用户表去添加用户表的职位字段情况（正职）
//		        		SysUser sysUser2=sysUserService.selectUserById(id1);
//		 	    	    sysUser2.setUser_position("正职");
//		 	    	   sysUserService.updateUserPosition2(sysUser2);
//		 	    	 //在用户表去添加用户表的职位字段情况（副职）
//
//							for(Integer id2:ids)
//							{
//								SysUser sysUser3=sysUserService.selectUserById(id2);
//								sysUser3.setUser_position("副职");
//								sysUserService.updateUserPosition2(sysUser3);
//							}
//
////		 	    	    for(Integer id2:ids)
////		 	    	    {
////		 	    	    	SysUser sysUser3=sysUserService.selectUserById(id2);
////		 	    	    	sysUser3.setUser_position("副职");
////		 	    	    	sysUserService.updateUserPosition2(sysUser3);
////		 	    	    }
//	        		}
//
//
//
//
//
////1.2
//
//	 	    	        //如果修改后的正职为空，副职不为空的情况
//	 	    	         if(sysDepartment.getDep_principal().equals("")&&(!sysDepartment.getDep_deputy().equals("")))
//	 	    	         {
//	 	    	        	 //正职为空则直接去修改去删除领带表原来用户和部门的关系
//	 	    	        	 //去用户表删除用户的职位信息（这个里面省略这两个步骤是因为，原来的正副职都为空）
//
//
//	 		        		//这个ids是传过来的副职所有的id
////	 		        	  List<Integer> ids=new ArrayList<Integer>();
////	 		        	  ids.add(20);
////	 		        	  ids.add(21);
//
//
//	 		             //新增部门和用户关系数据到领导表(副职)
//	 		        	   for(Integer id2:ids)
//	 		        	   {
//	 		        		   SysDepartLeader sysDepartLeader2=new SysDepartLeader();
//	 		        		   sysDepartLeader2.setDep_id(id);
//	 		        		   sysDepartLeader2.setUser_id(id2);
//	 		        		   sysDepartmentService.insertDepartmentLeader(sysDepartLeader2);
//	 		        	   }
//
//	 		 	    	 //在用户表去添加用户表的职位字段情况（副职）
//	 		 	    	    for(Integer id2:ids)
//	 		 	    	    {
//	 		 	    	    	SysUser sysUser3=sysUserService.selectUserById(id2);
//	 		 	    	    	sysUser3.setUser_position("副职");
//	 		 	    	    	sysUserService.updateUserPosition2(sysUser3);
//	 		 	    	    }
//
//	 	    	         }
//
//
//
//
////1.3
//
//	 	    	        //如果修改后的副职为空,正职不为空的情况
//	 	    	        if(!sysDepartment.getDep_principal().equals("")&&(sysDepartment.getDep_deputy().equals("")))
//	 	    	        {
//
//	 	    	        	 //正职为空则直接去修改去删除领带表原来用户和部门的关系
//	 	    	        	 //去用户表删除用户的职位信息（这个里面省略这两个步骤是因为，原来的正副职都为空）
//
//	 	    	        	 //传入正分管领导的id
////			        		int id1=19;//这里也是直接从传过来的用户信息获取就行
//
//			        	  //新增部门和用户关系数据到领导表(正职)
//			        		SysDepartLeader sysDepartLeader=new SysDepartLeader();
//			        		sysDepartLeader.setDep_id(id);
//			        		sysDepartLeader.setUser_id(id1);
//			        	    sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
//
//			        	  //在用户表去添加用户表的职位字段情况（正职）
//			        		SysUser sysUser2=sysUserService.selectUserById(id1);
//			 	    	    sysUser2.setUser_position("正职");
//			 	    	   sysUserService.updateUserPosition2(sysUser2);
//
//	 	    	        }
//
////1.4
//	 	    	        //如果修改后的正副职都为空的情况（不处理，因为原来和现在都为空）
//
//
//	 	    	   System.out.println("保存成功");
//	          }
//
//
//
//
//
//
//
//
//
//
//
////2
//
//	          //原来正职为空的情况，但是原来副职部位不为空的情况下
//	          if((sysDepartment4.getDep_principal()==null||sysDepartment4.getDep_principal().equals(""))&&
//	        	(sysDepartment4.getDep_deputy()!=null&&(!sysDepartment4.getDep_deputy().equals(""))))
//	          {
//
//	        	  //修改部门表的数据
////	        		SysDepartment sysDepartment=new SysDepartment();
////	        		sysDepartment.setId(id);
////	        		sysDepartment.setDep_number(1);
////	        		sysDepartment.setDep_name("管理部门1");
////	        	    sysDepartment.setDep_principal("");//这里直接从传过来的用户信息中获取就行
////	        		sysDepartment.setDep_deputy("");//这里直接从传过来的用户信息中获取就行，可能包含多个名字，用逗号隔开
//	        		Date date=new Date();
//	        		Timestamp t = new Timestamp(date.getTime());
//	        		sysDepartment.setCreate_time(t);
//
//	        		sysDepartmentService.updateDepartment(sysDepartment);
//
////2.1
//	        		//修改后的正副职都不为空
//	        		if((!sysDepartment.getDep_principal().equals(""))&&(!sysDepartment.getDep_deputy().equals("")))
//	        		{
//	        			 //传入正分管领导的id
//	//	        		int id1=19;//这里也是直接从传过来的用户信息获取就行
//
//		        		 //新增部门和用户关系数据到领导表(正职)
//		        		SysDepartLeader sysDepartLeader=new SysDepartLeader();
//		        		sysDepartLeader.setDep_id(id);
//		        		sysDepartLeader.setUser_id(id1);
//		        	    sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
//
//		        	    //在用户表去添加用户表的职位字段情况（正职）
//		        		SysUser sysUser2=sysUserService.selectUserById(id1);
//		 	    	    sysUser2.setUser_position("正职");
//		 	    	   sysUserService.updateUserPosition2(sysUser2);
//
//
//		 	    	//副职（可能存在多个人的情况）
//	                   //将获得的副职人名的字符串进行分割为多个人名
//	                   String[] strName=sysDepartment4.getDep_deputy().split(",");
//	                   List<SysUser> sysUsers=new ArrayList<SysUser>();//用于装副职的人名信息
//	                  for(String str:strName)
//	                  {
////将字符串变为int，用id去查找
//	                      int user_id=Integer.parseInt(str);
//
//	                      SysUser sysUser6=sysUserService.selectUserById(user_id);
////	                	  SysUser sysUser6=sysUserService.selectUserByRealName(str);
//	                	  sysUsers.add(sysUser6);
//	                  }
//
//	                  //副职
//	   		       List<SysDepartLeader> sysDepartLeaders=new ArrayList<SysDepartLeader>();
//
//	   		       for(SysUser sysUser6:sysUsers)
//	   		       {
//	   		    	   SysDepartLeader sysDepartLeader2=sysDepartmentService.selectDepartmentLeaderId(sysUser6.getId());
//	   		    	   sysDepartLeaders.add(sysDepartLeader2);
//	   		       }
//
//
//	   		  //传入副分管领导id
////	               List<Integer> ids=new ArrayList<Integer>();
////			       	       	    ids.add(20);
////			       	       	    ids.add(21);
//
//
//			       	 //用分管领导部门去查找领导部门的id
//			 		 int dep_id=sysDepartmentService.selectDepartIdByName(sysDepartment.getDep_name());
//
//			 		//副职
//			    	   int index=0;//用于用户id的增加，因为用户id和领导表的id顺序是对应的
//			    	   for(SysDepartLeader sysDepartLeader2:sysDepartLeaders)
//			    	   {
//
//			    		   SysDepartLeader sysDepartLeader4=new SysDepartLeader();//部门副职信息
//			    		   sysDepartLeader4.setId(sysDepartLeader2.getId());
//				    	   sysDepartLeader4.setUser_id(ids.get(index));
//				    	   sysDepartLeader4.setDep_id(dep_id);
//				    	   sysDepartmentService.updateDepartmentLeader(sysDepartLeader4);
//				    	   index++;
//			    	   }
//
//			    	   //修改用户表里面的职位信息
//			    	     //将原来的职位信息删除
//			    	         //副职
//			    	        for(SysUser sysUser6:sysUsers)
//			    	        {
//			    	        	sysUserService.updateUserPosition(sysUser6.getId());
//			    	        }
//
//			    	     //增加新联系用户的职位信息
//
//			    	 	    //副职
//			    	        for(Integer id3:ids)
//			    	        {
//			    	        	SysUser sysUser5=sysUserService.selectUserById(id3);
//			    	        	sysUser5.setUser_position("副职");
//			    	        	sysUserService.updateUserPosition2(sysUser5);
//			    	        }
//	        		}
//
//
////2.2
//	        		//修改后的正职为空，修改后的副职不为空
//	        		if(sysDepartment.getDep_principal().equals("")&&(!sysDepartment.getDep_deputy().equals("")))
//	        		{
//	        			//副职（可能存在多个人的情况）
//		                   //将获得的副职人名的字符串进行分割为多个人名
//		                   String[] strName=sysDepartment4.getDep_deputy().split(",");
//		                   List<SysUser> sysUsers=new ArrayList<SysUser>();//用于装副职的人名信息
//		                  for(String str:strName)
//		                  {
////将字符串变为int，用id去查找
//		                	  int user_id=Integer.parseInt(str);
//
//		                	  SysUser sysUser6=sysUserService.selectUserById(user_id);
////		                	  SysUser sysUser6=sysUserService.selectUserByRealName(str);
//		                	  sysUsers.add(sysUser6);
//		                  }
//
//		                  //副职
//		   		       List<SysDepartLeader> sysDepartLeaders=new ArrayList<SysDepartLeader>();
//
//		   		       for(SysUser sysUser6:sysUsers)
//		   		       {
//		   		    	   SysDepartLeader sysDepartLeader2=sysDepartmentService.selectDepartmentLeaderId(sysUser6.getId());
//		   		    	   sysDepartLeaders.add(sysDepartLeader2);
//		   		       }
//
//
//		   		  //传入副分管领导id
////		               List<Integer> ids=new ArrayList<Integer>();
////				       	       	    ids.add(20);
////				       	       	    ids.add(21);
//
//
//				       	 //用分管领导部门去查找领导部门的id
//				 		 int dep_id=sysDepartmentService.selectDepartIdByName(sysDepartment.getDep_name());
//
//				 		//副职
//				    	   int index=0;//用于用户id的增加，因为用户id和领导表的id顺序是对应的
//				    	   for(SysDepartLeader sysDepartLeader2:sysDepartLeaders)
//				    	   {
//
//				    		   SysDepartLeader sysDepartLeader4=new SysDepartLeader();//部门副职信息
//				    		   sysDepartLeader4.setId(sysDepartLeader2.getId());
//					    	   sysDepartLeader4.setUser_id(ids.get(index));
//					    	   sysDepartLeader4.setDep_id(dep_id);
//					    	   sysDepartmentService.updateDepartmentLeader(sysDepartLeader4);
//					    	   index++;
//				    	   }
//
//				    	   //修改用户表里面的职位信息
//				    	     //将原来的职位信息删除
//				    	         //副职
//				    	        for(SysUser sysUser6:sysUsers)
//				    	        {
//				    	        	sysUserService.updateUserPosition(sysUser6.getId());
//				    	        }
//
//				    	     //增加新联系用户的职位信息
//
//				    	 	    //副职
//				    	        for(Integer id3:ids)
//				    	        {
//				    	        	SysUser sysUser5=sysUserService.selectUserById(id3);
//				    	        	sysUser5.setUser_position("副职");
//				    	        	sysUserService.updateUserPosition2(sysUser5);
//				    	        }
//	        		}
//
////2.3
//	        		//修改后的副职为空，修改后的正职不为空
//	        		if(((!sysDepartment.getDep_principal().equals(""))&&sysDepartment.getDep_principal()!=null)&&
//	        				((sysDepartment.getDep_deputy().equals(""))||sysDepartment.getDep_deputy()==null))
//	        		{
//
//	        			//副职（可能存在多个人的情况）
//		                   //将获得的副职人名的字符串进行分割为多个人名
//		                   String[] strName=sysDepartment4.getDep_deputy().split(",");
//		                  for(int i=0;i<strName.length;i++)
//		                  {
////将字符串变为int，用id去查找
//		                	  int user_id=Integer.parseInt(strName[i]);
//
//		                	 SysUser sysUser2=sysUserService.selectUserById(user_id);
//
//
//
////		                	  SysUser sysUser2=sysUserService.selectUserByRealName(strName[i]);
//
//		                	  //将原来的副职的名字对应的用户里面职位字段设置为空
//		                	  SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
//			    	        	sysUser5.setUser_position("");
//			    	        	sysUserService.updateUserPosition2(sysUser5);
//			    	        	//将原来的副职对应的用户和部门的关联在领导表删除
//			                	  sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());
//		                  }
//
//
//
//
//
//	        			 //传入正分管领导的id
////		        		int id1=19;//这里也是直接从传过来的用户信息获取就行
//
//		        		 //新增部门和用户关系数据到领导表(正职)
//		        		SysDepartLeader sysDepartLeader=new SysDepartLeader();
//		        		sysDepartLeader.setDep_id(id);
//		        		sysDepartLeader.setUser_id(id1);
//		        	    sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
//
//		        	    //在用户表去添加用户表的职位字段情况（正职）
//		        		SysUser sysUser2=sysUserService.selectUserById(id1);
//		 	    	    sysUser2.setUser_position("正职");
//		 	    	   sysUserService.updateUserPosition2(sysUser2);
//
//	        		}
//
////2.4
//	        		//修改后正副职都为空
//	        		if(sysDepartment.getDep_principal().equals("")&&(sysDepartment.getDep_deputy().equals("")))
//	        		{
//	        			//副职（可能存在多个人的情况）
//		                   //将获得的副职人名的字符串进行分割为多个人名
//		                   String[] strName=sysDepartment4.getDep_deputy().split(",");
//		                  for(int i=0;i<strName.length;i++)
//		                  {
////将字符串变为int，用id去查找
//		                	  int user_id=Integer.parseInt(strName[i]);
//
//		                	 SysUser sysUser2=sysUserService.selectUserById(user_id);
//
////		                	  SysUser sysUser2=sysUserService.selectUserByRealName(strName[i]);
//		                	//将原来的副职对应的用户和部门的关联在领导表删除
//		                	  sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());
//		                	  //将原来的副职的名字对应的用户里面职位字段设置为空
//		                	  SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
//			    	        	sysUser5.setUser_position("");
//			    	        	sysUserService.updateUserPosition2(sysUser5);
//
//		                  }
//
//
//
//
//	        		}
//
//
//
//
//	          }
//
//
//
//
//
//
////3
//
//	          //原来副职为空,正职不为空
//	          if((sysDepartment4.getDep_deputy()==null||sysDepartment4.getDep_deputy().equals(""))&&
//	        	(sysDepartment4.getDep_principal()!=null)&&(!sysDepartment4.getDep_principal().equals("")))
//	          {
//	        	//修改部门表的数据
////	        		SysDepartment sysDepartment=new SysDepartment();
////	        		sysDepartment.setId(id);
////	        		sysDepartment.setDep_number(1);
////	        		sysDepartment.setDep_name("管理部门1");
////	        	    sysDepartment.setDep_principal("");//这里直接从传过来的用户信息中获取就行
////	        		sysDepartment.setDep_deputy("");//这里直接从传过来的用户信息中获取就行，可能包含多个名字，用逗号隔开
//	        		Date date=new Date();
//	        		Timestamp t = new Timestamp(date.getTime());
//	        		sysDepartment.setCreate_time(t);
//
//	        		sysDepartmentService.updateDepartment(sysDepartment);
////3.1
//	        		//修改后正副职都存在的情况
//	        		if((!sysDepartment.getDep_principal().equals(""))&&(!sysDepartment.getDep_deputy().equals("")))
//	        		{
//	        			//这个ids是传过来的副职所有的id
////			        	  List<Integer> ids=new ArrayList<Integer>();
////			        	  ids.add(20);
////			        	  ids.add(21);
//
//
//			        	 //新增部门和用户关系数据到领导表(副职)
//			        	   for(Integer id2:ids)
//			        	   {
//			        		   SysDepartLeader sysDepartLeader2=new SysDepartLeader();
//			        		   sysDepartLeader2.setDep_id(id);
//			        		   sysDepartLeader2.setUser_id(id2);
//			        		   sysDepartmentService.insertDepartmentLeader(sysDepartLeader2);
//			        	   }
//
//
//			        	 //在用户表去添加用户表的职位字段情况（副职）
//			 	    	    for(Integer id2:ids)
//			 	    	    {
//			 	    	    	SysUser sysUser3=sysUserService.selectUserById(id2);
//			 	    	    	sysUser3.setUser_position("副职");
//			 	    	    	sysUserService.updateUserPosition2(sysUser3);
//			 	    	    }
//
//
//
//
//
////将字符串变为int，用id去查找		 	   //正职
//			 	    	       int user_id=Integer.parseInt(sysDepartment4.getDep_principal());
//			 	    	      SysUser sysUser1=sysUserService.selectUserById(user_id);
////				               SysUser sysUser1=sysUserService.selectUserByRealName(sysDepartment4.getDep_principal());
//
//
//				               //得到领导表数据，部门表中部门id和用户id时一对多的关系
//				                  //正职
//					       SysDepartLeader sysDepartLeader1=sysDepartmentService.selectDepartmentLeaderId(sysUser1.getId());
//
//
//
////					       int id1=19;//传入正分管领导的id
//
//
//					       //用分管领导部门去查找领导部门的id
//				    	   int dep_id=sysDepartmentService.selectDepartIdByName(sysDepartment.getDep_name());
//
//
//				    	 //再将用户id和领导部门id插入领导表中建立关系(人员和部门为一对一关系)
//				    	   //正职
//				    	   SysDepartLeader sysDepartLeader3=new SysDepartLeader();//部门正职信息
//				    	   sysDepartLeader3.setId(sysDepartLeader1.getId());
//				    	   sysDepartLeader3.setUser_id(id1);
//				    	   sysDepartLeader3.setDep_id(dep_id);
//				    	   sysDepartmentService.updateDepartmentLeader(sysDepartLeader3);
//
//
//
//				    	   //修改用户表里面的职位信息
//				    	     //将原来的职位信息删除
//				    	         //正职
//				    	         sysUserService.updateUserPosition(sysUser1.getId());
//
//
//
//				    	     //增加新联系用户的职位信息
//				    	        //正职
//				    	        SysUser sysUser4=sysUserService.selectUserById(id1);
//				    	        sysUser4.setUser_position("正职");
//				    	        sysUserService.updateUserPosition2(sysUser4);
//	        		}
//
//
////3.2
//	        		//修改后正职存在，副职不存在时
//	        		if((!sysDepartment.getDep_principal().equals(""))&&(sysDepartment.getDep_deputy().equals("")))
//	        		{
//	        			//职处理正职就行了
//	        			 //正职
////将字符串变为int，用id去查找
//         			      int user_id=Integer.parseInt(sysDepartment4.getDep_principal());
//		 	    	      SysUser sysUser1=sysUserService.selectUserById(user_id);
////			               SysUser sysUser1=sysUserService.selectUserByRealName(sysDepartment4.getDep_principal());
//
//
//			               //得到领导表数据，部门表中部门id和用户id时一对多的关系
//			                  //正职
//				       SysDepartLeader sysDepartLeader1=sysDepartmentService.selectDepartmentLeaderId(sysUser1.getId());
//
//
//
////				       int id1=19;//传入正分管领导的id
//
//
//				       //用分管领导部门去查找领导部门的id
//			    	   int dep_id=sysDepartmentService.selectDepartIdByName(sysDepartment.getDep_name());
//
//
//			    	 //再将用户id和领导部门id插入领导表中建立关系(人员和部门为一对一关系)
//			    	   //正职
//			    	   SysDepartLeader sysDepartLeader3=new SysDepartLeader();//部门正职信息
//			    	   sysDepartLeader3.setId(sysDepartLeader1.getId());
//			    	   sysDepartLeader3.setUser_id(id1);
//			    	   sysDepartLeader3.setDep_id(dep_id);
//			    	   sysDepartmentService.updateDepartmentLeader(sysDepartLeader3);
//
//
//
//			    	   //修改用户表里面的职位信息
//			    	     //将原来的职位信息删除
//			    	         //正职
//			    	         sysUserService.updateUserPosition(sysUser1.getId());
//
//
//
//			    	     //增加新联系用户的职位信息
//			    	        //正职
//			    	        SysUser sysUser4=sysUserService.selectUserById(id1);
//			    	        sysUser4.setUser_position("正职");
//			    	        sysUserService.updateUserPosition2(sysUser4);
//	        		}
//
//
//
////3.3
//			    	//修改后正职不存在，副职存在
//	        		if(sysDepartment.getDep_principal().equals("")&&(!sysDepartment.getDep_deputy().equals("")))
//	        		{
////将字符串变为int，用id去查找
//	        			//处理正职
//	        			 int user_id=Integer.parseInt(sysDepartment4.getDep_principal());
//		 	    	      SysUser sysUser2=sysUserService.selectUserById(user_id);
////		                	  SysUser sysUser2=sysUserService.selectUserByRealName(sysDepartment4.getDep_principal());
//		                	//将原来的副职对应的用户和部门的关联在领导表删除
//		                	 sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());
//		                	  //将原来的副职的名字对应的用户里面职位字段设置为空
//		                	  SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
//			    	        sysUser5.setUser_position("");
//			    	        sysUserService.updateUserPosition2(sysUser5);
//
//
//
//			    	    //处理副职
//			    	      //这个ids是传过来的副职所有的id
////				        	  List<Integer> ids=new ArrayList<Integer>();
////				        	  ids.add(20);
////				        	  ids.add(21);
//
//
//				        	 //新增部门和用户关系数据到领导表(副职)
//				        	   for(Integer id2:ids)
//				        	   {
//				        		   SysDepartLeader sysDepartLeader2=new SysDepartLeader();
//				        		   sysDepartLeader2.setDep_id(id);
//				        		   sysDepartLeader2.setUser_id(id2);
//				        		   sysDepartmentService.insertDepartmentLeader(sysDepartLeader2);
//				        	   }
//
//
//				        	 //在用户表去添加用户表的职位字段情况（副职）
//				 	    	    for(Integer id2:ids)
//				 	    	    {
//				 	    	    	SysUser sysUser3=sysUserService.selectUserById(id2);
//				 	    	    	sysUser3.setUser_position("副职");
//				 	    	    	sysUserService.updateUserPosition2(sysUser3);
//				 	    	    }
//
//
//	        		}
//
//
//
////3.4
//	        		//正副都不存在的情况
//	        		if(sysDepartment.getDep_principal().equals("")&&(sysDepartment.getDep_deputy().equals("")))
//	        		{
//	        			//只处理正职
////将字符串变为int，用id去查找
//	        			 int user_id=Integer.parseInt(sysDepartment4.getDep_principal());
//		 	    	      SysUser sysUser2=sysUserService.selectUserById(user_id);
//
////	        			  SysUser sysUser2=sysUserService.selectUserByRealName(sysDepartment4.getDep_principal());
//		                	//将原来的副职对应的用户和部门的关联在领导表删除
//		                	 sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());
//		                	  //将原来的副职的名字对应的用户里面职位字段设置为空
//		                	  SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
//			    	        sysUser5.setUser_position("");
//			    	        sysUserService.updateUserPosition2(sysUser5);
//
//	        		}
//
//
//
//
//	          }
//
//
//
////4
//
//             //修改前正职和副职都在的情况
//             if((sysDepartment4.getDep_principal()!=null&&(!sysDepartment4.getDep_principal().equals("")))&&
//	        	 (sysDepartment4.getDep_deputy()!=null&&(!sysDepartment4.getDep_deputy().equals(""))))
//	          {
//            	 //正职
//	               SysUser sysUser1=sysUserService.selectUserByRealName(sysDepartment4.getDep_principal());
//	               //副职（可能存在多个人的情况）
//	                   //将获得的副职人名的字符串进行分割为多个人名
//	                   String[] strName=sysDepartment4.getDep_deputy().split(",");
//	                   List<SysUser> sysUsers=new ArrayList<SysUser>();//用于装副职的人名信息
//	                  for(String str:strName)
//	                  {
////将字符串变为int，用id去查找
//	                      int user_id=Integer.parseInt(str);
//
//	                      SysUser sysUser2=sysUserService.selectUserById(user_id);
////	                	  SysUser sysUser2=sysUserService.selectUserByRealName(str);
//	                	  sysUsers.add(sysUser2);
//	                  }
//
//		       //得到领导表数据，部门表中部门id和用户id时一对多的关系
//	                  //正职
//		       SysDepartLeader sysDepartLeader1=sysDepartmentService.selectDepartmentLeaderId(sysUser1.getId());
//		               //副职
//		       List<SysDepartLeader> sysDepartLeaders=new ArrayList<SysDepartLeader>();
//
//		       for(SysUser sysUser2:sysUsers)
//		       {
//		    	   SysDepartLeader sysDepartLeader2=sysDepartmentService.selectDepartmentLeaderId(sysUser2.getId());
//		    	   sysDepartLeaders.add(sysDepartLeader2);
//		       }
//
//
//
//		 //修改部门表的数据
////		SysDepartment sysDepartment=new SysDepartment();
////		sysDepartment.setId(id);
////		sysDepartment.setDep_number(1);
////		sysDepartment.setDep_name("管理部门1");
////	    sysDepartment.setDep_principal("");//考虑该属性为空是什么情况
////		sysDepartment.setDep_deputy("");
//			Date date=new Date();
//			Timestamp t = new Timestamp(date.getTime());
//			sysDepartment.setCreate_time(t);
//
//
//		   sysDepartmentService.updateDepartment(sysDepartment);
//
////4.1
//		   //修改后正副职都在
//		   if((!sysDepartment.getDep_principal().equals(""))&&(!sysDepartment.getDep_deputy().equals("")))
//		   {
//
//			   //添加分管领导的配置
//			      //传入正副分管领导的id(即用户id),分管领导部门名称
////			       int id1=19;//传入正分管领导的id
////			       int id2=1;
//			       //传入副分管领导id
////	               List<Integer> ids=new ArrayList<Integer>();
////			       	       	    ids.add(20);
////			       	       	    ids.add(21);
//
//
//
//
//
//			    	 //用分管领导部门去查找领导部门的id
//			    	   int dep_id=sysDepartmentService.selectDepartIdByName(sysDepartment.getDep_name());
//
//
//			    	 //再将用户id和领导部门id插入领导表中建立关系(人员和部门为一对一关系)
//			    	   //正职
//			    	   SysDepartLeader sysDepartLeader3=new SysDepartLeader();//部门正职信息
//			    	   sysDepartLeader3.setId(sysDepartLeader1.getId());
//			    	   sysDepartLeader3.setUser_id(id1);
//			    	   sysDepartLeader3.setDep_id(dep_id);
//			    	   sysDepartmentService.updateDepartmentLeader(sysDepartLeader3);
//
//
//			    	   //副职
//			    	   int index=0;//用于用户id的增加，因为用户id和领导表的id顺序是对应的
//			    	   for(SysDepartLeader sysDepartLeader2:sysDepartLeaders)
//			    	   {
//
//			    		   SysDepartLeader sysDepartLeader4=new SysDepartLeader();//部门副职信息
//			    		   sysDepartLeader4.setId(sysDepartLeader2.getId());
//				    	   sysDepartLeader4.setUser_id(ids.get(index));
//				    	   sysDepartLeader4.setDep_id(dep_id);
//				    	   sysDepartmentService.updateDepartmentLeader(sysDepartLeader4);
//				    	   index++;
//			    	   }
//
//
//
////			    	   //修改部门表里面的正职的名字,在更改部门时，这一部门已经完成了
////			    	        //查询新关联用户的id
////			    	 SysUser sysUser=sysUserService.selectUserById(id1);
////			    	       //再去部门表修改里面principal的内容
////			    	 UserPosition userPosition=new UserPosition();
////			    	 userPosition.setId(sysDepartment4.getId());
////			    	 userPosition.setName(sysUser.getReal_name());
////			    	 sysDepartmentService.updatePrincipal(userPosition);
//
//			    	 //修改用户表里面的职位信息
//			    	     //将原来的职位信息删除
//			    	         //正职
//			    	         sysUserService.updateUserPosition(sysUser1.getId());
//			    	         //副职
//			    	        for(SysUser sysUser2:sysUsers)
//			    	        {
//			    	        	sysUserService.updateUserPosition(sysUser2.getId());
//			    	        }
//
//
//			    	     //增加新联系用户的职位信息
//			    	        //正职
//			    	        SysUser sysUser4=sysUserService.selectUserById(id1);
//			    	        sysUser4.setUser_position("正职");
//			    	        sysUserService.updateUserPosition2(sysUser4);
//			    	 	    //副职
//			    	        for(Integer id3:ids)
//			    	        {
//			    	        	SysUser sysUser5=sysUserService.selectUserById(id3);
//			    	        	sysUser5.setUser_position("副职");
//			    	        	sysUserService.updateUserPosition2(sysUser5);
//			    	        }
//
//
//
//		   }
//
//
////4.2
//		 //修改后正职存在，副职不存在的情况
//		   if((!sysDepartment.getDep_principal().equals(""))&&(sysDepartment.getDep_deputy().equals("")))
//		   {
//
//
//
//			 //副职（可能存在多个人的情况）
//               //将获得的副职人名的字符串进行分割为多个人名
//               String[] strName1=sysDepartment4.getDep_deputy().split(",");
//              for(int i=0;i<strName1.length;i++)
//              {
//
// //将字符串变为int，用id去查找
//                  int user_id=Integer.parseInt(strName1[i]);
//
//                  SysUser sysUser2=sysUserService.selectUserById(user_id);
//
//
// //           	  SysUser sysUser2=sysUserService.selectUserByRealName(strName1[i]);
//            	//将原来的副职对应的用户和部门的关联在领导表删除
//            	  sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());
//            	  //将原来的副职的名字对应的用户里面职位字段设置为空
//            	  SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
//    	        	sysUser5.setUser_position("");
//    	        	sysUserService.updateUserPosition2(sysUser5);
//
//              }
//
//
//
//			   //传入正副分管领导的id(即用户id),分管领导部门名称
////		       int id1=19;//传入正分管领导的id
//		       //用分管领导部门去查找领导部门的id
//	    	   int dep_id=sysDepartmentService.selectDepartIdByName(sysDepartment.getDep_name());
//
//	    	 //再将用户id和领导部门id插入领导表中建立关系(人员和部门为一对一关系)
//	    	   //正职
//	    	   SysDepartLeader sysDepartLeader3=new SysDepartLeader();//部门正职信息
//	    	   sysDepartLeader3.setId(sysDepartLeader1.getId());
//	    	   sysDepartLeader3.setUser_id(id1);
//	    	   sysDepartLeader3.setDep_id(dep_id);
//	    	   sysDepartmentService.updateDepartmentLeader(sysDepartLeader3);
//
//	    	   //将原来的职位信息删除
//  	         //正职
//  	         sysUserService.updateUserPosition(sysUser1.getId());
//
//  	       //增加新联系用户的职位信息
// 	        //正职
// 	        SysUser sysUser4=sysUserService.selectUserById(id1);
// 	        sysUser4.setUser_position("正职");
// 	        sysUserService.updateUserPosition2(sysUser4);
//
//
//		   }
//
//
////4.3
//		   //修改后正职不存在，副职存在
//		   if(sysDepartment.getDep_principal().equals("")&&(!sysDepartment.getDep_deputy().equals("")))
//		   {
//			 //只处理正职
//// 将字符串变为int，用id去查找
//               int user_id=Integer.parseInt(sysDepartment4.getDep_principal());
//
//               SysUser sysUser2=sysUserService.selectUserById(user_id);
//
//
//
// //			  SysUser sysUser2=sysUserService.selectUserByRealName(sysDepartment4.getDep_principal());
//             	//将原来的副职对应的用户和部门的关联在领导表删除
//             	 sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());
//             	  //将原来的副职的名字对应的用户里面职位字段设置为空
//             	  SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
//	    	        sysUser5.setUser_position("");
//	    	        sysUserService.updateUserPosition2(sysUser5);
//
//
//
//
//	    	      //传入副分管领导id
////		               List<Integer> ids=new ArrayList<Integer>();
////				       	       	    ids.add(20);
////				       	       	    ids.add(21);
//
//				    	 //用分管领导部门去查找领导部门的id
//				    	   int dep_id=sysDepartmentService.selectDepartIdByName(sysDepartment.getDep_name());
//
//
//				    	 //再将用户id和领导部门id插入领导表中建立关系(人员和部门为一对一关系)
//
//				    	   //副职
//				    	   int index=0;//用于用户id的增加，因为用户id和领导表的id顺序是对应的
//				    	   for(SysDepartLeader sysDepartLeader2:sysDepartLeaders)
//				    	   {
//
//				    		   SysDepartLeader sysDepartLeader4=new SysDepartLeader();//部门副职信息
//				    		   sysDepartLeader4.setId(sysDepartLeader2.getId());
//					    	   sysDepartLeader4.setUser_id(ids.get(index));
//					    	   sysDepartLeader4.setDep_id(dep_id);
//					    	   sysDepartmentService.updateDepartmentLeader(sysDepartLeader4);
//					    	   index++;
//				    	   }
//
//				    	 //修改用户表里面的职位信息
//				    	     //将原来的职位信息删除
//				    	         //副职
//				    	        for(SysUser sysUser3:sysUsers)
//				    	        {
//				    	        	sysUserService.updateUserPosition(sysUser3.getId());
//				    	        }
//
//
//				    	     //增加新联系用户的职位信息
//				    	 	    //副职
//				    	        for(Integer id3:ids)
//				    	        {
//				    	        	SysUser sysUser6=sysUserService.selectUserById(id3);
//				    	        	sysUser6.setUser_position("副职");
//				    	        	sysUserService.updateUserPosition2(sysUser6);
//				    	        }
//
//		   }
//
////4.4
//		   //修改后正副职都不存在
//		   if(sysDepartment.getDep_principal().equals("")&&(sysDepartment.getDep_deputy().equals("")))
//		   {
//			   //只处理正职
//// 将字符串变为int，用id去查找
//               int user_id=Integer.parseInt(sysDepartment4.getDep_principal());
//
//               SysUser sysUser2=sysUserService.selectUserById(user_id);
//
//
////	 			  SysUser sysUser2=sysUserService.selectUserByRealName(sysDepartment4.getDep_principal());
//	             	//将原来的副职对应的用户和部门的关联在领导表删除
//	             	 sysDepartmentService.deleteDepartmentLeader3(sysUser2.getId());
//	             	  //将原来的副职的名字对应的用户里面职位字段设置为空
//	             	  SysUser sysUser5=sysUserService.selectUserById(sysUser2.getId());
//		    	        sysUser5.setUser_position("");
//		    	        sysUserService.updateUserPosition2(sysUser5);
//
//
//
//		    	      //副职（可能存在多个人的情况）
//		                //将获得的副职人名的字符串进行分割为多个人名
//		                String[] strName1=sysDepartment4.getDep_deputy().split(",");
//		               for(String str:strName1)
//		               {
//		            	// 将字符串变为int，用id去查找
//		                   int user_id2=Integer.parseInt(str);
//
//		                   SysUser sysUser3=sysUserService.selectUserById(user_id2);
//
////		             	  SysUser sysUser3=sysUserService.selectUserByRealName(str);
//		             	//将原来的副职对应的用户和部门的关联在领导表删除
//		             	  sysDepartmentService.deleteDepartmentLeader3(sysUser3.getId());
//		             	  //将原来的副职的名字对应的用户里面职位字段设置为空
//		             	  SysUser sysUser6=sysUserService.selectUserById(sysUser3.getId());
//		     	        	sysUser6.setUser_position("");
//		     	        	sysUserService.updateUserPosition2(sysUser6);
//
//		               }
//
//
//
//
//
//		   }
//
//
//
//
//
//		   System.out.println("保存成功");
//	          }
//
//
//
//
//
//
//
//
//	   return ResultUtil.success();
//   }
//
//
   
   
   
   
   
   
   
   
   
   
  
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
 //2人员操作  
   
   //接口：SysUser对象
   //插入用户
   //插入用户
   @PostMapping("/insertUser")
   public Result insertUser(@RequestBody SysUser sysUser)
   {

//	SysUser sysUser=new SysUser();
//	sysUser.setUser_password("123");
//	sysUser.setUser_position("副职");
//	sysUser.setUser_name("燕");
//	sysUser.setReal_name("燕青");
//	sysUser.setDep_id(12);
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
//		  SysUser sysUser2=sysUserService.selectUserByName(sysUser.getUser_name());

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
//			  SysUser sysUser2=sysUserService.selectUserByName(sysUser.getReal_name());
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
//
//	@PostMapping("/insertUser")
//   public Result insertUser(@RequestBody SysUser sysUser)
//   {
//
////	SysUser sysUser=new SysUser();
////	sysUser.setUser_password("123");
////	sysUser.setUser_position("副职");
////	sysUser.setUser_name("燕");
////	sysUser.setReal_name("燕青");
////	sysUser.setDep_id(12);
//		Date date=new Date();
//		//System.out.println(date.getTime());
//		//参数为long型
//		Timestamp t = new Timestamp(date.getTime());
//		//System.out.println(t);
//		sysUser.setCreate_time(t);
//	  // sysMenu.setUpdate_time(update_time);
//
//
//	  sysUserService.insertUser(sysUser);
//	   System.out.println("保存成功");
//
//	   //判断用户是否有职位，如果有职位则去职位表插入部门对应的领导，再去领导表插入用户id和部门id
//	   if(sysUser.getUser_position().equals("正职"))
//	   {
//		   //找到对应的部门
//		   //通过插入的用户的名字找到用户的id
//// 用id去查找
//
//           SysUser sysUser2=sysUserService.selectUserById(sysUser.getId());
////		  SysUser sysUser2=sysUserService.selectUserByName(sysUser.getUser_name());
//
//		  SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(sysUser.getDep_id());
//		  UserPosition userPosition=new UserPosition();
//		  userPosition.setId(sysDepartment.getId());
////将用户id变成字符串
//		  userPosition.setName(String.valueOf(sysUser.getId()));
//		   //通过部门id去将正职人姓名修改到（插入）部门表的正职字段
//		   sysDepartmentService.updatePrincipal(userPosition);
//
//
//		   //将用户id和领导id插入领带表中
//		   SysDepartLeader sysDepartLeader=new SysDepartLeader();
//		   sysDepartLeader.setDep_id(sysDepartment.getId());
//		   sysDepartLeader.setUser_id(sysUser2.getId());
//		   sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
//
//	   }
//
//
//	   if(sysUser.getUser_position().equals("副职"))
//	   {
//		   //找到对应的部门
//		   //通过插入的用户的名字找到用户的id
////用id去查找
//
//		     SysUser sysUser2=sysUserService.selectUserById(sysUser.getId());
////			  SysUser sysUser2=sysUserService.selectUserByName(sysUser.getReal_name());
//			  SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(sysUser2.getDep_id());
//			  UserPosition userPosition=new UserPosition();
//			  userPosition.setId(sysDepartment.getId());
//
//			  //判断该部门是否已经存在一个副职了
//			  if(sysDepartment.getDep_deputy()==null||sysDepartment.getDep_deputy().equals(""))
//			  {
////将用户id变成字符串
//				  userPosition.setName(String.valueOf(sysUser.getId()));
//			  }
//
//			  else
//			  {
////将用户id变成字符串
//				  userPosition.setName(sysDepartment.getDep_deputy()+","+String.valueOf(sysUser.getId()));
//			  }
//
//
//			   //通过部门id去将副职人姓名修改到（插入）部门表的副职字段
//			   sysDepartmentService.updateDeputy(userPosition);
//			   //将用户id和领导id插入领带表中
//			   SysDepartLeader sysDepartLeader=new SysDepartLeader();
//			   sysDepartLeader.setDep_id(sysDepartment.getId());
//			   sysDepartLeader.setUser_id(sysUser2.getId());
//			   sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
//
//
//
//
//	   }
//
//
//
//
//
//
//
//	   return ResultUtil.success();
//   }
   
   
   //接口：用户的账号名
   //人员账号查找
   @RequestMapping("/selectUserByName")
   public Result selectUserByName(String username)
   {
//	String username="孙权";
	SysUser sysUser=sysUserService.selectUserByName(username);
	System.out.println(sysUser);
	
	
	
	
	   return ResultUtil.success(sysUser);
   }
   
   
   
   //接口：人员的真实名
   //人员名称查找
   @RequestMapping("/selectUserByRealName")
   public Result selectUserByRealName(String realname)
   {
	   
	 
	SysUser sysUser=sysUserService.selectUserByRealName(realname);
	System.out.println(sysUser);
	   
	
	

	
	 //  return "成功";
	   return ResultUtil.success(sysUser);
   }
  

   //接口：SysUser的对象集合
   //删除人员
   @PostMapping (value="/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)//传入用户id，部门id,职位信息
   public Result deleteUser(@RequestBody List<SysUser> sysUsers)//传过来用户的id
   {                    
	   
	  // List<SysUser> sysUsers=new ArrayList<SysUser>();
	   //删除人员只是将人员的状态变为否
//	 SysUser sysUser=new SysUser();
//	 sysUser.setId(33);
	   for(SysUser sysUser:sysUsers)
	   {
		   sysUser.setStauts(1);
		   sysUserService.updateUserState(sysUser);
//		   sysUsers.add(sysUser);
	   }
	   
	 
//	 sysUser.setUser_position("正职");
//	 sysUser.setDep_id(26);
//	 sysUser.setReal_name("宋江");
//	 SysUser sysUser2=new SysUser();
//	 sysUser2.setId(2);
//	 sysUser2.setStauts(1);
//	 sysUser2.setUser_position("正职");
	 
	
//	 sysUsers.add(sysUser2);
	 
	 
	 
	 
	 
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
//					SysUser sysUser5=sysUserService.selectUserById(sysUser4.getId());
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
   
   
   //接口：文档的路径
   //批量人员的添加
   @RequestMapping("/MangImportUser")//对文档中的int类型要控制，不让输入英语
   public Result MangImportUser(@RequestParam("items_pic") MultipartFile items_pic)//
   {
	   
	//   String filePath="E:\\UserRole.xlsx";
	   String filePath=items_pic.getOriginalFilename();
	   SysUser sysUser=new SysUser();
	//   SysDepartment sysDepartment=new SysDepartment();
	 //  SysUserRole sysUserRole=new SysUserRole();
	   if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx")){
           System.out.println("文件不是excel类型");
       }
	   FileInputStream fis =null;
       Workbook wookbook = null;
       int lineNum = 0;
       Sheet sheet = null;
       try{
           //获取一个绝对地址的流
           fis = new FileInputStream(filePath);
           //2003版本的excel，用.xls结尾, 2007版本以.xlsx
           if(filePath.endsWith(".xls")){
               wookbook = new HSSFWorkbook(fis);//得到工作簿
           }else{
               wookbook = new XSSFWorkbook(fis);//得到工作簿
           }
           //得到一个工作表
           sheet = wookbook.getSheetAt(0);
           //获得表头
           Row rowHead = sheet.getRow(0);
           //列数
           int rows = rowHead.getPhysicalNumberOfCells();
           //行数
           lineNum = sheet.getLastRowNum();
           if(0 == lineNum){
               System.out.println("Excel内没有数据！");
           }
           getData2(sheet, lineNum, rows, sysUser,sysUserService,sysDepartmentService);
       } catch (Exception e){
           e.printStackTrace();
       }
	   
	   
	   
	   
	   return ResultUtil.success();
   }
   
   
   
   
   public static  void  getData2(Sheet sheet, int lineNum, int rowNum, SysUser sysUser, SysUserService sysUserService, SysDepartmentService sysDepartmentService)
   {
       //获得所有数据
       for(int i = 1; i <= lineNum; i++){
           //获得第i行对象
           Row row = sheet.getRow(i);
           List<String> list = new ArrayList<>();
           for(int j=0; j<rowNum; j++){
             
           Cell cell=row.getCell(j);
               if(cell!=null)
               {
            	   cell.setCellType(CellType.STRING);
                   String str =cell.getStringCellValue(); 
                   list.add(str);
               }
               else {
            	   String str="";
            	   list.add(str);
			     }
               
               
           }
                
           
           sysUser.setUser_name(list.get(0));
           sysUser.setUser_password(list.get(1));
           sysUser.setReal_name(list.get(2));
           sysUser.setUser_code(list.get(3));
           sysUser.setUser_level(Integer.valueOf(list.get(4)));    
           sysUser.setUser_position(list.get(5));
           if(!list.get(6).equals(""))
           {
        	  sysUser.setDep_id(Integer.valueOf(list.get(6)));
           }
          
           sysUser.setIs_company_leader(Integer.valueOf(list.get(7)));
           sysUser.setOrder_number(list.get(8));
           sysUser.setUser_email(list.get(9));
           sysUser.setStauts(Integer.valueOf(list.get(10)));
           
           

          
          
          
      	Date date=new Date();
		
		//参数为long型
		Timestamp t = new Timestamp(date.getTime());
		sysUser.setCreate_time(t);
          
       
          System.out.println(sysUser);
          sysUserService.insertUser(sysUser);
          
          
          
          
          //判断用户是否有职位，如果有职位则去职位表插入部门对应的领导，再去领导表插入用户id和部门id
   	   if(sysUser.getUser_position().equals("正职"))
   	   {
   		   //找到对应的部门
   		  SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(sysUser.getId());
   		  UserPosition userPosition=new UserPosition();
   		  userPosition.setId(sysDepartment.getId());
//将用户id变为字符串   		  
   		  userPosition.setName(String.valueOf(sysUser.getId()));    //sysUser.getReal_name()
   		   //通过部门id去将正职人姓名修改到（插入）部门表的正职字段
   		   sysDepartmentService.updatePrincipal(userPosition);
   		   
   		   //将用户id和领导id插入领带表中
   		   SysDepartLeader sysDepartLeader=new SysDepartLeader();
   		   sysDepartLeader.setDep_id(sysDepartment.getId());
   		   sysDepartLeader.setUser_id(sysUser.getId());
   		   sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
   		   
   	   }
   	   
   	   
   	   if(sysUser.getUser_position().equals("副职"))
   	   {
   		   //找到对应的部门
   			  SysDepartment sysDepartment=sysDepartmentService.selectDepartmentById(sysUser.getId());
   			  UserPosition userPosition=new UserPosition();  			    			  			  
   			  userPosition.setId(sysDepartment.getId());
   			  
   			  
   			//判断该部门是否已经存在一个副职了
 			  if(sysDepartment.getDep_deputy()==null||sysDepartment.getDep_deputy().equals(""))
 			  {
 //将用户id变为字符串				  
 				  userPosition.setName(String.valueOf(sysUser.getId())); //sysUser.getReal_name()
 			  }
 			  
 			  else
 			  {
 				  userPosition.setName(sysDepartment.getDep_deputy()+","+String.valueOf(sysUser.getId()));
 			  }
   			  
   			   //通过部门id去将副职人姓名修改到（插入）部门表的副职字段
   			   sysDepartmentService.updateDeputy(userPosition);
   			   //将用户id和领导id插入领带表中
   			   SysDepartLeader sysDepartLeader=new SysDepartLeader();
   			   sysDepartLeader.setDep_id(sysDepartment.getId());
   			   sysDepartLeader.setUser_id(sysUser.getId());
   			   sysDepartmentService.insertDepartmentLeader(sysDepartLeader);
   		   
   		   
   			
   		   
   	   }
          
          
                                                
          
       }
   }
   
   //查询所有的用户
   @RequestMapping("/selectUserNameId")
  public Result selectUserNameId()
  {
	 List<SysUser> sysUsers=sysUserService.selectAllUser();
	  
	   
	  return ResultUtil.success(sysUsers);
  }
   
   
   
   

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
 //3角色配置  
  //接口：SysRole对象，SysUser对象的集合
   //添加角色
   

   @PostMapping("/insertRole")
   public Result insertRole(@RequestBody SysRole sysRole)
   {
	   
	   //将角色信息和角色对应的多个用户一起传过来，保存在角色表和角色用户表
//	   SysRole sysRole=new SysRole();
//	   sysRole.setRole_code("s0001");     
//	   sysRole.setRole_name("业务组");
//	   sysRole.setRole_desc("业务组全体");
	  
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
   
   
   //接口：角色id
   //查找所有的在职且没有被当前角色关联的用户，供角色选择
   @RequestMapping(
		   value="/selectAllUser/{id}", 
		   method = RequestMethod.POST,
           produces = MediaType.APPLICATION_JSON_VALUE)
   public Result selectAllUser(@PathVariable int id)
   {
	   //传入角色id
//	   int id=1;
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
//	Set<SysUser> sysUsers3=new TreeSet<SysUser>(sysUsers2);	
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
   
   
   
   
   
   
   
   

//接口：无参数
//查找角色配置
 //角色信息查询,首先查找所有的角色id,和角色名称
   @RequestMapping("/selectRoleConfigure1")
   public Result selectRoleConfigure1()
   {
	 List<SysRole> sysRoles=sysRoleService.selectAllRole();
//	List<RoleConfigure> roleConfigures=sysRoleService.selectRoleConfigure1();
	 System.out.println(sysRoles);
	   
	   return ResultUtil.success(sysRoles);
   }
   
 
   //接口：角色id
 //角色信息查询，通过id查找角色的详细信息以及用户信息，部门信息
   @RequestMapping("/selectRoleConfigure2")
   public Result selectRoleConfigure2(int id)//int id
   {
//	 int id=1;
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
//	int i=0;
//	Map<String,Object> map=new HashMap<String, Object>();
//	map.put("Role",sysRole);
//	for(UserDepartment userDepartment:userDepartments)
//	{
//		i++;
//		String UserDepartment="UserDepartment"+Integer.toString(i);
//		map.put(UserDepartment, userDepartment);
//	}
	
	Map map=new HashMap();
	map.put("Role",sysRole);
	map.put("UserDepartment", userDepartments);
	
	
	 			   
	   return ResultUtil.success(map);
   }
   
   
  
         
                
   //接口：角色id,SysUser的id集合
 //修改角色信息
   @PostMapping("/updateRole")
   public Result updateRole(@RequestBody SysRole sysRole)
   {
//	   //角色id
//	   int id=3;
//	   //将角色信息和角色对应的多个用户一起传过来，保存在角色表和角色用户表
//	   SysRole sysRole=new SysRole();
//	   sysRole.setId(id);
//	   sysRole.setRole_code("s0002");
//	   sysRole.setRole_name("事务组");
//	   sysRole.setRole_desc("事务组全体");
	  
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
//	   int id=1;
//	   
//	   List<SysUser> sysUsers=new ArrayList<SysUser>();
//		  
//	   SysUser sysUser=new SysUser();
//	   sysUser.setId(21);
//	   sysUser.setReal_name("黄忠");
//	   sysUsers.add(sysUser);
	   
	  
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
   
   
   
   
   
   
   
   
   
   
  
   //接口：角色id
 //删除角色配置
   @RequestMapping(
           value = "/deleteRole/{id}", 
           method = RequestMethod.GET,
           produces = MediaType.APPLICATION_JSON_VALUE
           )
 public Result deleteRole(@PathVariable Integer id)
   {
//	   int id=2;
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
   
   
   
   
   
   
   
   
 
//4模块配置
  
   
   //接口：SysModule对象的集合
   //添加模块，应该将模块和角色关系以及模块菜单关系一起保存到数据库
   @PostMapping("/insertModule")
   public Result insertModule(@RequestBody SysModule sysModule)//SysModule sysModule
   {
//	  SysModule sysModule=new SysModule();
//	  sysModule.setModule_code("use_jh");
//	  sysModule.setModule_name("检查组菜单");
//	  sysModule.setDefault_query_scope("本人相关");
	 
	  
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
   
   
  //接口：模块id,角色id的集合
 //插入模块角色关联
 @RequestMapping(value="/insertModuleRole/{id}",
		 method = RequestMethod.POST,
         produces = MediaType.APPLICATION_JSON_VALUE)
 public Result insertModuleRole(@PathVariable int id, @RequestBody List<SysRole> sysRoles)//SysModuleRole sysModuleRole
 {
	
//	 //传入模块
//	 int id=1;
//	//传入角色的集合
//	 List<SysRole> sysRoles=new ArrayList<SysRole>();
//	 
//	 SysRole sysRole1=new SysRole();
//	 SysRole sysRole2=new SysRole();
//	 
//	 sysRole1.setId(1);
////	 sysRole2.setId(2);
//	 sysRoles.add(sysRole1);
//	 sysRoles.add(sysRole2);
	 for(SysRole sysRole:sysRoles)
	 {
		 SysModuleRole sysModuleRole=new SysModuleRole();
		 sysModuleRole.setModule_id(id);
		 sysModuleRole.setRole_id(sysRole.getId());
		 System.out.println(sysRole);
		 sysRoleService.insertModuleRole(sysModuleRole);
	 }
	 
	 
	 
//	 
//	SysModuleRole sysModuleRole=new SysModuleRole();
//	sysModuleRole.setRole_id(1);
//	sysModuleRole.setModule_id(1);
//	sysModuleRole.setQuery_scope("全省");
//	
//	
//	
//
//	     
//		Date date=new Date();
//		//System.out.println(date.getTime());
//		//参数为long型
//		Timestamp t = new Timestamp(date.getTime());
//		//System.out.println(t);
//		sysModuleRole.setCreate_time(t);
//	  // sysMenu.setUpdate_time(update_time);
//	   
//	   
//	 sysModuleService.insertModuleRole(sysModuleRole);
	   System.out.println("保存成功");
	   
	   
	   return ResultUtil.success();
 }
   
   
 
////接口：模块id
// //查找所有该模块还没关联的角色
// @RequestMapping(value="/selectAllRole/{id}",
//		 method= RequestMethod.POST,
//		 produces= MediaType.APPLICATION_JSON_VALUE)
// public Result selectAllRole(@PathVariable int id)
// {
//
//
//
////	 //已选按照属性结构输出
//	 //int authId=0;
////	 JSONArray jsonArray=new JSONArray();
////	 jsonArray=this.getAuthsByparentId2(authId,list);
//
//
//
//
//
//
//
////	 //向上递归查找菜单，因为有些菜单的上级菜单可能不在选择菜单，但是列表选择时要选择出来
////	 List<SysMenu> sysMenus3=new ArrayList<SysMenu>();
//////	 for(SysMenu sysMenu:sysMenus)
//////	 {
//////		 List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();
//////		 sysOtherMenus=fun5(sysMenu.getMenu_parent_id(), sysOtherMenus);
//////		 sysMenus3.addAll(sysOtherMenus);
//////	 }
//////
//////	 sysMenus3.addAll(sysMenus);
////
////	 //去除sysMenus3中重复的菜单
////	 List<SysMenu> sysMenus5=new ArrayList<SysMenu>();
////	 for(SysMenu sysMenus4:sysMenus3)
////	 {
////		 boolean flag=true;
////		 if(sysMenus5.isEmpty())
////		 {
////			 sysMenus5.add(sysMenus4);
////		 }
////		 else
////		 {
////			 for(SysMenu sysMenus6:sysMenus5)
////			 {
////				 if(sysMenus6.getId()==sysMenus4.getId())
////				 {
////					 flag=false;
////				 }
////			 }
////
////			 if(flag)
////			 {
////				 sysMenus5.add(sysMenus4);
////			 }
////
////		 }
////	 }
////
////	 JSONArray jsonArray2=new JSONArray();
////	 jsonArray2=this.getAuthsByparentId2(authId,sysMenus5);
//
//
//	 //传入模块id
////	   int id=1;
//	   //查找所有的角色
//	  List<SysRole> sysRoles=sysRoleService.selectAllRole();
//	   //查找出已经和该模块绑定的角色
//	  List<SysModuleRole> sysModuleRoles=sysRoleService.selectModuleRoleByModuleId(id);
//	 //找出没有和模块绑定的角色
//	  for(SysRole sysRole:sysRoles)
//	  {
//		  for(SysModuleRole sysModuleRole:sysModuleRoles)
//		  {
//			  if(sysRole.getId()==sysModuleRole.getRole_id())
//			  {
//				  sysRoles.remove(sysRole);
//			  }
//
//		  }
//	  }
//
//
//	  System.out.println(sysRoles);
//
//
//	   return ResultUtil.success();
// }


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

	//接口：模块id
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




//	//删除模块角色关联
//	@RequestMapping("/deleteModuleRole/{id}")
//	public Result deleteModuleRole(@PathVariable Integer id,@RequestBody List<Integer> ids) //int id,List<SysRole> sysRoles
//	{
//
////	   //传入模块
////		 int id=1;
////		//传入角色的集合
////		 List<SysRole> sysRoles=new ArrayList<SysRole>();
////
////		 SysRole sysRole1=new SysRole();
////		 SysRole sysRole2=new SysRole();
////
////		 sysRole1.setId(5);
////		 sysRole2.setId(6);
////		 sysRoles.add(sysRole1);
////		 sysRoles.add(sysRole2);
//
//		for(Integer id1:ids)
//		{
//			SysModuleRole sysModuleRole=new SysModuleRole();
//			sysModuleRole.setModule_id(id);
//			sysModuleRole.setRole_id(id1);
//
//			sysRoleService.deleteRoleModule(sysModuleRole);
//		}
//		System.out.println("删除成功");
//		return ResultUtil.success();
//	}
//





//	//接口：模块id,角色id的集合
//	//删除模块角色关联
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





	//接口：模块id,角色id的集合
   //修改模块角色关联
//   @RequestMapping(value = "/updateModuleRole/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
// public Result updateModuleRole(@PathVariable Integer id, @RequestBody List<SysRole> sysRoles)
// {
//	//传入模块id
////	   int id=2;
//	//通过模块id去删除模块对用的所有角色
//	   sysModuleService.deleteModuleRole(id);
//
//	//再将修改的模块对应角色加入模块角色表
//	 //传入角色的集合
////		 List<SysRole> sysRoles=new ArrayList<SysRole>();
////
////		 SysRole sysRole1=new SysRole();
//////		 SysRole sysRole2=new SysRole();
////
////		 sysRole1.setId(1);
//////		 sysRole2.setId(2);
////		 sysRoles.add(sysRole1);
//////		 sysRoles.add(sysRole2);
//		 for(SysRole sysRole:sysRoles)
//		 {
//			 SysModuleRole sysModuleRole=new SysModuleRole();
//			 sysModuleRole.setModule_id(id);
//			 sysModuleRole.setRole_id(sysRole.getId());
//
//			 sysRoleService.insertModuleRole(sysModuleRole);
//		 }
//
//
//  return ResultUtil.success();
// }
 
 
   //接口：模块id
   //删除模块
   @RequestMapping(value="/deleteModule/{id}",
		   method= RequestMethod.GET,
		   produces= MediaType.APPLICATION_JSON_VALUE)
   public Result deleteModule(@PathVariable Integer id)
   {
//	   int id=1;	   
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
   
   
     
      
   
   
   
   
   
   
   
   
   //接口：菜单id,模块id
   //插入菜单模块关联表,在模块中展示所有的菜单后，点击哪个菜单就将哪个菜单和模块关联
//   @PostMapping("/insertMenuModule")
//   public Result insertMenuModule(@RequestBody SysMenuModule sysMenuModule)//SysMenuModule sysMenuModule
//   {
//
//
//
//	   //传送进来一个菜单id和模块id
////	SysMenuModule sysMenuModule=new SysMenuModule();
////
////	sysMenuModule.setModule_id(1);
////	sysMenuModule.setMenu_id(4);
//
//
//	//判断这个菜单是否已经被关联了
//	SysMenuModule sysMenuModule5=sysMenuService.selectIsModuleMenu(sysMenuModule);
//	if(sysMenuModule5==null)
//	{
//		//将点击菜单插入关联表
//		sysMenuService.insertMenuModule(sysMenuModule);
//	}
//
//
//		Date date=new Date();
//		Timestamp t = new Timestamp(date.getTime());
//		sysMenuModule.setCreate_time(t);
//
//		//用传进来的菜单id去查找它的所有子菜单，将所有子菜单和这个模块id相关联，插入模块菜单关联表
//		 List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();
//
//		 int id=sysMenuModule.getMenu_id();
//		 sysOtherMenus=fun2(id,sysOtherMenus);
//
//	  if(!sysOtherMenus.isEmpty())
//	  {
//		  for(SysMenu sysMenu:sysOtherMenus)
//			{
//			    SysMenuModule sysMenuModule2=new SysMenuModule();
//				sysMenuModule2.setModule_id(sysMenuModule.getModule_id());
//				sysMenuModule2.setMenu_id(sysMenu.getId());
//				//判断这个菜单是否被关联
//				SysMenuModule sysMenuModule6=sysMenuService.selectIsModuleMenu(sysMenuModule2);
//				if(sysMenuModule6==null)
//				{
//					sysMenuService.insertMenuModule(sysMenuModule2);
//				}
//
//
//
//			}
//	  }
//
	  
	  
//	  //用传进来的菜单id去查找他的所有上级
//
//
//	  //用传进来的菜单id去查找它的上级菜单id属性
//	  SysMenu sysMenu=sysMenuService.selectParentMenu(sysMenuModule.getMenu_id());
//	  sysOtherMenus=fun5(sysMenu.getMenu_parent_id(), sysOtherMenus);
//
//	       //插入上级菜单没有和模块关联的菜单
//	       for(SysMenu sysMenu2:sysOtherMenus)
//	       {
//	    	 //判断该模块和id是否在模块菜单表已经关联
//
//	    	   SysMenuModule sysMenuModule3=new SysMenuModule();
//	    	   sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
//	    	   sysMenuModule3.setMenu_id(sysMenu2.getId());
//	    	   SysMenuModule sysMenuModule4=sysMenuService.selectIsModuleMenu(sysMenuModule3);
//	    	   //如果没有关联则插入
//	    	   if(sysMenuModule4==null)
//	    	   {
//	    		   sysMenuService.insertMenuModule(sysMenuModule3);
//	    	   }
//
//	    	   //如果已经关联则不用插入
//	       }
//
//
//
//
//
//
//
	
	  
	  
	  
	  
	  
		
		
//		//再用传进来的模块id,去查找它的上一级菜单属性
//		SysMenu sysMenu=sysMenuService.selectParentMenu(sysMenuModule.getMenu_id());
//		
//		
//		
//		
//		Boolean flag=false;
//		//如果上级菜单属性不为0，则查找与传入菜单同级的菜单，再判断同级其他菜单是否全部关联，如果全部关联，则将传入进来菜单的上一级菜单也与当前模块进行关联，即也
//		//将其模块id和菜单id相关联
//		if(sysMenu.getMenu_parent_id()!=0)
//		{
//			//查找同级id,包括点击的菜单id
//			List<SysMenu> sysMenus2=sysMenuService.selectSameMenu(sysMenu.getMenu_parent_id());
//			System.out.println(sysMenus2);
//			
//			for(SysMenu sysMenu2:sysMenus2)
//			{
//				SysMenuModule sysMenuModule3=new SysMenuModule();
//				sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
//				sysMenuModule3.setMenu_id(sysMenu2.getId());
//				SysMenuModule sysMenuModule4=sysMenuService.selectIsModuleMenu(sysMenuModule3);
//				if(sysMenuModule4==null)
//				{
//					flag=true;
//				}
//				
//			}
//			
//			
//			if(!flag)
//			{
//				//都在，则将父级添加到已选的菜单
//				SysMenu sysMenu2=sysMenuService.selectParentMenuId(sysMenu.getMenu_parent_id());
//				SysMenuModule sysMenuModule3=new SysMenuModule();
//				sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
//				sysMenuModule3.setMenu_id(sysMenu2.getId());
//				sysMenuService.insertMenuModule(sysMenuModule3);
//				
//			}
//			
//			
//		
//			
//		}
//		
//		
//	   
//		
//		
////	//  sysMenuService.insertMenuModule(sysMenuModule);
//	   System.out.println("保存成功");
//
//
//	   return ResultUtil.success();
//   }



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

//		  		    Boolean flag=false;
			//如果不是顶级菜单，再查找该菜单的同级菜单，如果该菜单的同级菜单的其他菜单都已经被删除了，则在删除该菜单时，将点击菜单的上一级菜单也删除
			if(sysMenu.getMenu_parent_id()!=0)
			{

				System.out.println(sysMenu.getMenu_parent_id());
				fun6(sysMenu.getMenu_parent_id(),sysMenuModule.getModule_id());
			}


		}


















//	  			//查找同级id,包括点击的菜单id
//	  			List<SysMenu> sysMenus2=sysMenuService.selectSameMenu(sysMenu.getMenu_parent_id());
//	  			System.out.println(sysMenus2);
//
//	  			for(SysMenu sysMenu2:sysMenus2)
//	  			{
//	  				SysMenuModule sysMenuModule3=new SysMenuModule();
//	  				sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
//	  				sysMenuModule3.setMenu_id(sysMenu2.getId());
//	  				SysMenuModule sysMenuModule4=sysMenuService.selectIsModuleMenu(sysMenuModule3);
//	  				if(sysMenuModule4==null)
//	  				{
//	  					flag=true;
//	  				}
//
//	  			}


//	  			if(!flag)
//	  			{
//	  				//同级菜单都在菜单模块表，所以将点击菜单的父级也从模块菜单表删除
//	  				SysMenu sysMenu2=sysMenuService.selectParentMenuId(sysMenu.getMenu_parent_id());
//	  				SysMenuModule sysMenuModule3=new SysMenuModule();
//	  				sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
//	  				sysMenuModule3.setMenu_id(sysMenu2.getId());
//	  				sysModuleService.deleteModuleMenu(sysMenuModule3);
//
//	  			}
//

		//如果是顶级菜单，不做处理
//	  		}





		return ResultUtil.success();
	}




//	//接口：模块id,菜单id
//   //删除模块和菜单对应表的数据
//   @PostMapping("/deleteModuleMenuDate/{id}")
//   public Result deleteModuleMenuDate(@PathVariable Integer id, @RequestBody SysMenuModule sysMenuModule)
//   {
//	   //传入要删除的菜单id和模块id;
////	   SysMenuModule sysMenuModule=new SysMenuModule();
////		sysMenuModule.setModule_id(1);
////		sysMenuModule.setMenu_id(7);
//		SysMenuModule sysMenuModule2=new SysMenuModule();
//
//		//将点击菜单从菜单模块表删除
//			sysModuleService.deleteModuleMenu(sysMenuModule);
//
//	        //判断是不是叶子节点
//		      //用传进来的菜单id去查找它的所有子菜单
//		      List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();
//
//		    //  int id=sysMenuModule.getMenu_id();
//		      sysOtherMenus=fun2(id,sysOtherMenus);
//		    //如果不是叶子节点，再递归找到该菜单的子菜单，将子菜单随着点击菜单一起从模块菜单表删除
//	          if(!sysOtherMenus.isEmpty())
//	           {
//		        for(SysMenu sysMenu:sysOtherMenus)
//			    {
//				sysMenuModule2.setModule_id(sysMenuModule.getModule_id());
//				sysMenuModule2.setMenu_id(sysMenu.getId());
//				//将点击菜单的子菜单从模块菜单关联表删除
//				sysModuleService.deleteModuleMenu(sysMenuModule2);
//			    }
//	          }
//	             //如果是叶子节点，不做处理
//
//	       //再判断该菜单是不是顶级
//	            //再用传进来的模块id,去查找它的上一级菜单属性
//	  		    SysMenu sysMenu=sysMenuService.selectParentMenu(sysMenuModule.getMenu_id());
//	  		    System.out.println(sysMenu);
//
////	  		    Boolean flag=false;
//                //如果不是顶级菜单，再查找该菜单的同级菜单，如果该菜单的同级菜单的其他菜单都已经被删除了，则在删除该菜单时，将点击菜单的上一级菜单也删除
//	  		  if(sysMenu.getMenu_parent_id()!=0)
//	  		{
//
//	  			  System.out.println(sysMenu.getMenu_parent_id());
//	  			  fun6(sysMenu.getMenu_parent_id(),sysMenuModule.getModule_id());
//
//
//
//
//
//
//
//
//
////	  			//查找同级id,包括点击的菜单id
////	  			List<SysMenu> sysMenus2=sysMenuService.selectSameMenu(sysMenu.getMenu_parent_id());
////	  			System.out.println(sysMenus2);
////
////	  			for(SysMenu sysMenu2:sysMenus2)
////	  			{
////	  				SysMenuModule sysMenuModule3=new SysMenuModule();
////	  				sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
////	  				sysMenuModule3.setMenu_id(sysMenu2.getId());
////	  				SysMenuModule sysMenuModule4=sysMenuService.selectIsModuleMenu(sysMenuModule3);
////	  				if(sysMenuModule4==null)
////	  				{
////	  					flag=true;
////	  				}
////
////	  			}
//
//
////	  			if(!flag)
////	  			{
////	  				//同级菜单都在菜单模块表，所以将点击菜单的父级也从模块菜单表删除
////	  				SysMenu sysMenu2=sysMenuService.selectParentMenuId(sysMenu.getMenu_parent_id());
////	  				SysMenuModule sysMenuModule3=new SysMenuModule();
////	  				sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
////	  				sysMenuModule3.setMenu_id(sysMenu2.getId());
////	  				sysModuleService.deleteModuleMenu(sysMenuModule3);
////
////	  			}
////
//
//              //如果是顶级菜单，不做处理
//	  		}
//
//
//
//
//
//	   return ResultUtil.success();
//   }
//
//
   
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







	//######以树状的形式传给前台，传出json的名字需要改

	//接口:无参
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

//		//传入模块id
//		//int id = 1;
//
//
//		//先在菜单表中，查询所有的菜单
//		List<SysMenu> sysMenus = sysMenuService.selectAllMenu();
//
//		//再在模块菜单表中查询该模块已经和菜单关联的菜单id
//		List<SysMenuModule> SysMenuModule = sysModuleService.selectSameModule(id);
//
//		List<SysMenu> list = new ArrayList<SysMenu>();
//
//		//然后获得没有关联的菜单
//		for (SysMenu sysMenu : sysMenus) {
//			for (SysMenuModule sysMenuModule2 : SysMenuModule) {
//				if (sysMenu.getId() == sysMenuModule2.getMenu_id()) {
//					//将相同的添加到list集合
//					list.add(sysMenu);
//				}
//			}
//		}
//		//将相同的去除
//		sysMenus.removeAll(list);
//
//
//		//未选择的
//		System.out.println(sysMenus);
//
//		System.out.println("********" + sysMenus);
//		//已经选择的
//		System.out.println(list);
//
//
//		//已选按照属性结构输出
//		int authId = 0;
//		JSONArray jsonArray = new JSONArray();
//		jsonArray = this.getAuthsByparentId2(authId, list);
//
//
//		//向上递归查找菜单，因为有些菜单的上级菜单可能不在选择菜单，但是列表选择时要选择出来
//		List<SysMenu> sysMenus3 = new ArrayList<SysMenu>();
//		for (SysMenu sysMenu : sysMenus) {
//			List<SysMenu> sysOtherMenus = new ArrayList<SysMenu>();
//			sysOtherMenus = fun5(sysMenu.getMenu_parent_id(), sysOtherMenus);
//			sysMenus3.addAll(sysOtherMenus);
//		}
//
//		sysMenus3.addAll(sysMenus);
//
//
//		//去除sysMenus3中重复的菜单
//		List<SysMenu> sysMenus5 = new ArrayList<SysMenu>();
//		for (SysMenu sysMenus4 : sysMenus3) {
//			boolean flag = true;
//			if (sysMenus5.isEmpty()) {
//				sysMenus5.add(sysMenus4);
//			} else {
//				for (SysMenu sysMenus6 : sysMenus5) {
//					if (sysMenus6.getId() == sysMenus4.getId()) {
//						flag = false;
//					}
//				}
//
//				if (flag) {
//					sysMenus5.add(sysMenus4);
//				}
//
//			}
//		}
//
//
//		JSONArray jsonArray2 = new JSONArray();
//		jsonArray2 = this.getAuthsByparentId2(authId, sysMenus5);
//
//
//		System.out.println(jsonArray2);
//
//
//		Map<String, Object> menu = new HashMap<String, Object>();
//		menu.put("select", jsonArray);
//		menu.put("noselect", jsonArray2);
//
//
//		return ResultUtil.success(menu);





	}







////######以树状的形式传给前台，传出json的名字需要改
//
//   //接口:无参
//   //查询已选择的菜单，和没选择的菜单列表
//   @RequestMapping("/selectMenuList")
//   public Result selectMenuList(int id)
//   {
//
//
//	 //传入模块id
////	   int id=1;
//
//
//	   //先在菜单表中，查询所有的菜单
//	   List<SysMenu> sysMenus=sysMenuService.selectAllMenu();
//
//	   //再在模块菜单表中查询该模块已经和菜单关联的菜单id
//	  List<SysMenuModule> SysMenuModule=sysModuleService.selectSameModule(id);
//
//	  List<SysMenu> list=new ArrayList<SysMenu>();
//
//	   //然后获得没有关联的菜单
//	  for(SysMenu sysMenu:sysMenus)
//	  {
//		  for(com.demo.entity.SysMenuModule sysMenuModule2:SysMenuModule)
//		  {
//			  if(sysMenu.getId()==sysMenuModule2.getMenu_id())
//			  {
//				 //将相同的添加到list集合
//				 list.add(sysMenu);
//			  }
//		  }
//	  }
//	  //将相同的去除
//	  sysMenus.removeAll(list);
//
//
//	   //未选择的
//	   System.out.println(sysMenus);
//
//
//	   //已经选择的
//	   System.out.println(list);
//
//
//
//      int authId=0;
//     JSONArray jsonArray=new JSONArray();
//    jsonArray=this.getAuthsByparentId2(authId,list);
//
//
//    JSONArray jsonArray2=new JSONArray();
//    jsonArray2=this.getAuthsByparentId2(authId,sysMenus);
//
//     System.out.println(jsonArray2);
//
//
//
//
//
//
//     return ResultUtil.success(jsonArray);
//
//
//
//
//
//
//
//
//
//
//
//
////	//将已选菜单，和未选菜单分别向上递归，分别形成各种的树形菜单
////
////	   //在已选菜单中找到所有的顶级菜单，通过顶级菜单找到他们的所有下级菜单
////	   //找到所有已选的顶级菜单
////	   List<SysMenu> sysMenus2=new ArrayList<SysMenu>();
////	   for(SysMenu sysMenu:list)
////	   {
////		   if(sysMenu.getMenu_parent_id()==0)
////		   {
////			   sysMenus2.add(sysMenu);
////		   }
////	   }
////
////	 //再将每一个顶级菜单，以及它的下一级菜单分别放进一个list集合当中，再将这些集合又放到一个包含list集合的集合当中去
////
////	   List<List<SysMenu>> menuList=new ArrayList<List<SysMenu>>();
////	   for(SysMenu sysMenu:sysMenus2)
////	   {
////		   List<SysMenu> sonMenuAll=new ArrayList<SysMenu>();
////		   int menu_id=sysMenu.getId();
////		   sonMenuAll=fun2(menu_id, sonMenuAll);
////		   sonMenuAll.add(sysMenu);
////		   Collections.sort(sonMenuAll);
////		   menuList.add(sonMenuAll);
////	   }
////
////	   //输出已选菜单树形结构
////	   System.out.println(menuList);
////
////
////
////
////
////
////
////
////
////
////
////	   //在未选菜单中找到所有的顶级菜单，通过顶级菜单找到他们的所有下级菜单
////	   //找到所有未选的顶级菜单
////
////
////	 //向上递归查找菜单，因为有些菜单的上级菜单可能不在选择菜单，但是列表选择时要选择出来
////	    List<SysMenu> sysMenus3=new ArrayList<SysMenu>();
////	    for(SysMenu sysMenu:sysMenus)
////	    {
////	    	List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();
////	    	sysOtherMenus=fun5(sysMenu.getMenu_parent_id(), sysOtherMenus);
////	    	sysMenus3.addAll(sysOtherMenus);
////	    }
////
////	    sysMenus3.addAll(sysMenus);
////
////
////
////	    //去除sysMenus3中重复的菜单
////	   List<SysMenu> sysMenus5=new ArrayList<SysMenu>();
////	    for(SysMenu sysMenus4:sysMenus3)
////	    {
////	    	boolean flag=true;
////	    	if(sysMenus5.isEmpty())
////	    	{
////	    		sysMenus5.add(sysMenus4);
////	    	}
////	    	else
////	    	{
////			  for(SysMenu sysMenus6:sysMenus5)
////			  {
////				  if(sysMenus6.getId()==sysMenus4.getId())
////				  {
////					  flag=false;
////				  }
////			  }
////
////			  if(flag)
////			  {
////				  sysMenus5.add(sysMenus4);
////			  }
////
////			}
////	    }
////
////
////	  //找到所有未选的顶级菜单
////		   List<SysMenu> sysMenus6=new ArrayList<SysMenu>();
////		   for(SysMenu sysMenu:sysMenus5)
////		   {
////			   if(sysMenu.getMenu_parent_id()==0)
////			   {
////				   sysMenus6.add(sysMenu);
////			   }
////		   }
////
////		 //再将每一个顶级菜单，以及它的下一级菜单分别放进一个list集合当中，再将这些集合又放到一个包含list集合的集合当中去
////
////		   List<List<SysMenu>> menuList2=new ArrayList<List<SysMenu>>();
////		   for(SysMenu sysMenu:sysMenus6)
////		   {
////			   List<SysMenu> sonMenuAll=new ArrayList<SysMenu>();
////			   int menu_id=sysMenu.getId();
////			   sonMenuAll=fun2(menu_id, sonMenuAll);//找到的是顶级id在所有菜单的儿子，而不是未选菜单里面的儿子
////			   //判断属于未选菜单才让添加
////			   List<SysMenu> sysMenus4=new ArrayList<SysMenu>();
////			   for(SysMenu sysMenu2:sonMenuAll)
////			   {
////				  for(SysMenu sysMenu3:sysMenus5)
////				  {
////					  if(sysMenu2.getId()==sysMenu3.getId())
////					  {
////						  sysMenus4.add(sysMenu3);
////					  }
////				  }
////			   }
////
////			   	//将顶级菜单添加到sysMenus4集合中
////			   sysMenus4.add(sysMenu);
////			   //排序
////			   Collections.sort(sysMenus4);
////			   menuList2.add(sysMenus4);
////		   }
////
////		   //输出未选菜单树形结构
////		   System.out.println(menuList2);
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////	   //将两个树形结构放到map集合中
////	   Map map=new HashMap();
////		map.put("selectMenu",menuList);
////		map.put("noSelectMenu", menuList2);
////
////
////
////
////
////	   return ResultUtil.success(map);
//   }










//   //查找模块
//   @RequestMapping("/selectModule")
//   public Result selectModule()
//   {
//	 List<SysModule> sysModules=sysModuleService.selectAllModule();
//	   System.out.println(sysModules);
//	   
//	   return ResultUtil.success(sysModules);
//   }






















  //5菜单配置
   
   
//#######接口中传入json中，要加一个菜单图标和菜单等级
   //添加菜单
   @PostMapping(value="/insertMenu")
   public Result insertMenu(@RequestBody SysMenu sysMenu)//SysMenu sysMenu
   {
	   
//	   SysMenu sysMenu=new SysMenu();
//	   sysMenu.setMenu_name("活动项目");
//	   
//	   sysMenu.setMenu_parent_id(0);
	   
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
   
   
   
   
   
   
   //接口：菜单id
  //删除菜单
   @RequestMapping(
           value = "/deleteMenu2/{id}", 
           method = RequestMethod.GET,
           produces = MediaType.APPLICATION_JSON_VALUE
           )
    public Result deleteMenu2(@PathVariable int id)//int id
    {
 	   
 	   
// 	   int id=6;
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
   
   
   
    

    
    

    
    
   
 //#######查询出来的菜单要以树状结构传给前台 ,这一块先不管。不属于我们现在做的部分（有问题：已选菜单不需要向上递归，直接查出来的就欧克了）
   
   //接口：用户id
  //用户查询自己的菜单列表，这个当中存在1对多，需要求并集的模块，并集菜单，不属于菜单配置
    @RequestMapping("/selectUserMenuList")
   public Result selectUserMenuList(String uid, String pwd)//String uid,String pwd
   {
//    	String uid="系统管理员";
//    	String pwd="123";
    	Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(uid, pwd);
		try {
			subject.login(token);
		} catch (Exception e) {
			
			return ResultUtil.error(-2, "登陆失败");
		}
    	
//    	//用传入的用户名去查找用户
	    SysUser sysUser=sysUserService.selectUserByName(uid);
		
//    	查询用户所有在模块菜单表的菜单的
       List<SysMenu> sysMenus=sysMenuService.selectUserMenuList(sysUser.getId());
	    
	    
				
		int authId=0;
		JSONArray jsonArray=new JSONArray();
		jsonArray=this.getAuthsByparentId2(authId,sysMenus);
    	    	

		
	   
	 
	   
	   
	     Map<String, Object> map=new HashMap<String, Object>();
	     map.put("user", uid);
	     map.put("menu", jsonArray);
	     
	     
	     return ResultUtil.success(map);
		
//    	//传入用户id
//    	int id=2;
//    	查询用户所有在模块菜单表的菜单的
//    List<SysMenu> sysMenus=sysMenuService.selectUserMenuList(sysUser.getId());
//    
//    
//    TreeSet<SysMenu> sysMenus2=new TreeSet<SysMenu>();
//    
//    for(SysMenu sysMenu:sysMenus)
//    {
//    	
//    	sysMenus2.add(sysMenu);
// 
//    }
//		
//
//   
//    	
//    //向上递归查找菜单，因为有些菜单的上级菜单可能不在选择菜单，但是列表选择时要选择出来
//    List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();
//    List<SysMenu> sysMenus3=new ArrayList<SysMenu>();
//    for(SysMenu sysMenu:sysMenus2)
//    {
//    	sysOtherMenus=fun5(sysMenu.getMenu_parent_id(), sysOtherMenus);
//    	sysMenus3.addAll(sysOtherMenus);
//    }
//    
//    sysMenus3.addAll(sysMenus2);
//    
//    //set集合去掉里面重复的菜单
//    Set<SysMenu> sysMenus4=new TreeSet<SysMenu>();
//    sysMenus4.addAll(sysMenus3);                
//   System.out.println(sysMenus2);
    
    	
//	   return ResultUtil.success();
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
   
   

  
    
    
    

    
    
    
    
    
    
   

  
//   //用户在点击对应控制菜单时，将所有的菜单数据全部搜索出来展示，供用户选择模块要对应的菜单
//   @RequestMapping("/selectMenu3")
//   public Result selectMenu3()
//   {
//	   //查询顶级菜单
//	   List<SysMenu> sysMenus=sysModuleService.selectMenu2();
//	   
//	   List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();
//	   for(SysMenu sysMenu:sysMenus)
//		 {
//			 //得到顶级菜单id
//			 int id=sysMenu.getId();
//			 sysOtherMenus=fun2(id,sysOtherMenus);
//		 }
//	   
//	   sysOtherMenus.addAll(sysMenus);
//		System.out.println(sysOtherMenus);
//	   System.out.println(sysOtherMenus);
//	   return ResultUtil.success(sysOtherMenus);
//   }
   
   
   
   
   
   
//   //用户在模块中通过模块id查找菜单,用于显示已经和模块对应的菜单
//   @RequestMapping("/selectMenu2")
//   public Result selectMenu2()
//   {
//	   
//      int id1=1;
//	 //顶级菜单
//	 List<SysMenu> sysMenus2=sysModuleService.selectMenu(id1);
//	 //list集合
//	 List<SysMenu> sysOtherMenus=new ArrayList<SysMenu>();
//	
//	 for(SysMenu sysMenu:sysMenus2)
//	 {
//		 //得到顶级菜单id
//		 int id=sysMenu.getId();
//		 sysOtherMenus=fun2(id,sysOtherMenus);
//	 }
//	 sysOtherMenus.addAll(sysMenus2);
//	 System.out.println(sysOtherMenus);
//	 
//	 //  System.out.println(sysMenus);
//	   return ResultUtil.success(sysOtherMenus);
//   }
//   
   
   
   
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
//					jsonArray.getJSONObject(i);
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
//				JSONObject attrObj= new JSONObject();	
//				jsObj.put("attributes", attrObj);
				jsonArray.add(jsObj);
//				add(jsObj);
			}
			
			return jsonArray;
		}
                                                           
   
   
 
		
		
		
//将部门的变成树形部门	
		
		 /**
		 * 取子部门
		 * @param parentId
		 * @return
	 * @throws JSONException 
		 */
		private JSONArray getDepartmentByParentId(int parentId) throws JSONException 
		{
			JSONArray jsonArray=new JSONArray();
//		    List<SysMenu> auths=sysMenuService.selectSameMenu(parentId);
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

	   
		/**
		 * 取部门
		 * @param parentId
		 * @return
		 * @throws JSONException 
		 * @throws NumberFormatException 
		 */
		private JSONArray getDepartmentsByparentId(int parentId) throws NumberFormatException, JSONException{
			JSONArray jsonArray=this.getDepartmentByParentId(parentId);
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject=jsonArray.getJSONObject(i);
//						jsonArray.getJSONObject(i);
				if (Integer.parseInt(jsonObject.getString("display"))!=0){
					continue;
				}else{
					jsonObject.put("children",getDepartmentsByparentId(Integer.parseInt(jsonObject.getString("id"))));
				}
			}
			return jsonArray;
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
//						jsonArray.getJSONObject(i);
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
//	            List<SysDepartment> sysDepartments=sysDepartmentService.selectDepartmentByDepId(parentId);
			
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
		    
		    
		    @GetMapping("/selectModule")
		    public Result selectModule1()
		    {
		 	 List<SysModule> sysModules=sysModuleService.selectAllModule();
		 	   System.out.println(sysModules);
		 	 
		 	   //移除不需要的数据，加快传输的速度
		 	   List<SysModule> sysModules2=new ArrayList<SysModule>();
		 	   for(SysModule sysModule:sysModules)
		 	   {
		 		   SysModule sysModule2=new SysModule();
		 		   sysModule2.setId(sysModule.getId());
		 		   sysModule2.setModule_name(sysModule.getModule_name());
		 		   sysModules2.add(sysModule2);
		 	   }
		 	   
		 	   return ResultUtil.success(sysModules2);
		    }
		    @GetMapping(value="/selectModuleInformation/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
		    public Result selectModuleInformation(@PathVariable Integer id)
		    {
		 	
		 	SysModule sysModule=sysModuleService.selectModuleInformation(id);
		 	 
		 	   System.out.println(sysModule);
		 	   return ResultUtil.success(sysModule);
		    }
		    
		    @PostMapping(value="/selectUserByRealUsername")
		    public Result selectUserByRealUsername(@RequestBody SysUser name)
		    {
		  	  
		  	  SysUser sysUser2=sysUserService.selectUserByRealUsername(name);
		  	  System.out.println(sysUser2);
		  	  
		  	  return ResultUtil.success(sysUser2);
		    }



	@PostMapping (value = "/insertMenuModule/{id2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result insertMenuModule( @PathVariable Integer id2, @RequestBody List<Integer> ids)//SysMenuModule sysMenuModule
	{

//	int id2=2;
//	List<Integer> ids=new ArrayList<Integer>();
//	ids.add(2);
//	ids.add(3);

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



//		//再用传进来的模块id,去查找它的上一级菜单属性
//		SysMenu sysMenu=sysMenuService.selectParentMenu(sysMenuModule.getMenu_id());
//
//
//
//
//		Boolean flag=false;
//		//如果上级菜单属性不为0，则查找与传入菜单同级的菜单，再判断同级其他菜单是否全部关联，如果全部关联，则将传入进来菜单的上一级菜单也与当前模块进行关联，即也
//		//将其模块id和菜单id相关联
//		if(sysMenu.getMenu_parent_id()!=0)
//		{
//			//查找同级id,包括点击的菜单id
//			List<SysMenu> sysMenus2=sysMenuService.selectSameMenu(sysMenu.getMenu_parent_id());
//			System.out.println(sysMenus2);
//
//			for(SysMenu sysMenu2:sysMenus2)
//			{
//				SysMenuModule sysMenuModule3=new SysMenuModule();
//				sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
//				sysMenuModule3.setMenu_id(sysMenu2.getId());
//				SysMenuModule sysMenuModule4=sysMenuService.selectIsModuleMenu(sysMenuModule3);
//				if(sysMenuModule4==null)
//				{
//					flag=true;
//				}
//
//			}
//
//
//			if(!flag)
//			{
//				//都在，则将父级添加到已选的菜单
//				SysMenu sysMenu2=sysMenuService.selectParentMenuId(sysMenu.getMenu_parent_id());
//				SysMenuModule sysMenuModule3=new SysMenuModule();
//				sysMenuModule3.setModule_id(sysMenuModule.getModule_id());
//				sysMenuModule3.setMenu_id(sysMenu2.getId());
//				sysMenuService.insertMenuModule(sysMenuModule3);
//
//			}
//
//
//
//
//		}
//
//
//
//
//
//	//  sysMenuService.insertMenuModule(sysMenuModule);
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
