package com.zl.client.emchat;

import java.util.List;

import com.zl.vo.IMUserVO;

/**
 * 环信IM用户服务
 * 
 * @author zhangxianjun
 * @version $Id: EMUserService.java, v 0.1 2015年6月21日 下午6:20:59 zhangxianjun Exp $
 */
public interface EMUserService {

    /**
     * 注册IM用户
     * 
     * @param user
     */
    public boolean register(IMUserVO user);

    /**
     * IM用户登录
     * 
     * @param user
     */
    public boolean login(IMUserVO user);

    /**
     * 获取IM用户信息
     * 
     * @param username
     * @return
     */
    public IMUserVO getUser(String userName);

    /**
     * IM用户修改密码
     * 
     * @param user
     */
    public boolean modifyPwd(IMUserVO user);

    /**
     * 删除IM用户
     * 
     * @param username
     */
    public boolean deleteUser(String userName);

    /**
     * 添加好友
     * 
     * @param username
     * @param firendname
     */
    public boolean addFriend(String userName, String firendName);

    /**
     * 删除好友
     * 
     * @param username
     * @param firendname
     */
    public boolean deleteFriend(String userName, String firendName);

    /**
     * 获取好友列表
     * 
     * @param username
     */
    public void getFriends(String userName);

    /**
     * 批量注册IM用户
     * 建议每次注册20-60个，不能太多
     * @param users
     */
    public boolean registerBatch(List<IMUserVO> users);

    /**
     * 批量删除IM用户
     * 
     * @param users
     */
    public boolean deleteBatch(List<IMUserVO> users);
}
