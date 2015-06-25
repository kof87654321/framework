package com.zl.web.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.client.industry.IndustryService;
import com.zl.vo.CategoryIndustryVo;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * 
 * @author is_zhoufeng
 *
 */
@Controller
@RequestMapping("/industry/")
public class IndustryController {

	private  List<CategoryIndustryVo> allCategoryIndustryCache = null ;

	@Autowired
	private IndustryService industryService ;

	@RequestMapping("getAll") 
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		if(allCategoryIndustryCache == null){
			allCategoryIndustryCache = industryService.getAll() ;
		} 
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(allCategoryIndustryCache), response); 
	} 
	
	@RequestMapping("clearCache") 
	public void clearCache(HttpServletRequest request, HttpServletResponse response) {
		allCategoryIndustryCache = null ;
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response);  
	}
	

}
