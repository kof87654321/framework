package com.zl.service.version.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.verson.VersionService;
import com.zl.dao.mapper.TVersionMapperExt;
import com.zl.pojo.TVersion;

@Service
public class VersionServiceImpl implements VersionService{
	
	@Autowired
	private TVersionMapperExt versionMapper ;

	@Override
	public void insertVersion(TVersion version) {
		Date currentDate = new Date();
		version.setCreateTime(currentDate);
		version.setModifyTime(currentDate);
		versionMapper.insert(version); 
	}

/*	@Override
	public TVersion getNewVersionByAppId(Integer appId) {
		TVersionExample condition = new TVersionExample() ;//查询条件
		condition.createCriteria().andAppIdEqualTo(appId);
		condition.setOrderByClause(" id desc "); //根据ID倒序
		condition.setPage(new Page(0,1));  //只查询1条数据
		List<TVersion> result =  versionMapper.selectByExample(condition); 
		if(result != null && result.size() > 0){
			return result.get(0);
		} 
		return null;
		
	} */
	
	
	/* 第二种查询方式，自己写sql查询 */
	@Override
	public TVersion getNewVersionByAppId(Integer appId) {
		return versionMapper.selectNewVersionByAppId(appId);
	}
	
	
	
	
}
