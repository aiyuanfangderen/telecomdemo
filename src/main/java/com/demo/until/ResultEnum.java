package com.demo.until;
//枚举类
public enum ResultEnum
{
	
	    UNKNOWN_ERROR(500,"未知错误"),
	    SUCCESS(200,"操作成功"),
	    ERROR(-2, "服务器端异常");
	    
//	    ERROR_1(111,"未成年1"),

//	    ERROR_2(112,"未成年2");

	 
	    private int code;
	    private String msg;

	 

	    ResultEnum(int code, String msg) {
	        this.code = code;
	        this.msg = msg;
	    }

	 
	    public int getCode() {
	        return code;
	    }

	 

	    public String getMsg() {
	        return msg;
	    }
}
