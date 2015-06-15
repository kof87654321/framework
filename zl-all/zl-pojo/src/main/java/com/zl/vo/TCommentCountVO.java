package com.zl.vo;

import java.io.Serializable;

import com.zl.pojo.TComment;

/**
 * 评论的count vo对象
 * @author youbush
 *
 */
public class TCommentCountVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2168896223981769326L;
	private long userFeedsId;//动态的主键id
	private int commentCount;//此动态有多少条评论

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
