package com.zl.vo;

import java.io.Serializable;
import java.util.List;

import com.zl.pojo.TComment;
import com.zl.pojo.TUserFeeds;

/**
 * 动态vo
 * @author youbush
 *
 */
public class TUserFeedsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8125138207651561538L;
	private TUserFeeds tUserFeeds; //动态的内容
	private List<TCommentAndUserVO> tCommentAndUserVOList; //动态里评论者的基本信息
	private TUserVO tPostUserFeedsUserVO;//发布动态的用户信息

	public TUserFeeds gettUserFeeds() {
		return tUserFeeds;
	}

	public void settUserFeeds(TUserFeeds tUserFeeds) {
		this.tUserFeeds = tUserFeeds;
	}

	public List<TCommentAndUserVO> gettCommentAndUserVOList() {
		return tCommentAndUserVOList;
	}

	public void settCommentAndUserVOList(
			List<TCommentAndUserVO> tCommentAndUserVOList) {
		this.tCommentAndUserVOList = tCommentAndUserVOList;
	}

	public TUserVO gettPostUserFeedsUserVO() {
		return tPostUserFeedsUserVO;
	}

	public void settPostUserFeedsUserVO(TUserVO tPostUserFeedsUserVO) {
		this.tPostUserFeedsUserVO = tPostUserFeedsUserVO;
	}

}
