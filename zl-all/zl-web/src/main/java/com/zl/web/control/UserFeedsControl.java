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

import com.alibaba.fastjson.JSONObject;
import com.zl.client.comment.CommentAndUserBiz;
import com.zl.client.comment.CommentService;
import com.zl.client.user.TUserService;
import com.zl.client.userFeeds.UserFeedsService;
import com.zl.common.util.Constant;
import com.zl.pojo.Page;
import com.zl.pojo.TComment;
import com.zl.pojo.TUser;
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
	 * 插入动态
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 */
	@RequestMapping("/postUserFeeds")
	@Security
	public void postUserFeeds(HttpServletRequest request, HttpServletResponse response
			,@RequestParam(value="title" ,required = true)String title 
			,@RequestParam(value="content" ,required = true)String content 
			,@RequestParam(value="imgs" ,required = true)String imgs ) {
		TUser currentUser = WebUtil.getCurrentUser(request);
		Long userId = currentUser.getId();
		TUserFeeds tUserFeeds = new TUserFeeds();
		tUserFeeds.setContent(request.getParameter("content"));
		tUserFeeds.setUserId(userId);
		tUserFeeds.setPraise(0);
		tUserFeeds.setTitle(title);
		tUserFeeds.setCommentCount(0);
		if(imgs != null){
			JSONObject attr = new JSONObject() ;
			tUserFeeds.setAttributes(attr.toString());   
		}
		tUserFeeds.setPageCount(HttpParamUtil.integerParam(request, "pageCount"));
		Long id = this.userFeedsService.insertTUserFeeds(tUserFeeds);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(id), response);
	}

	/**
	 * 插入某条动态的评论
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 */
	@RequestMapping("/postTcomment")
	@Security
	public void postTcomment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user2Id", required = false, defaultValue = "0") Long user2Id,
			@RequestParam(value = "userFeedsId", required = true, defaultValue = "0") Long userFeedsId,
			@RequestParam(value = "type", required = true, defaultValue = "0") Integer type,
			@RequestParam(value = "content", required = true, defaultValue = "") String content) {

		TUser currentUser = WebUtil.getCurrentUser(request);
		Long userId = currentUser.getId();
		
		if (user2Id == null || user2Id <= 0 || userFeedsId <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "PARAM_ERROR", Consts.ERRORCode.PARAM_ERROR), response);
			return;
		}

		TComment tComment = new TComment();
		tComment.setAuthorId(userId);
		tComment.setContent(content);
		tComment.setCreateTime(new Date());
		tComment.setModifyTime(new Date());
		tComment.setParentId(userFeedsId);
		tComment.setUserId(userId);
		tComment.setStatus(Constant.STATUS.NOMARL);
		tComment.setType(type);

		this.commentService.insertTComment(tComment);
		this.userFeedsService.addCommentCount(userFeedsId, 1);
		if (tComment.getId() != null && tComment.getId() > 0) {

		}

		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(tComment), response);
	}

	/**
	 * 首页列表
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @param modifyTime
	 * @param greaterThanOrEqualToPicCount
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/getHome")
	public void getHome(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "modifyTime", required = false, defaultValue = "0") Long modifyTime,
			@RequestParam(value = "greaterThanOrEqualToPicCount", required = false, defaultValue = "0") Integer greaterThanOrEqualToPicCount,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

		Page page = new Page();
		// int count = userFeedsService.getUserFeedsCount4TUserFeedsExample(0l,
		// greaterThanOrEqualToPicCount,
		// modifyTime);
		List<TUserFeeds> list = userFeedsService.getUserFeedsList4TUserFeedsExample(0l, greaterThanOrEqualToPicCount,
				modifyTime, page.setPageByPageNoAndPageSize(pageNo, pageSize));
		List<TUserFeedsVO> tUserFeedsVOList = new ArrayList<TUserFeedsVO>();
		if (list != null && list.size() > 0) {
			TUserVO tUserVO = null;
			TUserFeedsVO tUserFeedsVO = null;
			for (TUserFeeds tUserFeeds : list) {
				tUserVO = tUserService.getUserVOById(tUserFeeds.getUserId(), false, false);
				tUserFeedsVO = new TUserFeedsVO();
				tUserFeedsVO.settUserFeeds(tUserFeeds);
				tUserFeedsVO.settPostUserFeedsUserVO(tUserVO);
				tUserFeedsVOList.add(tUserFeedsVO);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tUserFeedsVOList", tUserFeedsVOList);
		// map.put("count", count);
		map.put("count", 0);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	/**
	 * 根据用户获取动态列表
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @param modifyTime
	 * @param greaterThanOrEqualToPicCount
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/getUserFeedsByUserId")
	public void getUserFeedsByUserId(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "modifyTime", required = false, defaultValue = "0") Long modifyTime,
			@RequestParam(value = "greaterThanOrEqualToPicCount", required = false, defaultValue = "0") Integer greaterThanOrEqualToPicCount,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

		if (userId == null || userId <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "userId is null", Consts.ERRORCode.USER_ID_ERROR),
					response);
			return;
		}

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
				tUserFeedsVO.settPostUserFeedsUserVO(tUserVO);
				tUserFeedsVOList.add(tUserFeedsVO);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tUserFeedsVOList", tUserFeedsVOList);
		map.put("count", count);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	/**
	 * 获取单条用户动态的对象，包含评论列表
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/getUserFeedsById")
	public void getUserFeedsVOById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true, defaultValue = "0") Long id,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

		if (id == null || id <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "id is null", Consts.ERRORCode.PARAM_ERROR), response);
			return;
		}

		Page page = new Page();
		List<TCommentAndUserVO> tCommentAndUserVOList = this.commentAndUserBiz.getTCommentAndUserVOListByUserFeedId(id,
				page.setPageByPageNoAndPageSize(pageNo, pageSize), false, true,false);
		// 当取单条用户动态的时候，则将所有评论至为已读
		// this.userFeedsService.updateStatus4TUserFeedsId(Constant.STATUS.CHECKED,Constant.STATUS.NO_CHECK,
		// id);
		TUserFeeds tUserFeeds = this.userFeedsService.getTUserFeedsById(id);
		TUserVO tUserVO = null;
		TUserFeedsVO tUserFeedsVO = null;
		tUserVO = tUserService.getUserVOById(tUserFeeds.getUserId(), false, false);
		tUserFeedsVO = new TUserFeedsVO();
		tUserFeedsVO.settUserFeeds(tUserFeeds);
		tUserFeedsVO.settPostUserFeedsUserVO(tUserVO);
		tUserFeedsVO.settCommentAndUserVOList(tCommentAndUserVOList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tUserFeedsVO", tUserFeedsVO);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	/**
	 * 点赞
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @param feedsId
	 */
	@RequestMapping("/addPraise")
	@Security
	public void addPraise(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "feedsId", required = true, defaultValue = "0") Long feedsId) {


		TUser currentUser = WebUtil.getCurrentUser(request);
		Long userId = currentUser.getId();
		
		TComment tComment = new TComment();
		tComment.setAuthorId(userId);
		tComment.setContent("");
		tComment.setCreateTime(new Date());
		tComment.setModifyTime(new Date());
		tComment.setParentId(feedsId);
		tComment.setUserId(userId);
		tComment.setStatus(Constant.STATUS.NOMARL);
		tComment.setType(2);

		this.commentService.insertTComment(tComment);
		int count = userFeedsService.addPraise(feedsId, 1);
		if (count > 0) {
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(true), response);
		} else {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "server error", Consts.ERRORCode.SERVER_ERROR), response);
		}

	}
	
	
	/**
	 * 根据用户获取动态列表
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @param modifyTime
	 * @param greaterThanOrEqualToPicCount
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/getTcommentByUserId")
	public void getTcommentByUserId(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "modifyTime", required = false, defaultValue = "0") Long modifyTime,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
		if (userId == null || userId <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "userId is null", Consts.ERRORCode.USER_ID_ERROR),
					response);
			return;
		}
		int count = this.commentService.getCountTComment4UserId(userId, modifyTime, null);
		List<TCommentAndUserVO> tCommentAndUserVOList = this.commentAndUserBiz.getListTComment4UserId(userId, modifyTime, null, new Page().setPageByPageNoAndPageSize(pageNo, pageSize));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tCommentAndUserVOList", tCommentAndUserVOList);
		map.put("count", count);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

}
