package com.zl.common.util.oss;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 阿里云OSS工具类
 * @author is_zhoufeng
 */
public class OssUtil {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(OssUtil.class);

	private String accessKeyId ;
	private String accessKeySecret  ;
	private String endPoint ;
	private String bucketName ;

	private OSSClient ossClient ;

	public OssUtil(String accessKeyId, String accessKeySecret, String endPoint , String bucketName) {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret ;
		this.endPoint = endPoint ;
		this.bucketName = bucketName ;
		ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret) ;
	}

	/**
	 * 上传文件
	 * @param resouce 文件输入流
	 * @param fileName 文件名(相对路径，不能以/开头)。名称在使用UTF-8编码后长度必须在 1-1023字节之间，而且不能包含回车、换行、以及xml1.0不支持的字符，同时也不能以“/”或者“\”开头。
	 */
	public boolean uploadFile(InputStream resouce , String fileName){ 
		ObjectMetadata meta = new ObjectMetadata(); 
		try {
			meta.setContentLength(resouce.available());
			PutObjectResult result =  ossClient.putObject(bucketName, fileName, resouce, meta) ;
			log.debug(result.getETag());
			return true ;
		} catch (IOException e) {
			log.error("上传文件到OSS失败" ,e);
			return false ;
		}
	}
	
	/**
	 * 根据文件名获取文件输入流（使用完成后请关闭输入流） 
	 * @param fileName
	 * @return
	 */
	public InputStream getFile(String fileName){
		OSSObject obj = ossClient.getObject(bucketName, fileName) ;
		if(obj == null){
			return null ;
		}
		InputStream result = obj.getObjectContent() ;
		return result ;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}


	public String getAccessKeySecret() {
		return accessKeySecret;
	}


	public String getEndPoint() {
		return endPoint;
	}


}
