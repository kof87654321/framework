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

	/*
	 * showPraiseList 是否查点赞list showTcommentUserVO 是否显示评论的用户vo
	 * showUserFeedsUerVO 是否显示动态的用户vo (non-Javadoc)
	 * 
	 * @see
	 * com.zl.client.comment.CommentAndUserBiz#getTCommentAndUserVOListByUserFeedId
	 * (long, com.zl.pojo.Page, boolean, boolean, boolean)
	 */
	@Override
	public List<TCommentAndUserVO> getTCommentAndUserVOListByUserFeedId(long userFeedsId, Page page,
			boolean showUserFeedsUerVO, boolean showTcommentUserVO, boolean showPraiseList) {
		List<TCommentAndUserVO> tCommentAndUserVOList = new ArrayList<TCommentAndUserVO>();
		Integer[] types = null;
		if (showPraiseList == false) {
			types = new Integer[] { 0, 1 };
		}
		List<TComment> tCommentList = this.commentService.getListTComment4UserFeedsId(userFeedsId, types, page);
		if (tCommentList == null || tCommentList.size() <= 0) {
			return tCommentAndUserVOList;
		}

		getTCommentAndUserVOByTCommentList(tCommentList);

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
				TUserVO tReviceUserVO = this.tUserService.getUserVOById(tComment.getAuthorId(), false, false);
				tCommentAndUserVO.settReviceUserVO(tReviceUserVO);
			}
			tCommentAndUserVO.settComment(tComment);
			tCommentAndUserVO.settUserFeedsUserVO(tUserFeedsUserVO);
			tCommentAndUserVOList.add(tCommentAndUserVO);
		}
		return tCommentAndUserVOList;
	}

	private void getTCommentAndUserVOByTCommentList(List<TComment> tCommentList) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TCommentAndUserVO> getListTComment4UserId(long userId, long modifyTime, Integer[] types, Page page) {
		List<TCommentAndUserVO> tCommentAndUserVOList = new ArrayList<TCommentAndUserVO>();
		List<TComment> tCommentList = this.commentService.getListTComment4UserId(userId, modifyTime, types, page);
		if (tCommentList == null || tCommentList.size() <= 0) {
			return tCommentAndUserVOList;
		}
		TUserVO tUserFeedsUserVO = this.tUserService.getUserVOById(userId, false, false);
		TCommentAndUserVO tCommentAndUserVO = null;
		for (TComment tComment : tCommentList) {
			tCommentAndUserVO = new TCommentAndUserVO();
			TUserVO tCommentPostUserVO = this.tUserService.getUserVOById(tComment.getUserId(), false, false);
			tCommentAndUserVO.settCommentPostUserVO(tCommentPostUserVO);
			tCommentAndUserVO.settComment(tComment);
			tCommentAndUserVO.settUserFeedsUserVO(tUserFeedsUserVO);
			tCommentAndUserVOList.add(tCommentAndUserVO);
		}
		return tCommentAndUserVOList;
	}

}
