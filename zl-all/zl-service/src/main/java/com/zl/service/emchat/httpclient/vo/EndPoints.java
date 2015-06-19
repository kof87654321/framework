package com.zl.service.emchat.httpclient.vo;

import java.net.URL;

import com.zl.service.emchat.comm.Constants;
import com.zl.service.emchat.httpclient.utils.HTTPClientUtils;

public interface EndPoints {

    static final URL TOKEN_APP_URL  = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/token");

    static final URL USERS_URL      = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users");

    static final URL MESSAGES_URL   = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/messages");

    static final URL CHATGROUPS_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatgroups");

    static final URL CHATFILES_URL  = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatfiles");
}
