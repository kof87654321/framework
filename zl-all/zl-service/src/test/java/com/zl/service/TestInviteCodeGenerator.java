package com.zl.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zl.client.invite.InviteCodeService;
import com.zl.common.util.invitecode.InviteCodeUtil;
import com.zl.pojo.TInviteCode;

/**
 * 
 * @author is_zhoufeng
 *
 */
public class TestInviteCodeGenerator extends BaseServiceTest{
	
	private static final Logger log = LoggerFactory.getLogger(TestInviteCodeGenerator.class);
	
	@Autowired
	private InviteCodeService inviteCodeService ;
	
	@Test
	public void generator(){
		long startId = 1000 ;
		long endId = 1100 ;
		for (long i = startId; i <=  endId; i++) {
			Date today = new Date();
			TInviteCode inviteCode = new TInviteCode() ;
			inviteCode.setStartTime(today);
			inviteCode.setEndTime(DateUtils.addYears(today, 1));
			inviteCode.setStatus(1);
			inviteCode.setUsedCount(0);
			inviteCode.setUserId(i);
			inviteCode.setCode(InviteCodeUtil.toSerialCode(i)); 
			inviteCodeService.insert(inviteCode);
			log.info("生成邀请码{}成功" , ToStringBuilder.reflectionToString(inviteCode));   
		}
	}

}
