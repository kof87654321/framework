package com.zl.common.util.token;

import java.util.Date;

/**
 * token中包含的信息
 * @author is_zhoufeng
 */
public class TokenInfo {
	
	private Long userId ;
	
	private String password ;
	
	private Date lastLoginTime ;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
}
