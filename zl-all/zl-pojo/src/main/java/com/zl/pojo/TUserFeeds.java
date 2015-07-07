package com.zl.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * tableName{T_User_Feeds}
 * by is_zhoufeng@163.com 2015-07-07 12:49:50
 */
public class TUserFeeds implements Serializable {
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
     *   
     * column{Title},jdbcType{VARCHAR}
     */
    private String title;

    /**
     * 信息内容  
     * column{Content},jdbcType{VARCHAR}
     */
    private String content;

    /**
     * 信息：json格式  
     * column{Attributes},jdbcType{VARCHAR}
     */
    private String attributes;

    /**
     *   
     * column{Praise},jdbcType{INTEGER}
     */
    private Integer praise;

    /**
     * 图片数  
     * column{PageCount},jdbcType{INTEGER}
     */
    private Integer pageCount;

    /**
     * 评论数量  
     * column{CommentCount},jdbcType{INTEGER}
     */
    private Integer commentCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
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
     *   
     * column{Title},jdbcType{VARCHAR}
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 信息内容  
     * column{Content},jdbcType{VARCHAR}
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 信息：json格式  
     * column{Attributes},jdbcType{VARCHAR}
     */
    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    /**
     *   
     * column{Praise},jdbcType{INTEGER}
     */
    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    /**
     * 图片数  
     * column{PageCount},jdbcType{INTEGER}
     */
    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 评论数量  
     * column{CommentCount},jdbcType{INTEGER}
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}