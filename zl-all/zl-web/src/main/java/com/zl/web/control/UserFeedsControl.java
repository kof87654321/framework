package com.zl.web.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zl.client.comment.CommentAndUserBiz;
import com.zl.client.comment.CommentService;
import com.zl.client.user.TUserService;
import com.zl.client.userFeeds.UserFeedsService;
import com.zl.pojo.Page;
import com.zl.pojo.TComment;
import com.zl.pojo.TUserFeeds;
import com.zl.vo.TCommentAndUserVO;
import com.zl.vo.TUserFeedsVO;
import com.zl.vo.TUserVO;
import com.zl.web.annotation.Security;
import com.zl.web.app.Consts;
import com.zl.web.app.util.HttpParamUtil;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

@Controller
@RequestMapping("/userFeeds")
public class UserFeedsControl {

	private static final Logger log = LoggerFactory.getLogger(UserFeedsControl.class);

	@Autowired
	private UserFeedsService userFeedsService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private TUserService tUserService;

	@Autowired
	private CommentAndUserBiz commentAndUserBiz;

	/**
	 * 插入
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 */
	@RequestMapping("/postUserFeeds")
	@Security
	public void postUserFeeds(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId) {

		TUserFeeds tUserFeeds = new TUserFeeds();
		tUserFeeds.setContent(request.getParameter("content"));
		tUserFeeds.setUserId(userId);
		tUserFeeds.setPraise(0);
		tUserFeeds.setPageCount(HttpParamUtil.integerParam(request, "pageCount"));
		Long id = this.userFeedsService.insertTUserFeeds(tUserFeeds);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(id), response);
	}

	/**
	 * 插入评论
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 */
	@RequestMapping("/postTcomment")
	@Security
	public void postTcomment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "user2Id", required = true, defaultValue = "0") Long user2Id,
			@RequestParam(value = "userFeedsId", required = true, defaultValue = "0") Long userFeedsId,
			@RequestParam(value = "content", required = true, defaultValue = "") String content) {

		TComment tComment = new TComment();
		tComment.setAuthorId(userId);
		tComment.setContent(content);
		tComment.setCreateTime(new Date());
		tComment.setModifyTime(new Date());
		tComment.setParentId(userFeedsId);
		tComment.setUserId(userId);

		this.commentService.insertTComment(tComment);
		if (tComment.getId() != null && tComment.getId() > 0) {

		}

		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(tComment), response);
	}

	@RequestMapping("/getUserFeedsByUserId")
	@Security
	public void getUserFeedsByUserId(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "modifyTime", required = false, defaultValue = "0") Long modifyTime,
			@RequestParam(value = "greaterThanOrEqualToPicCount", required = false, defaultValue = "0") Integer greaterThanOrEqualToPicCount,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

		Page page = new Page();
		int count = userFeedsService.getUserFeedsCount4TUserFeedsExample(userId, greaterThanOrEqualToPicCount,
				modifyTime);
		List<TUserFeeds> list = userFeedsService.getUserFeedsList4TUserFeedsExample(userId,
				greaterThanOrEqualToPicCount, modifyTime, page.setPageByPageNoAndPageSize(pageNo, pageSize));
		List<TUserFeedsVO> tUserFeedsVOList = new ArrayList<TUserFeedsVO>();
		if (list != null && list.size() > 0) {
			TUserVO tUserVO = null;
			TUserFeedsVO tUserFeedsVO = null;
			for (TUserFeeds tUserFeeds : list) {
				tUserVO = tUserService.getUserVOById(userId, false, false);
				tUserFeedsVO = new TUserFeedsVO();
				tUserFeedsVO.settUserFeeds(tUserFeeds);
				tUserFeedsVO.settUserVO(tUserVO);
				tUserFeedsVOList.add(tUserFeedsVO);
			}
		}

		/*
		 * List<TUserFeedsVO> tUserFeedsVOList = new ArrayList<TUserFeedsVO>();
		 * if (list == null || list.size() <= 0) { } else { int i = 0; Long[]
		 * userFeedsIds = new Long[list.size()]; for (TUserFeeds tUserFeeds :
		 * list) { userFeedsIds[i] = tUserFeeds.getId(); i++; } List<TCommentVO>
		 * commentVOList =
		 * this.commentService.getTCommentVOList4UserFeedsIds(userFeedsIds); if
		 * (commentVOList != null && commentVOList.size() > 0) { TUserFeedsVO
		 * tUserFeedsVO = null; for (TUserFeeds tUserFeeds : list) {
		 * for(TCommentVO tCommentVO : commentVOList){ if
		 * (tUserFeeds.getId().longValue() == tCommentVO.getUserFeedsId()) {
		 * tUserFeedsVO = new TUserFeedsVO();
		 * tUserFeedsVO.settCommentVO(tCommentVO);
		 * tUserFeedsVO.settUserFeeds(tUserFeeds);
		 * tUserFeedsVOList.add(tUserFeedsVO); } } } }
		 * 
		 * }
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tUserFeedsVOList", tUserFeedsVOList);
		map.put("count", count);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	@RequestMapping("/getUserFeedsById")
	@Security
	public void getUserFeedsVOById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true, defaultValue = "0") Long id,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

		Page page = new Page();
		List<TCommentAndUserVO> tCommentAndUserVOList = this.commentAndUserBiz.getTCommentAndUserVOListByUserFeedId(id,
				page.setPageByPageNoAndPageSize(pageNo, pageSize), false, true);

		TUserFeeds tUserFeeds = this.userFeedsService.getTUserFeedsById(id);
		TUserVO tUserVO = null;
		TUserFeedsVO tUserFeedsVO = null;
		tUserVO = tUserService.getUserVOById(tUserFeeds.getUserId(), false, false);
		tUserFeedsVO = new TUserFeedsVO();
		tUserFeedsVO.settUserFeeds(tUserFeeds);
		tUserFeedsVO.settUserVO(tUserVO);
		tUserFeedsVO.settCommentAndUserVOList(tCommentAndUserVOList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tUserFeedsVO", tUserFeedsVO);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	@RequestMapping("/addPraise")
	@Security
	public void addPraise(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "feedsId", required = true, defaultValue = "0") Long feedsId) {

		int count = userFeedsService.addPraise(feedsId, 1);
		if (count > 0) {
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(true), response);
		} else {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "server error", Consts.ERRORCode.SERVER_ERROR), response);
		}

	}

	/*
	 * @RequestMapping("/getUserFeeds4HomePage")
	 * 
	 * @Security public void getUserFeeds4HomePage( HttpServletRequest request,
	 * HttpServletResponse response,
	 * 
	 * @RequestParam(value = "userId", required = false, defaultValue = "0")
	 * Long userId,
	 * 
	 * @RequestParam(value = "modifyTime", required = false, defaultValue = "0")
	 * Long modifyTime,
	 * 
	 * @RequestParam(value = "greaterThanOrEqualToPicCount", required = false,
	 * defaultValue = "0") Integer greaterThanOrEqualToPicCount,
	 * 
	 * @RequestParam(value = "pageNo", required = false, defaultValue = "1")
	 * Integer pageNo,
	 * 
	 * @RequestParam(value = "pageSize", required = false, defaultValue = "20")
	 * Integer pageSize) {
	 * 
	 * Page page = new Page(); int count =
	 * userFeedsService.getUserFeedsCount4TUserFeedsExample(userId,
	 * greaterThanOrEqualToPicCount, modifyTime); List<TUserFeeds> list =
	 * userFeedsService.getUserFeedsList4TUserFeedsExample(userId,
	 * greaterThanOrEqualToPicCount, modifyTime,
	 * page.setPageByPageNoAndPageSize(pageNo, pageSize)); List<TUserFeedsVO>
	 * tUserFeedsVOList = new ArrayList<TUserFeedsVO>(); if (list != null &&
	 * list.size()>0) { TUserVO tUserVO = null; TUserFeedsVO tUserFeedsVO =
	 * null;
	 * 
	 * int i = 0; Long[] userFeedsIds = new Long[list.size()]; for (TUserFeeds
	 * tUserFeeds : list) { userFeedsIds[i] = tUserFeeds.getId(); i++; }
	 * List<TCommentVO> commentVOList =
	 * this.commentService.getTCommentVOList4UserFeedsIds(userFeedsIds);
	 * 
	 * for (TUserFeeds tUserFeeds : list) { tUserVO =
	 * tUserService.getUserVOById(userId, false, false); tUserFeedsVO = new
	 * TUserFeedsVO(); if (commentVOList != null && commentVOList.size() > 0) {
	 * for(TCommentVO tCommentVO : commentVOList){ if
	 * (tUserFeeds.getId().longValue() == tCommentVO.getUserFeedsId()) {
	 * tUserFeedsVO.settCommentVO(tCommentVO); continue; } } }
	 * 
	 * tUserFeedsVO.settUserFeeds(tUserFeeds); tUserFeedsVO.settUserVO(tUserVO);
	 * tUserFeedsVOList.add(tUserFeedsVO); } }
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>();
	 * map.put("tUserFeedsVOList", tUserFeedsVOList); map.put("count", count);
	 * WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response); }
	 */

}
