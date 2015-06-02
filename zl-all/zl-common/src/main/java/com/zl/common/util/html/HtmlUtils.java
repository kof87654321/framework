package com.zl.common.util.html;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * @author is_zhoufeng@163.com 
 * 2015年1月24日 下午6:33:05
 */
public class HtmlUtils {
	
	/**
	 * HTML标签转义
	 * @param content
	 * @return
	 */
	public static String toHtml(String content) {
	    if(content==null) return "";       
	    String html = content;
	    html = StringUtils.replace(html, "'", "&apos;");
	    html = StringUtils.replace(html, "\"", "&quot;");
	    html = StringUtils.replace(html, "\t", "&nbsp;&nbsp;");// 替换跳格
	    html = StringUtils.replace(html, " ", "&nbsp;");// 替换空格
	    html = StringUtils.replace(html, "<", "&lt;");
	    html = StringUtils.replace(html, ">", "&gt;");
	    return html;
	}


}
