package com.zl.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zl.client.device.DeviceService;
import com.zl.pojo.TDeviceUser;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

@Controller
@RequestMapping("/device")
public class DeviceController {

    private static final Logger log = LoggerFactory.getLogger(ChattControl.class);

    @Autowired
    private DeviceService       deviceService;

    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
                    @RequestParam(value = "deviceToken", required = true, defaultValue = "") String deviceToken,
                    @RequestParam(value = "platform", required = false, defaultValue = "") String platForm,
                    @RequestParam(value = "token", required = false, defaultValue = "") String token,
                    @RequestParam(value = "version", required = false) String version,
                    @RequestParam(value = "appId", required = false, defaultValue = "-1") Integer appId) {
        if (StringUtils.isBlank(deviceToken)) {
            WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "参数不正确:deviceToken不能为空", 100), response);
            return;
        }
        TDeviceUser device = new TDeviceUser();
        device.setAppId(appId);
        device.setPlatForm(platForm);
        device.setVersion(version);
        device.setDeviceToken(deviceToken);
        if (userId > 0) {
            device.setUserId(userId);
        }
        boolean result = deviceService.addDevice(device, token);
        if (result) {
            WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response);
        } else {
            WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "添加设备信息失败", 101), response);
        }
    }
}
