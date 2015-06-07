package com.zl.service;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zl.client.userFeeds.UserFeedsService;
import com.zl.pojo.Page;
import com.zl.pojo.TUserFeeds;

public class UserFeedsServiceTest extends BaseServiceTest {
	private static final Logger log = LoggerFactory.getLogger(UserFeedsServiceTest.class);

	@Autowired
	private UserFeedsService userFeedsService;

	@Test
	public void testInsert() {
		TUserFeeds tUserFeeds = new TUserFeeds();
		tUserFeeds.setAttributes("123");
		tUserFeeds.setPraise(1);
		tUserFeeds.setPageCount(1);
		tUserFeeds.setUserId(1l);
		tUserFeeds.setContent("123");
		int id = userFeedsService.insertTUserFeeds(tUserFeeds);
		System.out.println("id=" + id);
	}

	@Test
	public void testSelect() {
		int count = userFeedsService.getUserFeedsCount4TUserFeedsExample(1l, 1, 0);
		System.out.println("count=" + count);
		Page page = new Page();
		page.setBegin(0);
		page.setLength(20);
		List<TUserFeeds> list = userFeedsService.getUserFeedsList4TUserFeedsExample(1l, 0, 0, page);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("list=" + ReflectionToStringBuilder.toString(list.get(i)));
		}

	}
}
