package com.zl.service.file.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.file.FileService;
import com.zl.dao.mapper.TFileMapperExt;
import com.zl.pojo.TFile;
import com.zl.pojo.TFileExample;
import com.zl.pojo.TFileExample.Criteria;

/**
 * 
 * @author is_zhoufeng
 */
@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private TFileMapperExt tFileMapperExt ;

	@Override
	public boolean insert(TFile file) {
		return tFileMapperExt.insert(file) > 0; 
	}

	@Override
	public TFile getByUrl(String url) {
		TFileExample condition = new TFileExample() ;
		Criteria criteria = condition.createCriteria();
		criteria.andUrlEqualTo(url);
		List<TFile> files = tFileMapperExt.selectByExample(condition);
		if(files != null && files.size() > 0){
			return files.get(0) ;
		}
		return null; 
	}

	@Override
	public TFile getById(Long id) {
		return tFileMapperExt.selectByPrimaryKey(id); 
	}
	
	
	
}
