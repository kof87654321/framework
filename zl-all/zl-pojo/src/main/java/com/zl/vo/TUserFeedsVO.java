package com.zl.vo;

import java.io.Serializable;
import java.util.List;

import com.zl.pojo.TComment;
import com.zl.pojo.TUserFeeds;

public class TUserFeedsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8125138207651561538L;
	private TUserFeeds tUserFeeds;
	private List<TCommentAndUserVO> tCommentAndUserVOList;
	private TUserVO tUserVO;

	public TUserFeeds gettUserFeeds() {
		return tUserFeeds;
	}

	public void settUserFeeds(TUserFeeds tUserFeeds) {
		this.tUserFeeds = tUserFeeds;
	}

	public TUserVO gettUserVO() {
		return tUserVO;
	}

	public void settUserVO(TUserVO tUserVO) {
		this.tUserVO = tUserVO;
	}

	public List<TCommentAndUserVO> gettCommentAndUserVOList() {
		return tCommentAndUserVOList;
	}

	public void settCommentAndUserVOList(
			List<TCommentAndUserVO> tCommentAndUserVOList) {
		this.tCommentAndUserVOList = tCommentAndUserVOList;
	}

}
