package com.zl.web.app.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.zl.client.user.TUserService;
import com.zl.common.util.token.TokenUtils;
import com.zl.pojo.TUser;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * 校验token
 * @author zhangxianjun
 *
 */
public class TokenCheckFilter implements Filter {

    private static final Set<String> ignoreUri = new HashSet<String>();

    @Autowired
    private TUserService             tUserService;

    static {
        ignoreUri.add("/user/register");
        ignoreUri.add("/user/checkToken");
        ignoreUri.add("/addDevice");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                             ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();
        if (!canBeIgnore(uri)) {
            Long userId = Long.valueOf(httpRequest.getParameter("userId"));
            String token = httpRequest.getParameter("token");
            TUser user = tUserService.getUserById(userId);
            if (user == null) {
                WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "用户不存在", 403), httpResponse);
                return;
            }
            boolean flag = TokenUtils.checkToken(user.getId(), user.getPassword(), token);
            if (!flag) {
                WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "token校验失败，请检查是否登录", 403), httpResponse);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean canBeIgnore(String uri) {
        Iterator<String> it = ignoreUri.iterator();
        while (it.hasNext()) {
            if (uri.indexOf(it.next()) != -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
