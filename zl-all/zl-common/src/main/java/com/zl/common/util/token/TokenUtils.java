package com.zl.common.util.token;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.zl.common.util.encrypt.DesUtils;

/**
 * token工具类
 * 
 * @author zhangxianjun
 * @version $Id: TokenUtils.java, v 0.1 2015年6月15日 下午7:50:31 zhangxianjun Exp $
 */
public class TokenUtils {
	private static final Logger LOGGER      = LoggerFactory.getLogger(TokenUtils.class);

	private static final String ENCRYPT_KEY = "ABCD14CJO5F68DY69EQ5IWBYA3F2DESJ";

	private static String       HENGXIAN    = "_";

	/**
	 * 生成token
	 * 
	 * @param userId
	 * @param password
	 * @param lastLoginTime
	 * @return
	 */
	public static String getToken(Long userId, String password, Date lastLoginTime) {
		String message = MessageFormat.format("{0}" + HENGXIAN + "{1}" + HENGXIAN + "{2}", String.valueOf(userId),
				password, String.valueOf(lastLoginTime.getTime()));
		String token = null;
		try {
			token = DesUtils.encryptString(ENCRYPT_KEY, message);
			token = token.trim();
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			LOGGER.error("get token error!", e);
		}
		return token;
	}

	/**
	 * 校验token
	 * 
	 * @param userId
	 * @param password
	 * @param token
	 * @return
	 */
	public static boolean checkToken(Long userId, String password, String token) {
		token = StringUtils.replace(token, " ", "+");
		String tokenSource = null;
		try {
			tokenSource = DesUtils.decryptString(ENCRYPT_KEY, token);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			return false;
		}
		if (org.apache.commons.lang3.StringUtils.isBlank(tokenSource)) {
			return false;
		}
		String[] tokens = tokenSource.split(HENGXIAN);
		//如果需要，解析出来的日期可以用来做token过期比对
		return (userId + "").equals(tokens[0]) && (password + "").equals(tokens[1]);
	}

	/**
	 * 解析出token字符串中包含的内容(by:zhofueng)
	 * @param token
	 * @return
	 */
	public static TokenInfo parseToken(String token){
		token = StringUtils.replace(token, " ", "+");
		String tokenSource = null;
		try {
			tokenSource = DesUtils.decryptString(ENCRYPT_KEY, token);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			return null;
		}
		if (org.apache.commons.lang3.StringUtils.isBlank(tokenSource)) {
			return null;
		}
		String[] tokens = tokenSource.split(HENGXIAN);

		TokenInfo tokenInfo = null;
		try {
			tokenInfo = new TokenInfo();
			tokenInfo.setUserId(Long.parseLong(tokens[0]));
			tokenInfo.setPassword(tokens[1]);   
			tokenInfo.setLastLoginTime(new Date(Long.valueOf(tokens[2])));
		} catch (Exception e) {
			LOGGER.error("解析token失败" , e);  
		} 
		return tokenInfo ;
	}
}
