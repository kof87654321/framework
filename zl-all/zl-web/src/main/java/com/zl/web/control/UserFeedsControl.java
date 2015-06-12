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

import com.zl.client.userFeeds.UserFeedsService;
import com.zl.pojo.Page;
import com.zl.pojo.TUserFeeds;
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

	@RequestMapping("/getUserFeedsByUserId")
	@Security
	public void getFeedsByUserId(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "all", required = false, defaultValue = "0") Long all,
			@RequestParam(value = "greaterThanOrEqualToPicCount", required = false, defaultValue = "0") Integer greaterThanOrEqualToPicCount,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

		Page page = new Page();
		List<TUserFeeds> list = userFeedsService.getUserFeedsList4TUserFeedsExample(userId,
				greaterThanOrEqualToPicCount, 0l, page.setPageByPageNoAndPageSize(pageNo, pageSize));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userFeedsList", list);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}

	@RequestMapping("/addLike")
	@Security
	public void getFeedsByUserId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "feedsId", required = true, defaultValue = "0") Long feedsId) {

		int count = userFeedsService.addLike(feedsId, 1);
		if (count > 0) {
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(true), response);
		} else {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "server error", Consts.ERRORCode.SERVER_ERROR), response);
		}

	}

}
