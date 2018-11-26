package com.demo.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.ParameterIdName;
import com.demo.entity.SysModule;
import com.demo.entity.SysParameter;
import com.demo.entity.SysParameterType;
import com.demo.service.SysParameterService;
import com.demo.until.Result;
import com.demo.until.ResultUtil;

@RestController
@RequestMapping("/parameter")
public class Parameter {
	@Autowired
	private SysParameterService sysParameterService;
	
	   //查找所有的参数的id和name
	   @PostMapping("/selectParameter")
	   public Result selectParameter()
	   {		  
		List<ParameterIdName> parameters=sysParameterService.selectAllParameter();
		  
		   
		   return ResultUtil.success(parameters);
	   }
	 
	   //通过id查找参数的详细信息
	   @PostMapping("/selectAllParameterInformation")
	   public Result selectAllParameterInformation()
	   {		 
		   int id=1;
		    SysParameter selectParameterById = sysParameterService.selectParameterById(id);
		   
		   
		   return ResultUtil.success(selectParameterById);
	   }

	   //添加参数
	   @RequestMapping("/insertParameter")
	   public Result insertParameter()
	   {
//###考不考虑这个		   //还要判断参数类型和参数名是否完全一样，如果完全一样则不让插入

		   SysParameter sysParameter=new SysParameter();
		   sysParameter.setParm_type("ORDERTYPE");
		   sysParameter.setParm_name("你好");
		   //判断添加的参数的参数类型是否已经存在，存在则让插入，不然不让
		SysParameter sysParameter2=sysParameterService.selectParameterType(sysParameter.getParm_type());
		   if(sysParameter2!=null)
		   {
			   sysParameterService.insertParameter(sysParameter);
		   }

		   else
		   {
			   return ResultUtil.error(-2, "参数类型不存在，不能插入");
		   }
		   
		   
		   
		   return ResultUtil.success();
	   }
	   
	   //更新参数
	   @RequestMapping("/updateParameter")
	   public Result updateParameter()
	   {
		   SysParameter sysParameter=new SysParameter();
		   sysParameter.setId(77);
		   sysParameter.setParm_name("它好");
		   sysParameterService.updateParameter(sysParameter);
		   
		   return ResultUtil.success();
	   }
	   //删除参数
	   @RequestMapping("/deleteParameter")
	   public Result deleteParameter()
	   {
		   
		   int id=77;
		   sysParameterService.deleteParameter(id);
		  
		  
		   return ResultUtil.success();
	   }
	   
	   
	   //查找所有的参数类型
	   @RequestMapping("/selectAllParameterType")
	   public Result selectAllParameterType()
	   {
		List<SysParameterType> sysParameterTypes= sysParameterService.selectAllParameterType();
		 System.out.println(sysParameterTypes);
		  
		  
		   return ResultUtil.success(sysParameterTypes);
	   }
	   
	   
	 //通过参数类型名查找参数
	   @RequestMapping("/selectParameterByParameterType")
	   public Result selectParameterByParameterType()
	   {
		  String parm_type="ORDERTYPE";
		  List<SysParameter> sysParameters=sysParameterService.selectParameterByParameterTypeName(parm_type);
		  System.out.println(sysParameters);
		   return ResultUtil.success(sysParameters);
	   }
	   
	   
	   //添加参数类型
	   @RequestMapping("/insertParameterType")
	   public Result insertParameterType()
	   {
//####考不考虑这个情况		   //添加参数时，如果有相同的参数类型则不让插入
		   SysParameterType sysParameterType=new SysParameterType();
		   sysParameterType.setParm_type_name("你好");
		   sysParameterService.insertParameterType(sysParameterType);
		   
		   return ResultUtil.success();
	   }
	   
	   //更新参数类型
	   @RequestMapping("/updateParameterType")
	   public Result updateParameterType()
	   {
		   SysParameterType sysParameterType=new SysParameterType();
		   sysParameterType.setId(17);
		   sysParameterType.setParm_type_name("你好");
		   sysParameterService.updateParameterType(sysParameterType);
		   
		   return ResultUtil.success();
	   }
	   //删除参数类型
	   @RequestMapping("/deleteParameterType")
	   public Result deleteParameterType()
	   {
//####		   //删除参数类型时，如果参数类型下面有参数则不让删除
		   int id=17;
		SysParameter sysParameter=sysParameterService.selectParameterByTypeId(id);
		   if(sysParameter==null)
		   {
			   sysParameterService.deleteParameterType(id);
		   }
		   else
		   {
			   return ResultUtil.error(-2,"下面存在参数，不能被删除");
		   }
		  
		  
		   return ResultUtil.success();
	   }
	   
	   
}
