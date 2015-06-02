package com.zl.web.control;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zl.client.user.UserService;
import com.zl.common.util.bit.PropertiesConfigure;
import com.zl.pojo.Lvuser;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * 示例
 * @author is_zhoufeng
 *
 */
@Controller
@RequestMapping("/hello/")
public class HelloWorldControl {
	
	private static final Logger log = LoggerFactory.getLogger(HelloWorldControl.class);
	
	@Autowired
	private PropertiesConfigure propertiesConfigure ;
	
	@Autowired
	private UserService userService ;
	
	/**
	 * 普通http请求实例,浏览器输入http://127.0.0.1:8080/hello/index.htm进行访问
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("index")
	public String index(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		modelMap.put("userList", userService.selectAll()) ;     
		log.info("index request...");
		return "index";  
	}

	/**
	 * ajax请求实例（返回json内容）,浏览器输入http://127.0.0.1:8080/hello/json.htm进行访问
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping("json") 
	@ResponseBody
	public void json(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		List<Lvuser> userList = userService.selectAll();
		
		/*
		//业务成功，使用如下方式输出
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(userList), response); 
		*/
		
		//业务失败，使用如下方式输出 
		WebUtil.ajaxOutput(AjaxResult.newFailResult(
				userList /*这个参数是返回的数据，随便传什么都行,可以为空*/, 
				"请求出错啦", /*错误消息，自定义*/ 
				-1 /*这个参数是错误码，自定义*/ )
				, response);
		
		
		log.info("json request...");
	}
	
}
