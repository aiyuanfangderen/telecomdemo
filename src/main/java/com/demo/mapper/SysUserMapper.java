package com.demo.mapper;
//用户的mapper接口
import com.demo.dto.NamePassword;
import com.demo.dto.UserModule;
import com.demo.entity.SysRole;
import com.demo.entity.SysUser;
import com.demo.entity.SysUserRole;
import org.springframework.stereotype.Component;

import java.util.List;

public interface SysUserMapper 
{
   void insertUser(SysUser sysUser);

   void updateUser(SysUser sysUser);
   
   void insertUserRole(SysUserRole sysUserRole);
   
   
   
   //下面三个方法用于查询模块信息
   
    List<UserModule> selectModule(int id);
   
   
    SysUser selectUserByName(String username);
  
   
   SysUser selectUserByRealName(String realname);
   
    void updateUserState(SysUser sysUser);
   
    List<SysUser> selectUserByDepId(int dep_id);
   
    void updateUserDepId(SysUser sysUser);
   
   
  void deleteUserRole(int id);
   
   
  SysUser selectUserById(int id);
   
   
  void updateUserPosition(int id);
   
   
   void updateUserPosition2(SysUser sysUser);
   
   List<SysUser> selectAllUser();
   
   
   SysUser selectUserByDepIdReturnOne(int dep_id);
   
   List<SysUser> selectUserOnDepidNull();
   
   
   List<SysUserRole> selectUserRoleByUserId(int user_id);
   
   List<SysUserRole> selectUserRoleByRoleId(int role_id);
   
   
   SysUserRole selectUserRoleByUserIdReturnOne(int user_id);
   
   SysUserRole selectUserRoleByRoleIdReturnOne(int role_id);
   
   
    void updateUserDepIdById(SysUser sysUser);
   
   
   SysUser selectUserByPasswordUsername(NamePassword namePassword);
   
   List<SysRole> selectRoleByUserName(String user_name);
   
   List<SysUser> selectUserNameId();

   SysUser selectUserByRealUsername(SysUser sysUser);
   
   
}
