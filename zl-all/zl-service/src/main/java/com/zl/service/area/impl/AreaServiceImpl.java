package com.zl.service.area.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.area.AreaService;
import com.zl.dao.mapper.TAreaMapperExt;
import com.zl.pojo.TArea;
import com.zl.pojo.TAreaExample;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private TAreaMapperExt areaMapperExt ;
	
	@Override
	public List<TArea> getAll() {
		return areaMapperExt.selectByExample(new TAreaExample()) ;
	}

	
	
}
