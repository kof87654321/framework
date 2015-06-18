package com.zl.client.comment;

import java.util.List;

import com.zl.pojo.Page;
import com.zl.pojo.TComment;
import com.zl.vo.TCommentCountVO;

public interface CommentService {
	public TComment insertTComment(TComment tComment);

	public List<TComment> getListTComment4UserFeedsId(long userFeedsId, Integer[] types ,Page page);

	public int getCountTComment4UserFeedsIds(long userFeedsId);

	// public int getCountTComment4UserFeedsIds(long userFeedsId);

	public List<TCommentCountVO> getTCommentVOList4UserFeedsIds(Long... userFeedsIds);
}
