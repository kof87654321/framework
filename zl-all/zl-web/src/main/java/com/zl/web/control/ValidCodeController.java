package com.zl.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zl.common.util.sms.ValidCode;
import com.zl.web.app.util.ValidCodeUtil;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * 验证码
 * 
 * @author zhangxianjun
 * @version $Id: ValidCodeController.java, v 0.1 2015年6月14日 下午3:00:59 zhangxianjun Exp $
 */
@Controller
@RequestMapping("/valid")
public class ValidCodeController {

    private static final Logger log = LoggerFactory.getLogger(ValidCodeController.class);

    @RequestMapping("/send")
    public void sendValidCode(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "mobile", required = true, defaultValue = "") String mobile) {
        if (StringUtils.isBlank(mobile)) {
            log.debug("mobile is null");
            WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response);
            return;
        }
        ValidCode code = ValidCodeUtil.sendValidCode(mobile);
        WebUtil.ajaxOutput(AjaxResult.newSuccessResult(code), response);
    }
}
