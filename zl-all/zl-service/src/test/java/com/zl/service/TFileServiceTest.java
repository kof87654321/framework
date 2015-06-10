package com.zl.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zl.client.file.FileService;
import com.zl.pojo.TFile;

public class TFileServiceTest extends BaseServiceTest{

	@Autowired
	private FileService fileService ;
	
	@Test
	public void testInsert(){
		TFile file = new TFile() ;
		file.setBizType((byte)1);
		file.setFileName("aaa.txt");
		file.setFileType((byte)2);
		file.setUrl("/photos/1.txt");  
		file.setUserId(1L); 
		boolean success = fileService.insert(file); 
		System.out.println(success); 
	}
	
}
