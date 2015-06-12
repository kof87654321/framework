package com.zl.common.util.sms;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

/**
 * 短信验证码
 * 
 * @author zhangxianjun
 *
 */
public class ValidCodeUtil {

	public static final Map<String, ValidCode> ValidCode = new ConcurrentHashMap<String, ValidCode>();

	/**
	 * 验证码尝试次数
	 */
	private static final int VALID_CODE_TRY_LIMIT = 5;

	public static boolean sendValidCode(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return false;
		}
		ValidCode code = new ValidCode();
		String valid = getCode();
		code.setValidCode(valid);
		code.setTryCount(0);
		HuYiSmsUtil sms = new HuYiSmsUtil("", "");
		boolean result = sms.sendSms(mobile, valid);
		if (result) {
			ValidCode.put(mobile, code);
		}
		return result;
	}

	public static boolean checkValid(String mobile, String valid) {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(valid)) {
			return false;
		}
		ValidCode code = ValidCode.get(mobile);
		if (code == null) {
			return false;
		}
		if (code.getTryCount() >= VALID_CODE_TRY_LIMIT) {
			return false;
		}
		if (valid.equals(code.getValidCode())) {
			ValidCode.remove(mobile);
			return true;
		} else {
			code.increaseTryCount();
			ValidCode.put(mobile, code);
		}
		return false;
	}

	private static String getCode() {
		return String.valueOf((Math.random() * 9 + 1) * 100000);
	}
}
