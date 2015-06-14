package com.zl.web.control;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zl.client.chat.ChatService;
import com.zl.common.util.Constant;
import com.zl.pojo.ChatQuery;
import com.zl.pojo.Page;
import com.zl.pojo.TChat;
import com.zl.web.annotation.Security;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

@Controller
@RequestMapping("/chat")
public class ChattControl {

	private static final Logger log = LoggerFactory.getLogger(ChattControl.class);

	@Autowired
	private ChatService chatService;

	@RequestMapping("/send")
	@Security
	public void sendMessage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "toId", required = true, defaultValue = "0") Long toId,
			@RequestParam(value = "msg", required = true, defaultValue = "") String message) {
		TChat chat = new TChat();
		chat.setFromId(userId);
		chat.setToId(toId);
		chat.setMessage(message);
		chat.setStatus(Constant.STATUS.NOMARL);
		boolean result = chatService.sendMsg(chat);
		if (result) {
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response);

		} else {
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "发送消息失败", 101), response);
		}
	}

	@RequestMapping("/read")
	@Security
	public void readMessage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "since", required = true, defaultValue = "0") Long since,
			@RequestParam(value = "max", required = true, defaultValue = "0") Long max,
			@RequestParam(value = "status", required = false, defaultValue = "-1") Integer status) {
		ChatQuery query = new ChatQuery();
		query.setUserId(userId);
		query.setPage(new Page().setPageByPageNoAndPageSize(pageNo, pageSize));
		if (since > 0) {
			query.setSince(new Date(since));
		}
		if (max > 0) {
			query.setMax(new Date(max));
		}
		if (status > -1) {
			query.setStatus(status);
		}
		List<TChat> result = chatService.readMsg(query);
		WebUtil.ajaxOutput(AjaxResult.newSuccessResult(result), response);
	}
}
