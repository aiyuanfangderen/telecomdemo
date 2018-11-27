package com.demo.mapper;

import java.util.List;

import com.demo.dto.ParameterIdName;
import com.demo.po.SysParameter;
import com.demo.po.SysParameterType;

public interface SysParameterMapper {

	public List<ParameterIdName> selectAllParameter();
	
	public SysParameter selectParameterById(int id);
	
	public void insertParameter(SysParameter sysParameter);
	
	public SysParameter selectParameterType(String parm_type);
	
	public void updateParameter(SysParameter sysParameter);
	
	public void deleteParameter(int id);
	
	
	
	
	public List<SysParameterType> selectAllParameterType();
	
	
	public List<SysParameter> selectParameterByParameterTypeName(String parm_type);
	
	
    public void insertParameterType(SysParameterType sysParameterType);
	
	public void updateParameterType(SysParameterType sysParameterType);
	
	public void deleteParameterType(int id);
	
	
	public SysParameter selectParameterByTypeId(int id);
	
}
