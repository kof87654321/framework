package com.zl.vo;

import java.io.Serializable;

import com.zl.pojo.TComment;

public class TCommentCountVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2168896223981769326L;
	private long userFeedsId;
	private int commentCount;

	public long getUserFeedsId() {
		return userFeedsId;
	}

	public void setUserFeedsId(long userFeedsId) {
		this.userFeedsId = userFeedsId;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

}
