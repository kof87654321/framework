package com.zl.web.app.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class HttpParamUtil {

	public static Integer integerParam(HttpServletRequest request, String key) {
		if (request == null || StringUtils.isBlank(key)) {
			return 0;
		}
		try {
			return Integer.parseInt(request.getParameter(key));
		} catch (Exception e) {
			return 0;
		}
	}

	public static Long longParam(HttpServletRequest request, String key) {
		if (request == null || StringUtils.isBlank(key)) {
			return 0l;
		}
		try {
			return Long.parseLong(request.getParameter(key));
		} catch (Exception e) {
			return 0l;
		}
	}

	public static Boolean booleanParam(HttpServletRequest request, String key) {
		if (request == null || StringUtils.isBlank(key)) {
			return false;
		}
		try {
			return integerParam(request, key) > 0 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Byte byteParam(HttpServletRequest request, String key) {
		if (request == null || StringUtils.isBlank(key)) {
			return 0;
		}
		try {
			return Byte.valueOf(request.getParameter(key)); 
		} catch (Exception e) {
			return 0;
		}
	}
}
