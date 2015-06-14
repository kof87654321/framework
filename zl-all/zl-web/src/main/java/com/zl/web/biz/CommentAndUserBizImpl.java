package com.zl.web.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.comment.CommentAndUserBiz;
import com.zl.client.comment.CommentService;
import com.zl.client.user.TUserService;
import com.zl.client.userFeeds.UserFeedsService;
import com.zl.pojo.Page;
import com.zl.pojo.TComment;
import com.zl.pojo.TUserFeeds;
import com.zl.vo.TCommentAndUserVO;
import com.zl.vo.TUserVO;

@Service
public class CommentAndUserBizImpl implements CommentAndUserBiz {

	@Autowired
	private UserFeedsService userFeedsService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private TUserService tUserService;

	@Override
	public List<TCommentAndUserVO> getTCommentAndUserVOListByUserFeedId(long userFeedsId, Page page,
			boolean showUserFeedsUerVO, boolean showTcommentUserVO) {
		List<TCommentAndUserVO> tCommentAndUserVOList = new ArrayList<TCommentAndUserVO>();
		List<TComment> tCommentList = this.commentService.getListTComment4UserFeedsId(userFeedsId, page);
		if (tCommentList == null || tCommentList.size() <= 0) {
			return tCommentAndUserVOList;
		}

		TUserFeeds tUserFeeds = this.userFeedsService.getTUserFeedsById(userFeedsId);
		if (tUserFeeds == null) {
			return tCommentAndUserVOList;
		}
		TUserVO tUserFeedsUserVO = null;
		if (showUserFeedsUerVO) {
			tUserFeedsUserVO = this.tUserService.getUserVOById(tUserFeeds.getUserId(), false, false);
		}

		TCommentAndUserVO tCommentAndUserVO = null;
		for (TComment tComment : tCommentList) {
			tCommentAndUserVO = new TCommentAndUserVO();
			if (showTcommentUserVO) {
				TUserVO tCommentPostUserVO = this.tUserService.getUserVOById(tComment.getUserId(), false, false);
				tCommentAndUserVO.settCommentPostUserVO(tCommentPostUserVO);
			}
			tCommentAndUserVO.settUserFeedsUserVO(tUserFeedsUserVO);
			tCommentAndUserVOList.add(tCommentAndUserVO);
		}
		return tCommentAndUserVOList;
	}

}
