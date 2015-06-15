package com.zl.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于安全校验的注解
 * 
 * @author zhangxianjun
 * @version $Id: Security.java, v 0.1 2015年6月15日 下午7:51:43 zhangxianjun Exp $
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {

}
