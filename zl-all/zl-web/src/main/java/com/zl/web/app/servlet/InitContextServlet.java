package com.zl.web.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zl.common.util.bit.PropertiesConfigure;

/**
 * 
 * @author is_zhoufeng
 *
 */
public class InitContextServlet  extends HttpServlet{

	private static final Logger log = LoggerFactory.getLogger(InitContextServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3189110752397318951L;

	@Override
	public void init() throws ServletException {
		super.init();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		PropertiesConfigure propertyConfigure = applicationContext.getBean(PropertiesConfigure.class);
		if(propertyConfigure == null){
			log.error("初始化ContextPath、StaticServer失败！，没找到" + PropertiesConfigure.class.getName() + "对象");
		}
		String appContextPath = propertyConfigure.getProperties("app.context.path");
		String appStaticserverPath = propertyConfigure.getProperties("app.staticserver.path");
		String fileServerPath = propertyConfigure.getProperties("app.fileserver.path");
		if(StringUtils.isBlank(appContextPath)){  
			log.error("没找到app.context.path属性");
		}
		if(StringUtils.isBlank(appStaticserverPath)){
			log.error("没找到app.staticserver.path属性");  
		}
		if(StringUtils.isBlank(fileServerPath)){
			log.error("没找到app.fileserver.path属性");
		}
		getServletContext().setAttribute("ctx", appContextPath); 
		getServletContext().setAttribute("static_server", appStaticserverPath); 
		getServletContext().setAttribute("file_server", fileServerPath);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		throw new RuntimeException("不能访问该地址！");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		throw new RuntimeException("不能访问该地址！");
	}
	
}
