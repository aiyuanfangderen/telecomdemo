package com.demo.entity;

public class MyNodeRelation {

	private int currentcode;
	
	public int getCurrentcode() {
		return currentcode;
	}
	public void setCurrentcode(int currentcode) {
		this.currentcode = currentcode;
	}
	private int next_code_id;
	private String condition_name;
	private String condition_expression;
	
	
	
	
	public int getNext_code_id() {
		return next_code_id;
	}
	public void setNext_code_id(int next_code_id) {
		this.next_code_id = next_code_id;
	}
	public String getCondition_name() {
		return condition_name;
	}
	public void setCondition_name(String condition_name) {
		this.condition_name = condition_name;
	}
	public String getCondition_expression() {
		return condition_expression;
	}
	public void setCondition_expression(String condition_expression) {
		this.condition_expression = condition_expression;
	}
	
	
	
	
	
	
}
