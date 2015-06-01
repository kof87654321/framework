package com.zl.web.control;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.client.user.UserService;

@Controller
@RequestMapping
public class IndexControl {
	
	@Resource
	private UserService userService ;
	
	@RequestMapping("/index")
	public String index(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		modelMap.put("userList", userService.selectAll()) ;  
		return "index"; 
	}

}
