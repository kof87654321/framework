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

import com.zl.client.user.TUserService;
import com.zl.client.userFeeds.UserFeedsService;
import com.zl.pojo.Page;
import com.zl.pojo.TUserFeeds;
import com.zl.vo.TUserVO;
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
	public void newVersion(Long userId, Long bigId, int pageNo, int pageSize, HttpServletRequest request,
			HttpServletResponse response) {
		if (userId == null) {
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response);
			return;
		}

		TUserVO tUserVO = tUserService.getUserVOById(userId,true,false);
		int count = userFeedsService.getUserFeedsCount4TUserFeedsExample(userId, 1, bigId);
		List<TUserFeeds> list = null;
		if (count > 0) {
			Page page = new Page();
			page.setBegin(pageNo);
			page.setLength(pageSize);
			list = userFeedsService.getUserFeedsList4TUserFeedsExample(userId, 1, bigId, page);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tUserVO", tUserVO);
		map.put("count", count);
		map.put("list", list);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(map), response);
	}
}
