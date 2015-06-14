package com.zl.vo;

import java.io.Serializable;

import com.zl.pojo.TComment;

public class TCommentAndUserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5368212166716750946L;

	private TComment tComment;
	private TUserVO tUserFeedsUserVO;// 朋友圈内容的发布者
	private TUserVO tCommentPostUserVO;//评论发布者

	public TComment gettComment() {
		return tComment;
	}

	public void settComment(TComment tComment) {
		this.tComment = tComment;
	}

	public TUserVO gettUserFeedsUserVO() {
		return tUserFeedsUserVO;
	}

	public void settUserFeedsUserVO(TUserVO tUserFeedsUserVO) {
		this.tUserFeedsUserVO = tUserFeedsUserVO;
	}

	public TUserVO gettCommentPostUserVO() {
		return tCommentPostUserVO;
	}

	public void settCommentPostUserVO(TUserVO tCommentPostUserVO) {
		this.tCommentPostUserVO = tCommentPostUserVO;
	}

}
