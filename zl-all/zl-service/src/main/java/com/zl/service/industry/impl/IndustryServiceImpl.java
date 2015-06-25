package com.zl.service.industry.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.industry.IndustryService;
import com.zl.dao.mapper.TIndustryCategroyMapperExt;
import com.zl.dao.mapper.TIndustryMapperExt;
import com.zl.pojo.TIndustry;
import com.zl.pojo.TIndustryCategroy;
import com.zl.pojo.TIndustryCategroyExample;
import com.zl.pojo.TIndustryExample;
import com.zl.vo.CategoryIndustryVo;

@Service
public class IndustryServiceImpl implements IndustryService{

	@Autowired
	private TIndustryCategroyMapperExt industryCategroyMapperExt ;

	@Autowired
	private TIndustryMapperExt industryMapperExt ;

	@Override
	public List<CategoryIndustryVo> getAll() {
		List<TIndustryCategroy> categroies =  industryCategroyMapperExt.selectByExample(new TIndustryCategroyExample());
		if(categroies == null){
			return null ;
		}
		List<CategoryIndustryVo> result = new ArrayList<CategoryIndustryVo>() ;
		TIndustryExample condition = null ;
		List<TIndustry>  industries = null ;
		for (TIndustryCategroy category : categroies) {
			condition = new TIndustryExample() ;
			condition.createCriteria().andCateCodeEqualTo(category.getCode()) ;
			industries = industryMapperExt.selectByExample(condition);
			result.add(new CategoryIndustryVo(category, industries)) ;
		}
		return result; 
	}


}
