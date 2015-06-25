package com.zl.web.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.client.area.AreaService;
import com.zl.pojo.TArea;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * 
 * @author is_zhoufeng
 *
 */
@Controller
@RequestMapping("/area/")
public class AreaController {
	
	private static List<TArea> allAreaCache ; 
	
	@Autowired
	private AreaService areaService ;

	@RequestMapping("getAll") 
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		if(allAreaCache == null){
			allAreaCache = areaService.getAll() ;
		}
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(allAreaCache), response); 
	}
	
	@RequestMapping("clearCache") 
	public void clearCache(HttpServletRequest request, HttpServletResponse response) {
		allAreaCache = null ;
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response);  
	}
	
}
