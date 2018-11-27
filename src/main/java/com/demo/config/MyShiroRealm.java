package com.demo.config;//package com.demo.config;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

/**
 * 认证授权
 */
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.dto.NamePassword;
import com.demo.po.SysRole;
import com.demo.po.SysUser;
import com.demo.service.SysUserService;
//shire的认证授权
@Controller
public class MyShiroRealm extends AuthorizingRealm
	{

		@Autowired
		private SysUserService sysUserService;

		//角色权限和对应权限添加
		@Override
		protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
			  //获取登录用户名
	        String name = (String) principalCollection.getPrimaryPrincipal();

	        //查询用户的角色
	       List<SysRole> sysRoles=sysUserService.selectRoleByUserName(name);

	        //添加角色和权限
	        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

	        for(SysRole sysRole:sysRoles)
	        {
	        	//添加角色
		        simpleAuthorizationInfo.addRole(sysRole.getRole_name());
	        }

//	        //添加权限
//	        simpleAuthorizationInfo.addStringPermission("create");

	        return simpleAuthorizationInfo;

		}
	    //用户认证
		@Override
		protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken atoken) throws AuthenticationException {
			UsernamePasswordToken token = (UsernamePasswordToken) atoken;
             //得到用户登陆的用户名和密码
	        String name = token.getUsername();
	         char[] pass=token.getPassword();
	         //将密码转化为字符串
	         String password=String.copyValueOf(pass);
	        //创建一个用户密码对象
	         NamePassword namePassword=new NamePassword();
	         namePassword.setUsername(name);
	         namePassword.setPassword(password);
             //从数据库查找是否存在这个用户
	       SysUser sysUser=sysUserService.selectUserByPasswordUsername(namePassword);

	        if (sysUser == null) {
	            return null;
	        }
	        //这里验证authenticationToken和simpleAuthenticationInfo的信息
	        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, sysUser.getUser_password(), getName());
	        return simpleAuthenticationInfo;

		}

}
