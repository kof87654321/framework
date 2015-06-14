package com.zl.common.util.sms;

import java.io.Serializable;

public class ValidCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1836395812131634451L;

	private int tryCount;
	private String validCode;
	private String mobile;

	/**
	 * 偿试次数加1
	 */
	public void increaseTryCount() {
		tryCount++;
	}

	public int getTryCount() {
		return tryCount;
	}

	public void setTryCount(int tryCount) {
		this.tryCount = tryCount;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
