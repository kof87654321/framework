package com.zl.vo;

import java.io.Serializable;

/**
 * IM用户对象 
 * 
 * @author zhangxianjun
 * @version $Id: IMUserVO.java, v 0.1 2015年6月21日 下午6:44:20 zhangxianjun Exp $
 */
public class IMUserVO implements Serializable {

    /**  */
    private static final long serialVersionUID = 285176250633864953L;

    private String            uuid;

    private Long              userId;

    private String            type;

    private String            username;

    private String            nikcname;

    private String            password;

    private Boolean           activated;

    private Long              createTime;

    private Long              modifyTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNikcname() {
        return nikcname;
    }

    public void setNikcname(String nikcname) {
        this.nikcname = nikcname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

}
