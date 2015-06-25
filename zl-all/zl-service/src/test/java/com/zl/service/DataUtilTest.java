package com.zl.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zl.dao.mapper.TAreaMapperExt;
import com.zl.dao.mapper.TIndustryCategroyMapperExt;
import com.zl.dao.mapper.TIndustryMapperExt;
import com.zl.pojo.TArea;
import com.zl.pojo.TAreaExample;
import com.zl.pojo.TIndustry;
import com.zl.pojo.TIndustryCategroy;
import com.zl.pojo.TIndustryCategroyExample;
import com.zl.pojo.TIndustryExample;

public class DataUtilTest extends BaseServiceTest{

	@Autowired
	private TAreaMapperExt areaMapper ;

	@Autowired
	private TIndustryCategroyMapperExt industryCategroyMapperExt ;

	@Autowired
	private TIndustryMapperExt industryMapperExt ;

	@Test
	public void test01(){
		List<TArea> areaList = areaMapper.selectByExample(new TAreaExample());
		for (TArea area : areaList) {
			area.setName(area.getName().replace("\r\n", "").trim());  
			areaMapper.updateByPrimaryKeySelective(area);
		}
		System.out.println("done."); 
	}

	@Test
	public void test02(){
		List<TIndustryCategroy> list = industryCategroyMapperExt.selectByExample(new TIndustryCategroyExample()) ;
		for (TIndustryCategroy tIndustryCategroy : list) {
			tIndustryCategroy.setName(tIndustryCategroy.getName().replace("\r\n", "").trim());
			industryCategroyMapperExt.updateByPrimaryKeySelective(tIndustryCategroy);
		}
		System.out.println("done.");
	}

	@Test
	public void test03(){
		List<TIndustry> list = industryMapperExt.selectByExample(new TIndustryExample());
		for (TIndustry tIndustry : list) {
			tIndustry.setName(tIndustry.getName().replace("\r\n", "").trim());
			industryMapperExt.updateByPrimaryKeySelective(tIndustry);
		}
		System.out.println("done."); 
	}

}
