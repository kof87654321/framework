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
import com.zl.common.util.token.TokenUtils;
import com.zl.pojo.TUser;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

@Aspect
@Component
public class SecurityAop {

    @Autowired
    private TUserService tUserService;

    @Around("@annotation(com.zl.web.annotation.Security)")
    public void doCheck(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
            .getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
            .getResponse();
        String uid = request.getParameter("userId");
        String token = request.getParameter("token");
        if (StringUtils.isBlank(uid) || StringUtils.isBlank(token)) {
            WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "参数错误", 403), response);
            return;
        }
        Long userId = Long.valueOf(uid);
        TUser user = tUserService.getUserById(userId);
        if (user == null) {
            WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "用户不存在", 403), response);
            return;
        }
        boolean flag = TokenUtils.checkToken(user.getId(), user.getPassword(), token);
        if (!flag) {
            WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "token校验失败，请检查是否登录", 403), response);
            return;
        }
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
