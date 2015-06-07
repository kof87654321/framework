package com.zl.web.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import api.ClasspathApiStore;

import com.zl.common.util.bit.PropertiesConfigure;
import com.zl.web.app.Consts;

public class InitAppUtilServlet extends HttpServlet{

	private static final Logger log = LoggerFactory.getLogger(InitContextServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2431956210528874408L;

	@Override
	public void init() throws ServletException {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		PropertiesConfigure propertyConfigure = applicationContext.getBean(PropertiesConfigure.class);
		if(propertyConfigure == null){
			log.error("初始化ContextPath、StaticServer失败！，没找到" + PropertiesConfigure.class.getName() + "对象");
		}
		String apiDir = propertyConfigure.getProperties(Consts.PropertiesKey.APP_API_DIR);
		ClasspathApiStore.setApiStoreDir(apiDir); 
	}
	
}
