package com.zl.web.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zl.common.util.bit.PropertiesConfigure;
import com.zl.web.app.Consts;
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
	
	/**
	 * 普通http请求示例,浏览器输入http://127.0.0.1:8080/hello/index.htm进行访问
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response , ModelMap modelMap) {
		return "hello/index";  
	}

	/**
	 * ajax请求示例（返回json内容）,浏览器输入http://127.0.0.1:8080/hello/json.htm进行访问
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping("json") 
	@ApiOperation(value="JSON测试",httpMethod = "POST" , notes = "JSON测试。。。")
	public void json(
			@ApiParam(required = false ,name = "param" , value = "随便什么参数")
			@RequestParam(value="param" , required = false )Integer param , 
			HttpServletRequest request, HttpServletResponse response) {
		
		//业务成功，使用如下方式输出
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult("success"), response); 
		
		
		/*//业务失败，使用如下方式输出 
		WebUtil.ajaxOutput(AjaxResult.newFailResult(
				userList 这个参数是返回的数据，随便传什么都行,可以为空, 
				"请求出错啦", 错误消息，自定义 
				-1 这个参数是错误码，自定义 )
				, response);*/
		
		log.info("json request...");
	}
	
	
	/**
	 * 去到上传文件页面,浏览器输入http://127.0.0.1:8080/hello/uploadView.htm进行访问
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("uploadView") 
	public String uploadView(HttpServletRequest request, HttpServletResponse response){
		return "hello/uploadView";
	}
	
	/**
	 * 上传文件示例
	 * @param file
	 * @param type
	 * @param request
	 * @param model
	 */
	@RequestMapping("upload") 
	public void uplaod(@RequestParam("file")MultipartFile file, 
			@RequestParam(value = "type" , required = false)String type, 
			HttpServletRequest request, HttpServletResponse response ){
		 log.info("type:{}",type);
		 String filename = file.getOriginalFilename() ;
		 log.info("filename:{}",filename);
		 String uploadDir = propertiesConfigure.getProperties(Consts.PropertiesKey.APP_UPLOAD_DIR);
		 log.info("uploadDir:{}",uploadDir);  
		 File dir = new File(uploadDir);
		 if(!dir.exists()){
			 dir.mkdirs() ;
		 }
		 File uplaodFile = new File(dir,filename);
		 try {
			file.transferTo(uplaodFile);
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(filename), response); 
		} catch (IllegalStateException | IOException e) {
			log.error(e.getMessage() , e);  
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "upload fail", -1), response);   
		}
	}
	
	
}
