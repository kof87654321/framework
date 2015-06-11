package com.zl.web.control;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zl.client.file.FileService;
import com.zl.common.util.bit.PropertiesConfigure;
import com.zl.common.util.oss.OssUtil;
import com.zl.pojo.TFile;
import com.zl.web.app.Consts;
import com.zl.web.app.Consts.Upload.BizType;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * 文件上传
 * @author is_zhoufeng
 */
@Controller
@RequestMapping("/file/")
public class FileController {

	private static final Logger log = LoggerFactory.getLogger(FileController.class);

	public static final String SEPARATOR = "/" ;

	@Autowired
	private FileService fileService ;
	
	@Autowired
	private OssUtil ossUtil ;

	@Autowired
	private PropertiesConfigure propertiesConfigure ;

	/**
	 * 上传文件
	 * @param file
	 * @param fileType [1:图片，2:语音 ,3:视频]
	 * @param bizType  [1:用户头像,2:动态,3:聊天]  
	 * @param request
	 * @param model
	 */
	@RequestMapping("upload") 
	public void uplaod(@RequestParam("file")MultipartFile file, Byte fileType,Byte bizType
			,HttpServletRequest request, HttpServletResponse response ){
		String filename = file.getOriginalFilename() ;
		String extension = FilenameUtils.getExtension(filename);
		String baseDir = propertiesConfigure.getProperties(Consts.PropertiesKey.APP_UPLOAD_DIR);
		BizType bizTypeEnum = Consts.Upload.BizType.getByType(bizType) ;
		if(bizTypeEnum == null){
			log.warn("不支持上传该业务类型的文件!"); 
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "不支持上传该业务类型的文件!", -1), response);  
			return;
		}
		String saveDirPath = bizTypeEnum.getDir();
		File saveDir = new File(baseDir,saveDirPath);
		if(!saveDir.exists()){
			saveDir.mkdirs();
		}
		String saveFileName = UUID.randomUUID().toString().replace("-", "") + "." + extension ; //保存的文件名随机生成 
		File saveFile = new File(saveDir,saveFileName);  
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException  | IOException e) {
			log.error("保存文件失败" , e);  
			WebUtil.ajaxOutput(AjaxResult.newExceptionResult(e, "保存文件失败", -2), response);   
			return ;
		}

		//文件相对路径
		String saveFilePath = saveDirPath + SEPARATOR + saveFileName ;  

		TFile tfile = new TFile();
		tfile.setBizType(bizType);
		tfile.setFileType(fileType);
		tfile.setFileName(saveFileName); 
		tfile.setUrl(saveFilePath); 
		tfile.setUserId(1L); //TODO 设置成当前登录用户ID 
		boolean insertResult = fileService.insert(tfile);
		if(!insertResult){
			log.error("文件信息保存到数据库失败"); 
		}
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(tfile), response);
	}

	
	/**
	 * 上传文件到OSS
	 * @param file
	 * @param fileType [1:图片，2:语音 ,3:视频]
	 * @param bizType  [1:用户头像,2:动态,3:聊天]  
	 * @param request
	 * @param model
	 */
	@RequestMapping("oss/upload") 
	public void uplaod2Oss(@RequestParam("file")MultipartFile file, Byte fileType,Byte bizType
			,HttpServletRequest request, HttpServletResponse response ){
		String filename = file.getOriginalFilename() ;
		String extension = FilenameUtils.getExtension(filename);
		BizType bizTypeEnum = Consts.Upload.BizType.getByType(bizType) ;
		if(bizTypeEnum == null){
			log.warn("不支持上传该业务类型的文件!"); 
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "不支持上传该业务类型的文件!", -1), response);  
			return;
		}
		String saveDirPath = bizTypeEnum.getDir();
		
		String saveFileName = UUID.randomUUID().toString().replace("-", "") + "." + extension ; //保存的文件名随机生成 
		
		//文件相对路径
		String saveFilePath = saveDirPath + SEPARATOR + saveFileName ;  
		
		try {
			ossUtil.uploadFile(file.getInputStream(), saveFilePath) ;
		} catch (IOException e) {
			log.error("上传文件到OSS失败" ,e);  
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "上传文件到OSS失败!", -2), response); 
			return ;
		}
		
		TFile tfile = new TFile();
		tfile.setBizType(bizType);
		tfile.setFileType(fileType);
		tfile.setFileName(saveFileName); 
		tfile.setUrl(saveFilePath); 
		tfile.setUserId(1L); //TODO 设置成当前登录用户ID 
		boolean insertResult = fileService.insert(tfile);
		if(!insertResult){
			log.error("文件信息保存到数据库失败"); 
		}
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(tfile), response);
	}
	
	

}

