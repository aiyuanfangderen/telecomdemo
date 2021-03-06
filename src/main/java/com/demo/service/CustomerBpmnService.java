package com.demo.service;

import java.util.List;

import com.demo.po.MyNode;
import com.demo.po.MyNodeRelation;

public interface CustomerBpmnService {

	public  List<MyNode> selectAllNodeByProcessId(int process_id);
	
	public List<MyNodeRelation> selectNodeRelation(List<Integer> ids);
	
	public String selectNodeById(int id);
	
}
