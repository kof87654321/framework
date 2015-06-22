package com.zl.client.invite;

import com.zl.pojo.TInviteCode;

/**
 * 邀请码相关服务
 * @author is_zhoufeng
 *
 */
public interface InviteCodeService {

	/**
	 * 添加邀请码
	 * @param inviteCode
	 * @return
	 */
	boolean insert(TInviteCode inviteCode);
	
	/**
	 * 根据用户Id获取用户拥有的邀请码
	 * @param userId
	 * @return
	 */
	TInviteCode getInviteCode(Long userId);
	
	/**
	 * 根据邀请码code获取邀请码
	 * @param code
	 * @return
	 */
	TInviteCode getInviteCode(String code);

	/**
	 * 使用邀请码后调用该方法（将邀请成功数量+1）
	 * @param code
	 */
	void incrUsedCount(String code);
	
	/**
	 * 校验邀请码是否有效
	 * @param code
	 * @return
	 */
	boolean checkInviteCode(String code);
}
