package com.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.entity.SysMenu;
import com.demo.entity.SysUser;
import com.demo.service.SysDepartmentService;
import com.demo.service.SysMenuService;
import com.demo.service.SysModuleService;
import com.demo.service.SysRoleService;
import com.demo.service.SysUserService;
import com.demo.until.Result;
import com.demo.until.ResultUtil;
//用户登陆controller
@RestController
@RequestMapping("login")
public class Login
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
//  					jsonArray.getJSONObject(i);
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
//  				JSONObject attrObj= new JSONObject();	
//  				jsObj.put("attributes", attrObj);
  				jsonArray.add(jsObj);
//  				add(jsObj);
  			}
  			
  			return jsonArray;
  		}
    
    
    
    
    
    
}
