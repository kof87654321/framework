package com.zl.web.control;

import java.text.SimpleDateFormat;
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

import com.zl.client.invite.InviteCodeService;
import com.zl.client.user.TUserService;
import com.zl.client.userFeeds.UserFeedsService;
import com.zl.common.util.Constant;
import com.zl.pojo.Page;
import com.zl.pojo.TInviteCode;
import com.zl.pojo.TUser;
import com.zl.pojo.TUserFeeds;
import com.zl.pojo.TUserInfo;
import com.zl.pojo.TUserProfile;
import com.zl.vo.TSearchUserVo;
import com.zl.vo.TUserVO;
import com.zl.web.annotation.Security;
import com.zl.web.app.Consts;
import com.zl.web.app.util.HttpParamUtil;
import com.zl.web.app.util.ValidCodeUtil;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * 用户页面入口
 * 
 * @author youbush
 *
 */
@Controller
@RequestMapping("/user")
public class UserControl {
	private static final Logger log = LoggerFactory.getLogger(UserControl.class);

	@Autowired
	/* 用户service */private TUserService tUserService;

	@Autowired
	/* 用户动态service */private UserFeedsService userFeedsService;

	@Autowired
	private InviteCodeService inviteCodeService ;

	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YYYY");



	/**
	 * 根据用户Id批量获取用户信息
	 * 通过浏览器{host:port}/user/getMyPage.htm?userId=1&bigId=0&pageNo=1&
	 * pageSize=10&token=888进行访问
	 * 
	 */
	@RequestMapping("/getUsersBaseInfo")
	public void getUsersBaseInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userNames", required = true, defaultValue = "0") String userNames){
		String[] userNamesArray = userNames.split(",");
		if(userNamesArray.length <= 0){
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "mobiles is null", -1), response);
			return ;
		}
		List<String> userNamesLong = new ArrayList<String>() ;
		for (String userNameString : userNamesArray) {
			userNamesLong.add(userNameString) ; 
		}
		List<TUserVO> userVoList = tUserService.getUserBaseInfoByUserNames(userNamesLong) ;  
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(userVoList), response); 
	}


	/**
	 * 􏰢􏰐􏰢􏰐个人中心页面调用http接口
	 * 通过浏览器{host:port}/user/getMyPage.htm?userId=1&bigId=0&pageNo=1&
	 * pageSize=10&token=888进行访问
	 * 
	 */
	@RequestMapping("/getMyPage")
	public void myPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "modifyTime", required = false, defaultValue = "0") Long modifyTime,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		if (userId == null) {
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response);
			return;
		}

		TUserVO tUserVO = tUserService.getUserVOById(userId, true, false);
		// 查询有多少条动态
		int noReadFeedsCount = userFeedsService.getUserFeedsCount4TUserFeedsExample(userId, 0, modifyTime);
		// 有图片的动态
		List<TUserFeeds> listHasPicFeeds = null;
		if (noReadFeedsCount > 0) {
			Page page = new Page().setPageByPageNoAndPageSize(pageNo, pageSize);
			listHasPicFeeds = userFeedsService.getUserFeedsList4TUserFeedsExample(userId, 1, modifyTime, page);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tUserVO", tUserVO);
		map.put("noReadFeedsCount", noReadFeedsCount);
		map.put("listHasPicFeeds", listHasPicFeeds);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	/**
	 * 􏰢􏰐􏰢􏰐搜索用户页面调用http接口 通过浏览器{host:port}/user/searchUserList.htm进行访问
	 * 
	 */
	@RequestMapping("/searchUserList")
	public void searchUserList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = false, defaultValue = "0") Long userId,
			@RequestParam(value = "area", required = false, defaultValue = "0") Integer area,
			@RequestParam(value = "industry", required = false, defaultValue = "0") Integer industry,
			@RequestParam(value = "key", required = false, defaultValue = "") String key,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
		Page page = new Page();
		List<TSearchUserVo> listSearchTUserVO = this.tUserService.getListByAreaAndIndustry(area, industry, key,
				page.setPageByPageNoAndPageSize(pageNo, pageSize));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listSearchTUserVO", listSearchTUserVO);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	/**
	 * 用户注册http接口 通过浏览器{host:port}/user/registerUser.htm进行访问
	 * 
	 * @param request
	 * @param response
	 * @param userName
	 * @param passWord
	 * @param valid
	 * @param mobile
	 */
	@RequestMapping("/registerUser")
	public void registerUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userName", required = true, defaultValue = "") String userName,
			@RequestParam(value = "passWord", required = true, defaultValue = "") String passWord,
			@RequestParam(value = "valid", required = true, defaultValue = "") String valid,
			@RequestParam(value = "mobile", required = true, defaultValue = "") String mobile) {

		if (StringUtils.isBlank(userName) || StringUtils.isBlank(passWord) || StringUtils.isBlank(valid) 
				|| StringUtils.isBlank(mobile)) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "PARAM_ERROR", Consts.ERRORCode.PARAM_ERROR), response);
			return;
		}

		//校验手机验证码
		boolean checkValid = ValidCodeUtil.checkValid(mobile, valid,true);
		if (checkValid == false) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "valid error", Consts.ERRORCode.VALID_ERROR), response);
			return;
		}

		TUserInfo tUserInfoMobile = this.tUserService.getUserInfoByMobile(mobile);
		if (tUserInfoMobile != null) {
			WebUtil.ajaxOutput(
					AjaxResult.newFailResult(null, "mobile ready register", Consts.ERRORCode.MOBILE_HAS_USEED_ERROR),
					response);
			return;
		}

		TUserVO tUserVO = new TUserVO();
		TUser tUser = new TUser();
		tUser.setLastLoginTime(new Date());
		tUser.setStatus(Constant.STATUS.NOMARL);
		tUser.setPassword(passWord);
		// tUser.setUserName(userName);
		tUser.setUserName(mobile);

		TUserInfo tUserInfo = new TUserInfo();

		tUserInfo.setArea(HttpParamUtil.integerParam(request, "area"));
		tUserInfo.setBackground(request.getParameter("background"));
		tUserInfo.setDescription(request.getParameter("description"));
		tUserInfo.setEmail(request.getParameter("email"));
		tUserInfo.setFaceImg(request.getParameter("faceImg"));
		tUserInfo.setFriends(0);
		tUserInfo.setIndustry(HttpParamUtil.integerParam(request, "industry"));
		tUserInfo.setMobile(mobile);
		tUserInfo.setNickName(userName);
		tUserInfo.setPraise(0);
		tUserInfo.setQrCode(request.getParameter("qrCode"));
		tUserInfo.setSex(HttpParamUtil.byteParam(request, "sex")); 
		tUserInfo.setTitle(request.getParameter("title"));

		tUserVO.settUser(tUser);
		tUserVO.settUserInfo(tUserInfo);
		tUserVO.settUserProfileList(null);

		TUserVO returnTUserVO = this.tUserService.insertUser(tUserVO);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(returnTUserVO), response);
	}

	/**
	 * 更新用户账号密码http接口 通过浏览器{host:port}/user/updateUser.htm进行访问
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @param userName
	 * @param passWord
	 */
	@RequestMapping("/updateUser")
	@Security
	public void updateUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nickName", required = false, defaultValue = "") String nickName,
			@RequestParam(value = "passWord", required = false, defaultValue = "") String passWord) {
		TUser currentUser = WebUtil.getCurrentUser(request);
		Long userId = currentUser.getId();
		TUserVO tUserVO = this.tUserService.getUserVOById(userId, false, false);
		TUser tUser = tUserVO.gettUser();
		tUser.setLastLoginTime(new Date());
		// if (!StringUtils.isBlank(userName)) {
		// tUser.setUserName(userName);
		// }
		if (!StringUtils.isBlank(passWord)) {
			tUser.setPassword(passWord);
		}

		TUserInfo tUserInfo = tUserVO.gettUserInfo();

		if(request.getParameter("area") != null){
			tUserInfo.setArea(HttpParamUtil.integerParam(request, "area"));
		}
		if (StringUtils.isNotBlank(request.getParameter("background"))) {
			tUserInfo.setBackground(request.getParameter("background"));
		}
		if(request.getParameter("description") != null){
			tUserInfo.setDescription(request.getParameter("description"));
		}
		if(request.getParameter("email") != null){
			tUserInfo.setEmail(request.getParameter("email"));
		}
		if (StringUtils.isNotBlank(request.getParameter("faceImg"))) {
			tUserInfo.setFaceImg(request.getParameter("faceImg"));
		}
		// tUserInfo.setFaceImg(request.getParameter("faceImg"));
		// tUserInfo.setFriends(0);
		if (StringUtils.isNotBlank(request.getParameter("industry"))) {
			tUserInfo.setIndustry(HttpParamUtil.integerParam(request, "industry"));
		}
		// tUserInfo.setMobile(request.getParameter("mobile"));
		if (StringUtils.isNotBlank(nickName)) {
			tUserInfo.setNickName(nickName);
		}
		// tUserInfo.setPraise(0);
		if (StringUtils.isNotBlank(request.getParameter("qrCode"))) {
			request.getParameter("qrCode");
		}
		// tUserInfo.setQrCode(request.getParameter("qrCode"));
		if(request.getParameter("sex") != null){
			tUserInfo.setSex(HttpParamUtil.byteParam(request, "sex"));
		}
		if(request.getParameter("title") != null){
			tUserInfo.setTitle(request.getParameter("title"));
		}
		if(request.getParameter("introduce") != null){
			tUserInfo.setIntroduce(request.getParameter("introduce"));
		}

		tUserVO.settUser(tUser);
		tUserVO.settUserInfo(tUserInfo);
		tUserVO.settUserProfileList(null);

		TUserVO returnTUserVO = this.tUserService.updateUser(tUserVO, false);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(returnTUserVO), response);
	}

	/**
	 * 插入单条用户经历
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 */
	@RequestMapping("/insertUserProfile")
	@Security
	public void insertUserProfile(
			@RequestParam(value="jobStartTime" , required = true )Long jobStartTime ,
			@RequestParam(value="jobEndTime" , required = true )Long jobEndTime ,
			HttpServletRequest request, HttpServletResponse response) {
		TUser currentUser = WebUtil.getCurrentUser(request);
		Long userId = currentUser.getId();
		List<TUserProfile> tUserProfileList = new ArrayList<TUserProfile>();
		TUserProfile tUserProfile = new TUserProfile();
		tUserProfile.setCompany(request.getParameter("company"));
		tUserProfile.setIntroduce(request.getParameter("introduce"));
		tUserProfile.setJobEndTime(jobEndTime); 
		tUserProfile.setJobStartTime(jobStartTime);
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

	/**
	 * 更新用户经历
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @param id
	 */
	@RequestMapping("/updateUserProfile")
	@Security
	public void updateUserProfile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true, defaultValue = "0") Long id ,
			@RequestParam(value="jobStartTime" , required = true )Long jobStartTime ,
			@RequestParam(value="jobEndTime" , required = true )Long jobEndTime) {

		TUser currentUser = WebUtil.getCurrentUser(request);
		Long userId = currentUser.getId();

		if (id == null || id <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "id is null", Consts.ERRORCode.PARAM_ERROR),
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
		tUserProfile.setJobEndTime(jobEndTime);
		tUserProfile.setJobStartTime(jobStartTime);
		tUserProfile.setModifyTime(new Date());
		tUserProfile.setPosition(request.getParameter("position"));
		tUserProfile.setUserId(userId);
		tUserProfile.setId(id);

		List<TUserProfile> tUserProfileListDB = new ArrayList<TUserProfile>();
		tUserProfileListDB.add(tUserProfile);

		this.tUserService.insertTUserProfile(tUserProfileListDB, userId);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(id), response);

	}

	/**
	 * 删除用户经历
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @param id
	 */
	@RequestMapping("/deleteUserProfileById")
	@Security
	public void deleteUserProfileById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true, defaultValue = "0") Long id) {

		TUser currentUser = WebUtil.getCurrentUser(request);
		Long userId = currentUser.getId();

		// List<TUserProfile> list =
		// this.tUserService.getTUserProfileList(userId, id, null);`

		List<TUserProfile> tUserProfileList = this.tUserService.getTUserProfileList(userId, id, null);
		if (tUserProfileList == null || tUserProfileList.size() <= 0) {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "id is null", Constant.STATUS.DELETE), response);
			return;
		}

		int count = this.tUserService.deleteTUserProfileByIdAndUserId(userId, id);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(count), response);

	}

	/**
	 * 用户注册http接口 通过浏览器{host:port}/user/registerUser.htm进行访问
	 * 
	 * @param request
	 * @param response
	 * @param userName
	 * @param passWord
	 * @param valid
	 * @param mobile
	 */
	@RequestMapping("/loginUser")
	public void loginUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "mobile", required = true, defaultValue = "0") String mobile,
			@RequestParam(value = "passWord", required = true, defaultValue = "0") String passWord) {

		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(passWord)) {
			WebUtil.ajaxOutput(
					AjaxResult.newFailResult(null, "用户名和密码不能为空", Consts.ERRORCode.PARAM_ERROR), response);
			return;
		}
		
		TUser user = tUserService.getUserByUserName(mobile) ;
		if(user == null){
			WebUtil.ajaxOutput(
					AjaxResult.newFailResult(null, "手机号不存在", Consts.ERRORCode.PARAM_ERROR), response);
			return;
		}
		
		if(!user.getPassword().equals(passWord.trim())){
			WebUtil.ajaxOutput(
					AjaxResult.newFailResult(null, "密码错误", Consts.ERRORCode.PARAM_ERROR), response);
			return;
		}
		
		TUserVO tUserVO = tUserService.getUserVOById(user.getId(), false, true);
		if(tUserVO == null){
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "登录失败", -1), response); 
		}else{  
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(tUserVO), response);
		}
	}


	/**
	 * 获取当前用户的邀请码
	 * @param request
	 * @param response
	 */
	@Security
	@RequestMapping("/getMyInviteCode")
	public void getMyInviteCode(HttpServletRequest request, HttpServletResponse response){
		TUser currentUser = WebUtil.getCurrentUser(request);
		TInviteCode inviteCode = inviteCodeService.getInviteCode(currentUser.getId());
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(inviteCode), response); 
	}
}
