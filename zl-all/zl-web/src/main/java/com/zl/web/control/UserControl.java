package com.zl.web.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zl.client.user.TUserService;
import com.zl.client.userFeeds.UserFeedsService;
import com.zl.common.util.Constant;
import com.zl.pojo.Page;
import com.zl.pojo.TUser;
import com.zl.pojo.TUserFeeds;
import com.zl.pojo.TUserInfo;
import com.zl.pojo.TUserProfile;
import com.zl.vo.TUserVO;
import com.zl.web.annotation.Security;
import com.zl.web.app.Consts;
import com.zl.web.app.util.HttpParamUtil;
import com.zl.web.app.util.ValidCodeUtil;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

@Controller
@RequestMapping("/user")
public class UserControl {
	private static final Logger log = LoggerFactory.getLogger(UserControl.class);

	@Autowired
	private TUserService tUserService;

	@Autowired
	private UserFeedsService userFeedsService;

	/**
	 * 获取最新版本，通过浏览器{host:port}/user/getMyPage.htm?userId=1&bigId=0&pageNo=1&
	 * pageSize=10&token=888进行访问
	 * 
	 */
	@RequestMapping("/getMyPage")
	@Security
	public void myPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "modifyTime", required = false, defaultValue = "0") Long modifyTime,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize) {
		if (userId == null) {
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response);
			return;
		}

		TUserVO tUserVO = tUserService.getUserVOById(userId, true, false);
		// TODO 是否还缺少某个用户看过另一个用户的内容
		int noReadFeedsCount = userFeedsService.getUserFeedsCount4TUserFeedsExample(userId, 0, modifyTime);
		List<TUserFeeds> listFeeds = null;
		if (noReadFeedsCount > 0) {
			Page page = new Page();
			page.setBegin(pageNo);
			page.setLength(pageSize);
			listFeeds = userFeedsService.getUserFeedsList4TUserFeedsExample(userId, 1, modifyTime, page);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tUserVO", tUserVO);
		map.put("noReadFeedsCount", noReadFeedsCount);
		map.put("list", listFeeds);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	@RequestMapping("/searchUserList")
	@Security
	public void searchUserList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = false, defaultValue = "0") Long userId,
			@RequestParam(value = "area", required = false, defaultValue = "0") Integer area,
			@RequestParam(value = "industry", required = false, defaultValue = "0") Integer industry,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
		Page page = new Page();
		List<TUserVO> listSearchTUserVO = this.tUserService.getListByAreaAndIndustry(area, industry,
				page.setPageByPageNoAndPageSize(pageNo, pageSize));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listSearchTUserVO", listSearchTUserVO);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	@RequestMapping("/registerUser")
	public void registerUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userName", required = true, defaultValue = "0") String userName,
			@RequestParam(value = "passWord", required = true, defaultValue = "0") String passWord,
			@RequestParam(value = "valid", required = true, defaultValue = "0") String valid,
			@RequestParam(value = "mobile", required = true, defaultValue = "0") String mobile) {

		boolean checkValid = ValidCodeUtil.checkValid(mobile, valid);
		if (checkValid == false) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "valid error", Consts.ERRORCode.VALID_ERROR), response);
			return;
		}

		TUserVO tUserVO = new TUserVO();
		TUser tUser = new TUser();
		tUser.setLastLoginTime(new Date());
		tUser.setStatus(Constant.STATUS.NOMARL);
		tUser.setPassword(passWord);
		tUser.setUserName(userName);
		tUser.setInviteCode(request.getParameter("inviteCode"));

		TUserInfo tUserInfo = new TUserInfo();

		tUserInfo.setArea(HttpParamUtil.integerParam(request, "area"));
		tUserInfo.setBackground(request.getParameter("background"));
		tUserInfo.setDescription(request.getParameter("description"));
		tUserInfo.setEmail(request.getParameter("email"));
		tUserInfo.setFaceImg(request.getParameter("faceImg"));
		tUserInfo.setFriends(0);
		tUserInfo.setIndustry(HttpParamUtil.integerParam(request, "industry"));
		tUserInfo.setMobile(request.getParameter("mobile"));
		tUserInfo.setNickName(request.getParameter("nickName"));
		tUserInfo.setPraise(0);
		tUserInfo.setQrCode(request.getParameter("qrCode"));
		tUserInfo.setSex(HttpParamUtil.booleanParam(request, "sex"));
		tUserInfo.setTitle(request.getParameter("title"));

		tUserVO.settUser(tUser);
		tUserVO.settUserInfo(tUserInfo);
		tUserVO.settUserProfileList(null);

		TUserVO returnTUserVO = this.tUserService.insertUser(tUserVO);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(returnTUserVO), response);
	}

	@RequestMapping("/updateUser")
	@Security
	public void updateUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "userName", required = true, defaultValue = "0") String userName,
			@RequestParam(value = "passWord", required = true, defaultValue = "0") String passWord) {

		if (userId == null || userId <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "userId is null", Consts.ERRORCode.USER_ID_ERROR),
					response);
			return;
		}
		TUserVO tUserVO = this.tUserService.getUserVOById(userId, false, false);
		TUser tUser = tUserVO.gettUser();
		tUser.setLastLoginTime(new Date());
		tUser.setPassword(passWord);
		tUser.setUserName(userName);

		TUserInfo tUserInfo = tUserVO.gettUserInfo();

		tUserInfo.setArea(HttpParamUtil.integerParam(request, "area"));
		if (StringUtils.isNotBlank(request.getParameter("background"))) {
			tUserInfo.setBackground(request.getParameter("background"));
		}
		// tUserInfo.setBackground(request.getParameter("background"));
		tUserInfo.setDescription(request.getParameter("description"));
		tUserInfo.setEmail(request.getParameter("email"));
		if (StringUtils.isNotBlank(request.getParameter("faceImg"))) {
			tUserInfo.setFaceImg(request.getParameter("faceImg"));
		}
		// tUserInfo.setFaceImg(request.getParameter("faceImg"));
		// tUserInfo.setFriends(0);
		tUserInfo.setIndustry(HttpParamUtil.integerParam(request, "industry"));
		tUserInfo.setMobile(request.getParameter("mobile"));
		tUserInfo.setNickName(request.getParameter("nickName"));
		// tUserInfo.setPraise(0);
		if (StringUtils.isNotBlank(request.getParameter("qrCode"))) {
			request.getParameter("qrCode");
		}
		// tUserInfo.setQrCode(request.getParameter("qrCode"));
		tUserInfo.setSex(HttpParamUtil.booleanParam(request, "sex"));
		tUserInfo.setTitle(request.getParameter("title"));

		tUserVO.settUser(tUser);
		tUserVO.settUserInfo(tUserInfo);
		tUserVO.settUserProfileList(null);

		TUserVO returnTUserVO = this.tUserService.updateUser(tUserVO, false);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(returnTUserVO), response);
	}

	@RequestMapping("/insertUserProfile")
	@Security
	public void insertUserProfile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId) {

		List<TUserProfile> tUserProfileList = new ArrayList<TUserProfile>();
		TUserProfile tUserProfile = new TUserProfile();
		tUserProfile.setCompany(request.getParameter("company"));
		tUserProfile.setIntroduce(request.getParameter("introduce"));
		tUserProfile.setJobEndTime(HttpParamUtil.integerParam(request, request.getParameter("jobEndTime")));
		tUserProfile.setJobStartTime(HttpParamUtil.integerParam(request, request.getParameter("jobStartTime")));
		tUserProfile.setModifyTime(new Date());
		tUserProfile.setPosition(request.getParameter("position"));
		tUserProfile.setUserId(userId);
		tUserProfile.setStatus(Constant.STATUS.NOMARL);
		tUserProfile.setModifyTime(new Date());
		tUserProfile.setCreateTime(new Date());
		tUserProfileList.add(tUserProfile);
		Long id = this.tUserService.insertTUserProfile(tUserProfileList, userId);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(id), response);

	}

	@RequestMapping("/updateUserProfile")
	@Security
	public void updateUserProfile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "id", required = true, defaultValue = "0") Long id) {

		if (userId <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "userId is null", Consts.ERRORCode.USER_ID_ERROR),
					response);
			return;
		}

		// List<TUserProfile> list =
		// this.tUserService.getTUserProfileList(userId, id, null);`

		List<TUserProfile> tUserProfileList = this.tUserService.getTUserProfileList(userId, id, null);
		if (tUserProfileList == null || tUserProfileList.size() <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "id is null", Consts.ERRORCode.BIZ_ID_ERROR), response);
			return;
		}
		TUserProfile tUserProfile = tUserProfileList.get(0);
		tUserProfile.setCompany(request.getParameter("company"));
		tUserProfile.setIntroduce(request.getParameter("introduce"));
		tUserProfile.setJobEndTime(HttpParamUtil.integerParam(request, request.getParameter("jobEndTime")));
		tUserProfile.setJobStartTime(HttpParamUtil.integerParam(request, request.getParameter("jobStartTime")));
		tUserProfile.setModifyTime(new Date());
		tUserProfile.setPosition(request.getParameter("position"));
		tUserProfile.setUserId(userId);
		tUserProfile.setId(id);

		List<TUserProfile> tUserProfileListDB = new ArrayList<TUserProfile>();
		tUserProfileListDB.add(tUserProfile);

		this.tUserService.insertTUserProfile(tUserProfileListDB, userId);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(id), response);

	}

	@RequestMapping("/deleteUserProfileById")
	@Security
	public void deleteUserProfileById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "id", required = true, defaultValue = "0") Long id) {

		if (userId <= 0 || id <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "userId is null", Consts.ERRORCode.PARAM_ERROR), response);
			return;
		}

		// List<TUserProfile> list =
		// this.tUserService.getTUserProfileList(userId, id, null);`

		List<TUserProfile> tUserProfileList = this.tUserService.getTUserProfileList(userId, id, null);
		if (tUserProfileList == null || tUserProfileList.size() <= id) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "id is null", Consts.ERRORCode.BIZ_ID_ERROR), response);
			return;
		}

		int count = this.tUserService.deleteTUserProfileByIdAndUserId(userId, id);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(count), response);

	}

}
