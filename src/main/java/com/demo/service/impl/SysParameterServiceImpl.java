package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.ParameterIdName;
import com.demo.entity.SysParameter;
import com.demo.entity.SysParameterType;
import com.demo.mapper.SysParameterMapper;
import com.demo.service.SysParameterService;
@Service
public class SysParameterServiceImpl implements SysParameterService{

	@Autowired
	private SysParameterMapper sysParameterMapper;
	
	
	@Override
	public List<ParameterIdName> selectAllParameter() {
		
		return sysParameterMapper.selectAllParameter();
	}


	@Override
	public SysParameter selectParameterById(int id) {
		
		return sysParameterMapper.selectParameterById(id);
	}


	@Override
	public void insertParameter(SysParameter sysParameter) {
		sysParameterMapper.insertParameter(sysParameter);
		
	}


	@Override
	public SysParameter selectParameterType(String parm_type) {
		
		return sysParameterMapper.selectParameterType(parm_type);
	}
	
	
	@Override
	public void updateParameter(SysParameter sysParameter) {
		sysParameterMapper.updateParameter(sysParameter);
		
	}


	@Override
	public void deleteParameter(int id) {
		sysParameterMapper.deleteParameter(id);
		
	}


	@Override
	public List<SysParameterType> selectAllParameterType() {
		
		return sysParameterMapper.selectAllParameterType();
	}


	@Override
	public List<SysParameter> selectParameterByParameterTypeName(String parm_type) {
	   
		return  sysParameterMapper.selectParameterByParameterTypeName(parm_type);
	}


	@Override
	public void insertParameterType(SysParameterType sysParameterType) {
		sysParameterMapper.insertParameterType(sysParameterType);
		
	}


	@Override
	public void updateParameterType(SysParameterType sysParameterType) {
		
		sysParameterMapper.updateParameterType(sysParameterType);
	}


	@Override
	public void deleteParameterType(int id) {
		sysParameterMapper.deleteParameterType(id);
		
	}


	@Override
	public SysParameter selectParameterByTypeId(int id) {
		
		return sysParameterMapper.selectParameterByTypeId(id);
		
	}




}
