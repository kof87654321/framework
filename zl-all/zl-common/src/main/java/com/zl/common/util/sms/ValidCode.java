package com.zl.common.util.sms;

import java.io.Serializable;

/**
 * 验证码对象
 * 
 * @author zhangxianjun
 * @version $Id: ValidCode.java, v 0.1 2015年6月15日 下午7:49:22 zhangxianjun Exp $
 */
public class ValidCode implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1836395812131634451L;

    /**
     * 验证尝试次数 
     */
    private int               tryCount;

    /**
     * 手机验证码
     */
    private String            validCode;

    /**
     * 手机号
     */
    private String            mobile;

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
