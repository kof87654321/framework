package com.zl.web.app.handler;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * Controller全局异常处理器
 * @author is_zhoufeng
 *
 */
public class ControlExceptionHandler implements HandlerExceptionResolver{

	private static final Logger log = LoggerFactory.getLogger(ControlExceptionHandler.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		log.error("后台异常,{}" + exception.getMessage()); 
		HandlerMethod handlerMethod = (HandlerMethod)handler ;
		Method controllerMethod = handlerMethod.getMethod();
		Class<?> returnType = controllerMethod.getReturnType();
		if(returnType == Void.TYPE){  //如果返回值类型为Void，那么可以判断该方法返回JSON类型
			WebUtil.ajaxOutput(AjaxResult.newExceptionResult(exception, "后台异常", -500 ), response);  
			return null ;
		}else{   
			//如果返回类型不为Void，那么就跳转到错误404页面
			return new ModelAndView("/404.html");
		}
	}

	
	
}
