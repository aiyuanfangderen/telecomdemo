package com.demo.dto;
//包装参数表的id和name
public class ParameterIdName {

	private Integer id;//参数id
	private String name;//参数名
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ParameterIdName [id=" + id + ", name=" + name + "]";
	}

	
}
