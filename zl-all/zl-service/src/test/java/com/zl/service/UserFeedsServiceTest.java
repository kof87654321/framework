package com.zl.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zl.client.userFeeds.UserFeedsService;
import com.zl.pojo.TUserFeeds;

public class UserFeedsServiceTest extends BaseServiceTest{
	private static final Logger log = LoggerFactory.getLogger(UserFeedsServiceTest.class);
	
	@Autowired
	private UserFeedsService userFeedsService;
	
	@Test
	public void testInsert(){
		TUserFeeds tUserFeeds = new TUserFeeds();
		tUserFeeds.setAttributes("123");
		tUserFeeds.setPraise(1);
		tUserFeeds.setPageCount(1);
		tUserFeeds.setUserId(1l);
		userFeedsService.insertTUserFeeds(tUserFeeds );
	}
}
