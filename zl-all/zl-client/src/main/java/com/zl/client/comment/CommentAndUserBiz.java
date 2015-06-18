package com.zl.client.comment;

import java.util.List;

import com.zl.pojo.Page;
import com.zl.vo.TCommentAndUserVO;

public interface CommentAndUserBiz {

	public List<TCommentAndUserVO> getTCommentAndUserVOListByUserFeedId(long userFeedsId, Page page,
			boolean showUserFeedsUerVO, boolean showTcommentUserVO,boolean showPraiseList);

}
