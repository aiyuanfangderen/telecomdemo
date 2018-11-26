package com.demo.exception;

import com.demo.until.Result;
import com.demo.until.ResultEnum;
import com.demo.until.ResultUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author 陈回
 *
 */
@ControllerAdvice
public class GlobaExceptionHandler
{
	
	private static final Logger logger = LoggerFactory.getLogger(GlobaExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
      //如果是用户自定义异常抛出，则执行if语句的内容
        if(e instanceof MyException) {
            MyException myException = (MyException)e;
            return ResultUtil.error(myException.getCode(),myException.getMessage());
        }else{
            logger.error("系统异常",e);
              System.out.println("异常##########################");
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());

        }

    }
}
