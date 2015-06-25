package com.zl.web.app.util;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.zl.common.util.Constant;
import com.zl.common.util.ProjectEnv;
import com.zl.common.util.sms.HuYiSmsUtil;
import com.zl.common.util.sms.ValidCode;

/**
 * 短信验证码
 * 
 * @author zhangxianjun
 *
 */
public class ValidCodeUtil {

	public static final Map<String, ValidCode> ValidCode            = new ConcurrentHashMap<String, ValidCode>();

	public static HuYiSmsUtil                  huYiSmsUtil          = (HuYiSmsUtil) SpringContextUtil
			.getBean("huYiSmsUtil");

	private static ProjectEnv projectEnv = (ProjectEnv) SpringContextUtil.getBean("projectEnv"); 

	/**
	 * 验证码尝试次数
	 */
	private static final int                   VALID_CODE_TRY_LIMIT = 5;

	public static ValidCode sendValidCode(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return null;
		}
		ValidCode code = new ValidCode();
		String valid = getCode();
		code.setValidCode(valid);
		code.setTryCount(0);
		code.setMobile(mobile);

		boolean result = true ;
		/**
		 * 测试阶段只能使用下面格式的短信，其他格式会发送失败，审核通过后，将下面代码块注释掉
		 */
		String conent = "您的验证码是：" + valid + "。请不要把验证码泄露给其他人。" ;
		if(Constant.ENV.ONLINE.equals(projectEnv.getEnv())){  //线上环境才发送验证码
			result = huYiSmsUtil.sendSms(mobile, conent);
		}
		if (result) {
			ValidCode.put(mobile, code);
			return code;
		}
		return null;
	}

	/**
	 * 验证码校验
	 * @param mobile
	 * @param valid
	 * @param remove  校验成功后是否在服务端移除改验证码
	 * @return
	 */
	public static boolean checkValid(String mobile, String valid , boolean remove) {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(valid)) {
			return false;
		}
		ValidCode code = ValidCode.get(mobile);
		if (code == null) {
			return false;
		}
		if (valid.equals(code.getValidCode())) {
			if(remove){
				ValidCode.remove(mobile);
			}
			return true;
		} else {
			code.increaseTryCount();
			ValidCode.put(mobile, code);
		}
		if (code.getTryCount() >= VALID_CODE_TRY_LIMIT) {
			ValidCode.remove(mobile);
			return false;
		}
		return false;
	}

	private static String getCode() {
		Random rand = new Random();
		String code = String.valueOf(rand.nextInt(1000000));
		code = "000000" + code;
		code = code.substring(code.length() - 6);
		return code;
	}
}
