package com.zl.vo;

import java.io.Serializable;
import java.util.List;

import com.zl.pojo.TUser;
import com.zl.pojo.TUserInfo;
import com.zl.pojo.TUserProfile;

/**
 * 用户vo对象 用于结合TUser TUserInfo TUserProfile 前台显示使用
 * 
 * @author youbush
 *
 */
public class TUserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2777905243908846977L;

	private TUser tUser;
	private TUserInfo tUserInfo;
	private List<TUserProfile> tUserProfileList;

	public TUser gettUser() {
		return tUser;
	}

	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}

	public TUserInfo gettUserInfo() {
		return tUserInfo;
	}

	public void settUserInfo(TUserInfo tUserInfo) {
		this.tUserInfo = tUserInfo;
	}

	public List<TUserProfile> gettUserProfileList() {
		return tUserProfileList;
	}

	public void settUserProfileList(List<TUserProfile> tUserProfileList) {
		this.tUserProfileList = tUserProfileList;
	}

}
