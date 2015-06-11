package com.zl.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.zl.common.util.oss.OssUtil;

/**
 * Oss测试
 * @author is_zhoufeng
 */
public class OssTest {
	
	String accessKeyId = "QnrP9Gry4yIhxIJU";
	String accessKeySecret = "hmUNtELSR6w38NlJl166x33RXZNmUK";
	String endPoint = "http://oss-cn-hangzhou.aliyuncs.com";
	String bucketName = "youyang";
	
	private OssUtil ossUtil = new OssUtil(accessKeyId, accessKeySecret, endPoint,bucketName) ;
	
	@Test
	public void testUpload() throws FileNotFoundException{  
		//要上传的文件
		String uploadFile = "C:/Users/Administrator/Desktop/sqoo笔记.txt"; 
		
		InputStream resouce = new FileInputStream(new File(uploadFile)) ;
		ossUtil.uploadFile(resouce, "userfile/aa.txt");  
		
	}
	
	@Test
	public void testDownload() throws IOException{  
		//要下载的文件
		String fileName = "userfile/aa.txt";
		InputStream is = ossUtil.getFile(fileName);
		//文件保存位置
		File saveFile = new File("C:/Users/Administrator/Desktop/sqoo笔记_download.txt");
		OutputStream out = new FileOutputStream(saveFile);
		byte[] buf = new byte[1024];
		int readLen = -1 ;
		while((readLen = is.read(buf)) != -1){
			out.write(buf , 0 ,readLen); 
		}
		out.close();
		is.close();
		System.out.println("下载文件完成"); 
	}

}
