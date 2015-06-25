package com.zl.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zl.client.invite.InviteCodeService;
import com.zl.web.app.Consts;
import com.zl.web.app.util.ValidCodeUtil;
import com.zl.web.app.util.WebUtil;
import com.zl.web.app.vo.AjaxResult;

/**
 * 校验相关 
 * @author is_zhoufeng
 */
@Controller
@RequestMapping("/check")
public class CheckController {

	@Autowired
	private InviteCodeService inviteCodeService ;
	
	/**
	 * 校验邀请码是否有效
	 * @param code
	 * @param request
	 * @param response
	 */
	@RequestMapping("/inviteCode") 
	public void inviteCodeCheck(@RequestParam(value="code" , required = true) String code ,
			HttpServletRequest request, HttpServletResponse response) {
		boolean checkInviteCode = inviteCodeService.checkInviteCode(code);
		if(checkInviteCode){
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response); 
		}else{ 
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "inviteCode error", Consts.ERRORCode.INVITE_CODE_ERROR), response);  
		}
	}
	
	
	/**
	 * 校验验证码是否有效
	 * @param code
	 * @param request
	 * @param response
	 */
	@RequestMapping("/validCode") 
	public void validCodeChekc(
			@RequestParam(value="code" , required = true) String code , 
			@RequestParam(value="mobile" , required = true) String mobile , 
			HttpServletRequest request, HttpServletResponse response) {
		boolean checkValidCode = ValidCodeUtil.checkValid(mobile, code, false) ;
		if(checkValidCode){
			WebUtil.ajaxOutput(AjaxResult.newSuccessResult(null), response); 
		}else{ 
			WebUtil.ajaxOutput(AjaxResult.newFailResult(null, "validCode error", Consts.ERRORCode.VALID_ERROR), response);  
		}
	}
	
	
	
}
