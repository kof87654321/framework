package com.zl.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.client.verson.VersionService;
import com.zl.pojo.TVersion;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

@Controller
@RequestMapping("/app/version")
public class AppVersionControl {
	
	private static final Logger log = LoggerFactory.getLogger(AppVersionControl.class);
	
	@Autowired
	private VersionService versionService ;

	/**
	 * 获取最新版本，通过浏览器{host:port}/app/version/newVersion.htm?appId=1进行访问
	 * @param appId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/newVersion") 
	public void newVersion(Integer appId , HttpServletRequest request, HttpServletResponse response) {
		log.info("newVersion request...");
		TVersion newVersion = versionService.getNewVersionByAppId(appId); 
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(newVersion), response);  
	}
	
}
