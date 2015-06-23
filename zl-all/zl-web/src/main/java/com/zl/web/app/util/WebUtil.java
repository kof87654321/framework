package com.zl.web.app.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;
import com.zl.pojo.TUser;
import com.zl.web.app.Consts;
import com.zl.web.app.vo.AjaxResult;
import com.zl.web.app.vo.AjaxResultType;

/**
 * 
 * @author is_zhoufeng@163.com
 * 2015年1月25日 下午5:06:04
 */
public class WebUtil {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(WebUtil.class);

	/**
	 * 输出Json内容到客户端，并关闭连接
	 * @param result
	 * @param response
	 */
	public static void ajaxOutput(AjaxResult result , ServletResponse response){
		PrintWriter out = null ;
		try {
			if(result == null){
				result = new AjaxResult() ;
				result.setSuccess(AjaxResultType.FAIL.getValue());
			}
			String jsonResult = JSON.toJSONString(result) ;
			out = response.getWriter(); 
			out.write(jsonResult);
		} catch (IOException e) {  
			log.error(e.getMessage() , e);
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
	 * 从Request中获取当前用户信息 （Controller方法加入了@Security注解才能使用）
	 * @param request
	 * @return
	 */
	public static TUser getCurrentUser(HttpServletRequest request){
		Object obj = request.getAttribute(Consts.CURRENT_USER_REQUEST_KEY) ; 
		if(obj == null){
			return null ;
		}
		return (TUser)obj ;
	}

}
