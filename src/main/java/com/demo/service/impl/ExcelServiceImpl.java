package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.SysCheckStandard;
import com.demo.entity.SysCheckStdSchedule;
import com.demo.mapper.ExcelMapper;
import com.demo.service.ExcelService;
@Service
public class ExcelServiceImpl implements ExcelService{

	@Autowired
	private ExcelMapper excelMapper;
	
	@Override
	public void insertChectStandard(SysCheckStandard sysCheckStandard) {
		excelMapper.insertChectStandard(sysCheckStandard);
		
	}

	@Override
	public void insertChectStdSchedule(SysCheckStdSchedule sysCheckStdSchedule) {
		excelMapper.insertChectStdSchedule(sysCheckStdSchedule);
		
	}

}
