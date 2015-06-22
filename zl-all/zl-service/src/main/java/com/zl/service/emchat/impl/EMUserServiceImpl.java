package com.zl.service.emchat.impl;

import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zl.client.emchat.EMUserService;
import com.zl.service.emchat.comm.Constants;
import com.zl.service.emchat.comm.HTTPMethod;
import com.zl.service.emchat.comm.Roles;
import com.zl.service.emchat.httpclient.utils.HTTPClientUtils;
import com.zl.service.emchat.httpclient.vo.ClientSecretCredential;
import com.zl.service.emchat.httpclient.vo.Credential;
import com.zl.service.emchat.httpclient.vo.EndPoints;
import com.zl.vo.IMUserVO;

@Service
public class EMUserServiceImpl implements EMUserService {

    private static final Logger          LOGGER         = LoggerFactory.getLogger(EMUserServiceImpl.class);
    private static final JsonNodeFactory factory        = new JsonNodeFactory(false);

    private static Credential            credential     = new ClientSecretCredential(Constants.APP_CLIENT_ID,
                                                            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    private static final String          STATUSCODE     = "statusCode";

    private static final Integer         HTTPSTATUS_200 = 200;

    @Override
    public boolean register(IMUserVO user) {
        if (user == null) {
            return false;
        }
        if (StringUtils.isBlank(user.getUsername())) {
            return false;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return false;
        }
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("userName", user.getUsername());
        datanode.put("password", user.getPassword());

        ObjectNode objectNode = factory.objectNode();

        if (!checkAppKey()) {
            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
            objectNode.put("message", "Bad format of Constants.APPKEY");
            return false;
        }
        objectNode.removeAll();
        try {
            objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL, credential, datanode,
                HTTPMethod.METHOD_POST);
            if (objectNode != null && HTTPSTATUS_200.equals(objectNode.get(STATUSCODE))) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean login(IMUserVO user) {
        if (user == null) {
            return false;
        }
        if (StringUtils.isBlank(user.getUsername())) {
            return false;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return false;
        }
        ObjectNode objectNode = factory.objectNode();

        if (!checkAppKey()) {
            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
            objectNode.put("message", "Bad format of Constants.APPKEY");
            return false;
        }

        try {
            ObjectNode dataNode = factory.objectNode();
            dataNode.put("grant_type", "password");
            dataNode.put("userName", user.getUsername());
            dataNode.put("password", user.getPassword());

            objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.TOKEN_APP_URL, null, dataNode,
                HTTPMethod.METHOD_POST);
            if (objectNode != null && HTTPSTATUS_200.equals(objectNode.get(STATUSCODE))) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Some errors occurred while fetching a token by userName and password .");
        }

        return false;
    }

    @Override
    public IMUserVO getUser(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        if (!checkAppKey()) {
            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
            return null;
        }

        IMUserVO user = null;
        try {
            ObjectNode objectNode = factory.objectNode();
            URL userPrimaryUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/" + userName);
            objectNode = HTTPClientUtils.sendHTTPRequest(userPrimaryUrl, credential, null, HTTPMethod.METHOD_GET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean modifyPwd(IMUserVO user) {
        if (user == null) {
            return false;
        }
        if (StringUtils.isBlank(user.getUsername())) {
            return false;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return false;
        }
        ObjectNode objectNode = factory.objectNode();

        if (!checkAppKey()) {
            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
            objectNode.put("message", "Bad format of Constants.APPKEY");
            return false;
        }

        try {
            URL modifyIMUserPasswordWithAdminTokenUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/")
                                                                               + "/users/" + user.getUsername()
                                                                               + "/password");
            objectNode = HTTPClientUtils.sendHTTPRequest(modifyIMUserPasswordWithAdminTokenUrl, credential,
                user.getPassword(), HTTPMethod.METHOD_PUT);
            if (objectNode != null && HTTPSTATUS_200.equals(objectNode.get(STATUSCODE))) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUser(String userName) {
        if (StringUtils.isBlank(userName)) {
            return false;
        }
        ObjectNode objectNode = factory.objectNode();

        if (!checkAppKey()) {
            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
            objectNode.put("message", "Bad format of Constants.APPKEY");
            return false;
        }

        try {

            URL deleteUserPrimaryUrl = HTTPClientUtils
                .getURL(Constants.APPKEY.replace("#", "/") + "/users/" + userName);
            objectNode = HTTPClientUtils.sendHTTPRequest(deleteUserPrimaryUrl, credential, null,
                HTTPMethod.METHOD_DELETE);
            if (objectNode != null && HTTPSTATUS_200.equals(objectNode.get(STATUSCODE))) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean addFriend(String userName, String friendName) {
        if (StringUtils.isBlank(userName)) {
            return false;
        }
        if (StringUtils.isBlank(friendName)) {
            return false;
        }
        ObjectNode objectNode = factory.objectNode();

        if (!checkAppKey()) {
            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
            objectNode.put("message", "Bad format of Constants.APPKEY");
            return false;
        }

        try {

            URL addFriendSingleUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/" + userName
                                                            + "/contacts/users/" + friendName);

            ObjectNode body = factory.objectNode();
            objectNode = HTTPClientUtils.sendHTTPRequest(addFriendSingleUrl, credential, body, HTTPMethod.METHOD_POST);
            if (objectNode != null && HTTPSTATUS_200.equals(objectNode.get(STATUSCODE))) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteFriend(String userName, String friendName) {
        if (StringUtils.isBlank(userName)) {
            return false;
        }
        if (StringUtils.isBlank(friendName)) {
            return false;
        }
        ObjectNode objectNode = factory.objectNode();

        if (!checkAppKey()) {
            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
            objectNode.put("message", "Bad format of Constants.APPKEY");
            return false;
        }
        try {
            URL addFriendSingleUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/" + userName
                                                            + "/contacts/users/" + friendName);

            ObjectNode body = factory.objectNode();
            objectNode = HTTPClientUtils
                .sendHTTPRequest(addFriendSingleUrl, credential, body, HTTPMethod.METHOD_DELETE);
            if (objectNode != null && HTTPSTATUS_200.equals(objectNode.get(STATUSCODE))) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void getFriends(String userName) {
        if (StringUtils.isBlank(userName)) {
            return;
        }

        ObjectNode objectNode = factory.objectNode();

        if (!checkAppKey()) {
            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
            objectNode.put("message", "Bad format of Constants.APPKEY");
            return;
        }
        try {

            URL addFriendSingleUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/" + userName
                                                            + "/contacts/users");

            ObjectNode body = factory.objectNode();
            objectNode = HTTPClientUtils.sendHTTPRequest(addFriendSingleUrl, credential, body, HTTPMethod.METHOD_GET);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean registerBatch(List<IMUserVO> users) {
        if (users == null || users.isEmpty()) {
            return false;
        }
        ObjectNode objectNode = factory.objectNode();

        if (!checkAppKey()) {
            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
            objectNode.put("message", "Bad format of Constants.APPKEY");
            return false;
        }
        ArrayNode arrayNode = factory.arrayNode();
        for (IMUserVO user : users) {
            ObjectNode userNode = factory.objectNode();
            if (user != null && StringUtils.isNoneBlank(user.getUsername())
                && StringUtils.isNoneBlank(user.getPassword())) {
                userNode.put("username", user.getUsername());
                userNode.put("password", user.getPassword());
                arrayNode.add(userNode);
            }
        }
        try {

            objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL, credential, arrayNode,
                HTTPMethod.METHOD_POST);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBatch(List<IMUserVO> users) {
        //环信接口暂时只提供删除N个用户的接口，无法具体删除某些用户
        //TODO
        return false;
    }

    /**
     * check Constants.APPKEY format
     * 
     * @return
     */
    private boolean checkAppKey() {
        return HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY);
    }
}
