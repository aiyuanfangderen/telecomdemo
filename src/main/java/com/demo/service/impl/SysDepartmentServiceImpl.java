package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.UserPosition;
import com.demo.mapper.SysDepartmentMapper;
import com.demo.po.SysDepartLeader;
import com.demo.po.SysDepartment;
import com.demo.po.SysUser;
import com.demo.service.SysDepartmentService;
//部门实现类
@Service
public class SysDepartmentServiceImpl implements SysDepartmentService
{

	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;
	
	public void insertDepartment(SysDepartment sysDepartment)
	{
		sysDepartmentMapper.insertDepartment(sysDepartment);
	}
	
	
	public void insertDepartmentLeader(SysDepartLeader sysDepartLeader)
	{
		sysDepartmentMapper.insertDepartmentLeader(sysDepartLeader);
	}
	
	
	public List<SysDepartment> selectDepartmentZero()
	{
		return sysDepartmentMapper.selectDepartmentZero();
	}
	
	
	public List<SysDepartment> seleceLowerDepartment(int id)
	{
		return sysDepartmentMapper.seleceLowerDepartment(id);
	}
	
	
	
	
	public SysDepartment selectDepartmentInformation(String name)
	{
		return sysDepartmentMapper.selectDepartmentInformation(name);
	}
	
	public SysDepartment selectDepartmentInformationById(String id)
	{
		return sysDepartmentMapper.selectDepartmentInformationById(id);
	}
	
	
	 public SysDepartment selectDepartmentInformationByNumber(String number)
	 {
		 return sysDepartmentMapper.selectDepartmentInformationByNumber(number);
	 }
	
	 public void deleteDepartment(int id)
	 {
		  sysDepartmentMapper.deleteDepartment(id);
	 }
	 
	 
	 public int selectDepartIdByName(String dep_name)
	 {
		 return sysDepartmentMapper.selectDepartIdByName(dep_name);
	 }
	 
	 public SysUser selectUserId(int id)
	 {
		 return sysDepartmentMapper.selectUserId(id);
	 }
	 
	public void deleteDepartmentLeader(int id)
	{
		sysDepartmentMapper.deleteDepartmentLeader(id);
	}
	
	
	public void updateDepartment(SysDepartment sysDepartment)
	{
		sysDepartmentMapper.updateDepartment(sysDepartment);
	}
	
	
	public void updateDepartmentLeader(SysDepartLeader sysDepartLeader)
	{
		sysDepartmentMapper.updateDepartmentLeader(sysDepartLeader);
	}
	
	
	public SysDepartment selectDepartmentById(int id)
	{
		return sysDepartmentMapper.selectDepartmentById(id);
	}
	
	
	
	
public SysDepartLeader selectDepartmentLeaderId(int id)
{
	return sysDepartmentMapper.selectDepartmentLeaderId(id);
	
	
}


public void updatePrincipal(UserPosition userPosition)
{
	sysDepartmentMapper.updatePrincipal(userPosition);
}


public void updateDeputy(UserPosition userPosition)
{
	sysDepartmentMapper.updateDeputy(userPosition);
}


public void deleteDepartmentLeader2(SysDepartLeader sysDepartLeader)
{
	sysDepartmentMapper.deleteDepartmentLeader2(sysDepartLeader);
}


public void deleteDepartmentLeader3(int user_id)
{
	sysDepartmentMapper.deleteDepartmentLeader3(user_id);
	
}

public List<SysDepartment> selectDepartmentByDepId(int parent_dep_id)
{
	return sysDepartmentMapper.selectDepartmentByDepId(parent_dep_id);
}

	
}
