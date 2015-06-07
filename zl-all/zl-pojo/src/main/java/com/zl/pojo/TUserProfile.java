package com.zl.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * tableName{T_User_Profile}
 * by is_zhoufeng@163.com 2015-06-07 14:53:51
 */
public class TUserProfile implements Serializable {
    /**
     *   
     * column{Id},jdbcType{BIGINT}
     */
    private Long id;

    /**
     * 用户ID  
     * column{UserId},jdbcType{BIGINT}
     */
    private Long userId;

    /**
     *   
     * column{CreateTime},jdbcType{TIMESTAMP}
     */
    private Date createTime;

    /**
     *   
     * column{ModifyTime},jdbcType{TIMESTAMP}
     */
    private Date modifyTime;

    /**
     * 描述：Json格式  
     * column{Attributes},jdbcType{VARCHAR}
     */
    private String attributes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user_profile
     *
     * @mbggenerated Sun Jun 07 14:53:51 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     *   
     * column{Id},jdbcType{BIGINT}
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID  
     * column{UserId},jdbcType{BIGINT}
     */
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *   
     * column{CreateTime},jdbcType{TIMESTAMP}
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *   
     * column{ModifyTime},jdbcType{TIMESTAMP}
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 描述：Json格式  
     * column{Attributes},jdbcType{VARCHAR}
     */
    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}