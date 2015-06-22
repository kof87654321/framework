package com.zl.service.invite.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.invite.InviteCodeService;
import com.zl.dao.mapper.TInviteCodeMapperExt;
import com.zl.pojo.Page;
import com.zl.pojo.TInviteCode;
import com.zl.pojo.TInviteCodeExample;

/**
 * 
 * @author is_zhoufeng
 *
 */
@Service
public class InviteCodeServiceImpl implements InviteCodeService{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(InviteCodeServiceImpl.class) ;

	@Autowired
	private TInviteCodeMapperExt inviteCodeMapperExt ;
	
	@Override
	public boolean insert(TInviteCode inviteCode) {
		return inviteCodeMapperExt.insert(inviteCode) > 0;
	}

	@Override
	public TInviteCode getInviteCode(Long userId) {
		TInviteCodeExample condition = new TInviteCodeExample() ;
		condition.createCriteria().andUserIdEqualTo(userId);
		condition.setPage(new Page(0, 1)); 
		List<TInviteCode> inviteCodes = inviteCodeMapperExt.selectByExample(condition);
		if(inviteCodes == null || inviteCodes.size() <= 0){
			return null ;
		}
		return inviteCodes.get(0); 
	}

	@Override
	public TInviteCode getInviteCode(String code) {
		TInviteCodeExample condition = new TInviteCodeExample();
		condition.createCriteria().andCodeEqualTo(code);
		condition.setPage(new Page(0, 1)); 
		List<TInviteCode> inviteCodes = inviteCodeMapperExt.selectByExample(condition);
		if(inviteCodes == null || inviteCodes.size() <= 0){
			return null ;
		}
		return inviteCodes.get(0); 
	}
	
	@Override
	public boolean checkInviteCode(String code) {
		TInviteCodeExample condition = new TInviteCodeExample() ;
		condition.createCriteria().andCodeEqualTo(code);
		condition.setPage(new Page(0, 1)); 
		List<TInviteCode> inviteCodes = inviteCodeMapperExt.selectByExample(condition);
		if(inviteCodes == null || inviteCodes.size() <= 0){
			log.warn("没找到邀请码[{}]",code);
			return false ;
		}
		TInviteCode inviteCode = inviteCodes.get(0);
		Date today = new Date();
		if(today.before(inviteCode.getStartTime()) || today.after(inviteCode.getEndTime())){
			log.warn("邀请码[{}]已过期");
			return false ;
		}
		if(inviteCode.getUsedCount() >= 3){
			log.warn("邀请码[{}]以使用3次或以上");
			return false ;
		}
		if(inviteCode.getStatus().equals(1)){
			return true ;
		}
		return false ;
	}

	@Override
	public void incrUsedCount(String code) {
		TInviteCode inviteCode = getInviteCode(code);
		if(inviteCode != null){
			TInviteCode updateCode = new TInviteCode();
			updateCode.setId(inviteCode.getId()); 
			updateCode.setUsedCount(inviteCode.getUsedCount() + 1);
			inviteCodeMapperExt.updateByPrimaryKeySelective(updateCode) ;
		}
	}


}
