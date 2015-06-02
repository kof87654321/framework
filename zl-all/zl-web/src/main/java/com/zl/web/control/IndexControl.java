package com.zl.web.control;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.client.user.UserService;
import com.zl.common.util.bit.PropertiesConfigure;

@Controller
@RequestMapping
public class IndexControl {
	
	private static final Logger log = LoggerFactory.getLogger(IndexControl.class);
	
	@Autowired
	private PropertiesConfigure propertiesConfigure ;
	
	@Resource(name="userService")
	private UserService userService ;
	
	@RequestMapping("/index")
	public String index(ServletRequest request, ServletResponse response , ModelMap modelMap) {
		modelMap.put("userList", userService.selectAll()) ;     
		log.info("index request...");
		return "index";  
	}

}
