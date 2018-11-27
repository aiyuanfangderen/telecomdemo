package com.demo.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mapper.CustomerBpmnMapper;
import com.demo.po.MyNode;
import com.demo.po.MyNodeRelation;
import com.demo.service.CustomerBpmnService;
@Service
public class CustomerBpmnServiceImpl implements CustomerBpmnService {

	@Autowired
	private CustomerBpmnMapper customerBpmnMapper;
	
	
	
	
	
	@Override
	public List<MyNode> selectAllNodeByProcessId(int process_id) {
		
		return customerBpmnMapper.selectAllNodeByProcessId(process_id);
	}


	@Override
	public List<MyNodeRelation> selectNodeRelation(List<Integer> ids) {
		
		return customerBpmnMapper.selectNodeRelation(ids);
	}


	@Override
	public String selectNodeById(int id) {
		
		return customerBpmnMapper.selectNodeById(id);
	}

	
	
}
