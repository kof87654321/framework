package com.zl.vo;

import java.util.List;

import com.zl.pojo.TIndustry;
import com.zl.pojo.TIndustryCategroy;

public class CategoryIndustryVo {

	private TIndustryCategroy category ;
	
	private List<TIndustry> industries ;
	
	public CategoryIndustryVo() {}

	public CategoryIndustryVo(TIndustryCategroy category,
			List<TIndustry> industries) {
		this.category = category;
		this.industries = industries;
	}

	public TIndustryCategroy getCategory() {
		return category;
	}

	public void setCategory(TIndustryCategroy category) {
		this.category = category;
	}

	public List<TIndustry> getIndustries() {
		return industries;
	}

	public void setIndustries(List<TIndustry> industries) {
		this.industries = industries;
	} 
	
}
