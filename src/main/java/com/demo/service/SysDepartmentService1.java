package com.demo.service;

import java.util.List;

import com.demo.dto.UserPosition;
import com.demo.entity.SysDepartLeader;
import com.demo.entity.SysDepartment;
import com.demo.entity.SysUser;

public interface SysDepartmentService1
{
	public void insertDepartment(SysDepartment sysDepartment);
	
	
	
	public void insertDepartmentLeader(SysDepartLeader sysDepartLeader);
	
	
	
	public List<SysDepartment> selectDepartmentZero();
	
	
	
	public List<SysDepartment> seleceLowerDepartment(int id);
	
	
	
	
	
	public SysDepartment selectDepartmentInformation(String name);
	
	
	public SysDepartment selectDepartmentInformationById(String id);
	
	
	
	 public SysDepartment selectDepartmentInformationByNumber(String number);
	
	
	 public void deleteDepartment(int id);
	 
	 
	 
	 public int selectDepartIdByName(String dep_name);
	 
	 public SysUser selectUserId(int id);
	 
	 
	public void deleteDepartmentLeader(int id);
	
	
	
	public void updateDepartment(SysDepartment sysDepartment);
	
	
	
	public void updateDepartmentLeader(SysDepartLeader sysDepartLeader);
	
	
	
	public SysDepartment selectDepartmentById(int id);
	
	
	
	
	
public SysDepartLeader selectDepartmentLeaderId(int id);



public void updatePrincipal(UserPosition userPosition);



public void updateDeputy(UserPosition userPosition);



public void deleteDepartmentLeader2(SysDepartLeader sysDepartLeader);



public void deleteDepartmentLeader3(int user_id);


public List<SysDepartment> selectDepartmentByDepId(int parent_dep_id);



}
