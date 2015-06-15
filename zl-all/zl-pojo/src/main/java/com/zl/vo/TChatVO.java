package com.zl.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天对象
 * 
 * @author zhangxianjun
 * @version $Id: TChatVO.java, v 0.1 2015年6月15日 下午7:51:10 zhangxianjun Exp $
 */
public class TChatVO implements Serializable {

    /**  */
    private static final long serialVersionUID = -8919970054524606700L;

    private Long              id;

    /**
     * 消息接收者  
     */
    private Long              toId;

    /**
     * 消息发送者  
     */
    private Long              fromId;

    /**
     * 消息发送者名称
     */
    private String            fromName;
    /**
     *   
     */
    private String            message;

    /**
     * 消息状态：未读、已读等  
     */
    private Integer           status;

    /**
     * 消息类型：文本，表情，视频等  
     */
    private Integer           type;

    /**
     *   
     */
    private Date              createTime;

    /**
     *   
     */
    private Date              modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
