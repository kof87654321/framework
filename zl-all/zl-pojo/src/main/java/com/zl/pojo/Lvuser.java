package com.zl.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * tableName{lvuser}
 * by is_zhoufeng@163.com 2015-06-01 22:28:29
 */
public class Lvuser implements Serializable {
    /**
     *   
     * column{id},jdbcType{BIGINT}
     */
    private Long id;

    /**
     * 登录名  
     * column{loginname},jdbcType{VARCHAR}
     */
    private String loginname;

    /**
     * 昵称  
     * column{nick},jdbcType{VARCHAR}
     */
    private String nick;

    /**
     * 靓号  
     * column{idxcode},jdbcType{VARCHAR}
     */
    private String idxcode;

    /**
     * 密码  
     * column{password},jdbcType{VARCHAR}
     */
    private String password;

    /**
     * 用户钢镚数量  
     * column{coin},jdbcType{BIGINT}
     */
    private Long coin;

    /**
     * 用户被赞次数  
     * column{praise},jdbcType{BIGINT}
     */
    private Long praise;

    /**
     * 用户头像地址（相对地址）  
     * column{photo},jdbcType{VARCHAR}
     */
    private String photo;

    /**
     * 用户邮箱  
     * column{mail},jdbcType{VARCHAR}
     */
    private String mail;

    /**
     * 用户电话  
     * column{phone},jdbcType{VARCHAR}
     */
    private String phone;

    /**
     * 用户QQ帐号  
     * column{qq},jdbcType{VARCHAR}
     */
    private String qq;

    /**
     * 扩展标位  
     * column{flag},jdbcType{INTEGER}
     */
    private Integer flag;

    /**
     * 用户状态，1：启用，2：禁用  
     * column{status},jdbcType{TINYINT}
     */
    private Byte status;

    /**
     * 是否是第三方用户，0：不是，1：是  
     * column{thirduser},jdbcType{TINYINT}
     */
    private Byte thirduser;

    /**
     * 创建时间  
     * column{createtime},jdbcType{TIMESTAMP}
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     *   
     * column{id},jdbcType{BIGINT}
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 登录名  
     * column{loginname},jdbcType{VARCHAR}
     */
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * 昵称  
     * column{nick},jdbcType{VARCHAR}
     */
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * 靓号  
     * column{idxcode},jdbcType{VARCHAR}
     */
    public String getIdxcode() {
        return idxcode;
    }

    public void setIdxcode(String idxcode) {
        this.idxcode = idxcode;
    }

    /**
     * 密码  
     * column{password},jdbcType{VARCHAR}
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户钢镚数量  
     * column{coin},jdbcType{BIGINT}
     */
    public Long getCoin() {
        return coin;
    }

    public void setCoin(Long coin) {
        this.coin = coin;
    }

    /**
     * 用户被赞次数  
     * column{praise},jdbcType{BIGINT}
     */
    public Long getPraise() {
        return praise;
    }

    public void setPraise(Long praise) {
        this.praise = praise;
    }

    /**
     * 用户头像地址（相对地址）  
     * column{photo},jdbcType{VARCHAR}
     */
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 用户邮箱  
     * column{mail},jdbcType{VARCHAR}
     */
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * 用户电话  
     * column{phone},jdbcType{VARCHAR}
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 用户QQ帐号  
     * column{qq},jdbcType{VARCHAR}
     */
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 扩展标位  
     * column{flag},jdbcType{INTEGER}
     */
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 用户状态，1：启用，2：禁用  
     * column{status},jdbcType{TINYINT}
     */
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 是否是第三方用户，0：不是，1：是  
     * column{thirduser},jdbcType{TINYINT}
     */
    public Byte getThirduser() {
        return thirduser;
    }

    public void setThirduser(Byte thirduser) {
        this.thirduser = thirduser;
    }

    /**
     * 创建时间  
     * column{createtime},jdbcType{TIMESTAMP}
     */
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}