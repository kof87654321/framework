package com.zl.web.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zl.client.user.TUserService;
import com.zl.common.util.ProjectEnv;
import com.zl.common.util.token.TokenInfo;
import com.zl.common.util.token.TokenUtils;
import com.zl.pojo.TUser;
import com.zl.web.app.Consts;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * Security注解的AOP实现
 * 
 * @author zhangxianjun
 * @version $Id: SecurityAop.java, v 0.1 2015年6月15日 下午7:52:05 zhangxianjun Exp $
 */
@Aspect
@Component
public class SecurityAop {

    @Autowired
    private TUserService tUserService;

    @Autowired
    private ProjectEnv   projectEnv;

    @Around("@annotation(com.zl.web.annotation.Security)")
    public void doCheck(ProceedingJoinPoint joinPoint) {
    	/*
        if (projectEnv != null && StringUtils.isNotBlank(projectEnv.getEnv())
            && Constant.ENV.TEST.equals(projectEnv.getEnv())) {
            try {
                joinPoint.proceed();
            } catch (Throwable e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }*/

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
            .getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
            .getResponse();
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "参数错误", Consts.ERRORCode.TOKEN_ERROR), response);
            return;
        }
        TokenInfo tokenInfo = TokenUtils.parseToken(token);
        if(tokenInfo == null){
        	 WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "token校验失败", Consts.ERRORCode.TOKEN_ERROR), response); 
             return;
        }
        TUser user = tUserService.getUserById(tokenInfo.getUserId()); 
        if (user == null) {
            WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "用户不存在", Consts.ERRORCode.TOKEN_ERROR), response);
            return;
        }
        boolean flag = TokenUtils.checkToken(user.getId(), user.getPassword(), token);
        if (!flag) {
            WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "token校验失败，请检查是否登录", Consts.ERRORCode.TOKEN_ERROR),
                response);
            return;
        }
        request.setAttribute(Consts.CURRENT_USER_REQUEST_KEY, user); 
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
