package com.zl.web.control;

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

import com.zl.client.user.TUserService;
import com.zl.client.userFeeds.UserFeedsService;
import com.zl.pojo.Page;
import com.zl.pojo.TUserFeeds;
import com.zl.vo.TUserVO;
import com.zl.web.annotation.Security;
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
			@RequestParam(value = "bigId", required = false, defaultValue = "0") Long bigId,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize) {
		if (userId == null) {
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response);
			return;
		}

		TUserVO tUserVO = tUserService.getUserVOById(userId, true, false);
		// TODO 是否还缺少某个用户看过另一个用户的内容
		int noReadFeedsCount = userFeedsService.getUserFeedsCount4TUserFeedsExample(userId, 0, bigId);
		List<TUserFeeds> listFeeds = null;
		if (noReadFeedsCount > 0) {
			Page page = new Page();
			page.setBegin(pageNo);
			page.setLength(pageSize);
			listFeeds = userFeedsService.getUserFeedsList4TUserFeedsExample(userId, 1, bigId, page);
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
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
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

}
