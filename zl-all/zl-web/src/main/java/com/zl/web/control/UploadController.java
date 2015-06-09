package com.zl.web.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zl.common.util.bit.PropertiesConfigure;
import com.zl.web.app.Consts;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

@Controller
@RequestMapping("/upload/")
public class UploadController {

	private static final Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private PropertiesConfigure propertiesConfigure ;
	
	/**
	 * 上传文件
	 * @param file
	 * @param fileType [1:图片，2:语音 ,3:视频]
	 * 
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

