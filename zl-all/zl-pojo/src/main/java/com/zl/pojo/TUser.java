package com.zl.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * tableName{T_User}
 * by is_zhoufeng@163.com 2015-06-14 16:11:25
 */
public class TUser implements Serializable {
    /**
     * 用户ID  
     * column{Id},jdbcType{BIGINT}
     */
    private Long id;

    /**
     * 用户名  
     * column{UserName},jdbcType{VARCHAR}
     */
    private String userName;

    /**
     * 密码  
     * column{Password},jdbcType{VARCHAR}
     */
    private String password;

    /**
     * 创建时间  
     * column{CreateTime},jdbcType{TIMESTAMP}
     */
    private Date createTime;

    /**
     * 最后登录时间  
     * column{LastLoginTime},jdbcType{TIMESTAMP}
     */
    private Date lastLoginTime;

    /**
     * 用户注册使用的邀请码  
     * column{InviteCode},jdbcType{VARCHAR}
     */
    private String inviteCode;

    /**
     *   
     * column{Token},jdbcType{VARCHAR}
     */
    private String token;

    /**
     * 状态：1正常，0废弃  
     * column{Status},jdbcType{INTEGER}
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID  
     * column{Id},jdbcType{BIGINT}
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户名  
     * column{UserName},jdbcType{VARCHAR}
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 密码  
     * column{Password},jdbcType{VARCHAR}
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 创建时间  
     * column{CreateTime},jdbcType{TIMESTAMP}
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后登录时间  
     * column{LastLoginTime},jdbcType{TIMESTAMP}
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 用户注册使用的邀请码  
     * column{InviteCode},jdbcType{VARCHAR}
     */
    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    /**
     *   
     * column{Token},jdbcType{VARCHAR}
     */
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 状态：1正常，0废弃  
     * column{Status},jdbcType{INTEGER}
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}