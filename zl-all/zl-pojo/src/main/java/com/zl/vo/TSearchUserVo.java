package com.zl.vo;

import com.zl.pojo.TUserFeeds;

/**
 * 搜索用户结果
 * @author is_zhoufeng
 *
 */
public class TSearchUserVo extends TUserVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -768975407829593928L;
	
	private TUserFeeds userFeed ;

	public TUserFeeds getUserFeed() {
		return userFeed;
	}

	public void setUserFeed(TUserFeeds userFeed) {
		this.userFeed = userFeed;
	}
	
}
