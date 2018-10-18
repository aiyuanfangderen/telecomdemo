package com.demo.until;
//返回的三种情况，异常，正常无返回对象，正常有返回对象
public class ResultUtil
{
	 public static Result error(int code, String msg) {
	        Result result = new Result();
	        result.setCode(code);
	        result.setMsg(msg);
	        result.setData(null);
	        return result;
	    }

	 

	    public static Result success(Object obj) {
	        Result result = new Result();
	        result.setCode(200);
	        result.setMsg("成功");
	        result.setData(obj);
	        return result;
	    }

	 

	    public static Result success() {
	        Result result = new Result();
	        result.setCode(200);
	        result.setMsg("成功");
	        result.setData(null);
	        return result;
	    }
}
