package com.zl.client.file;

import com.zl.pojo.TFile;

/**
 * 
 * @author is_zhoufeng
 */
public interface FileService {

	boolean insert(TFile file);
	
	TFile getByUrl(String url); 
	
	TFile getById(Long id );

}
