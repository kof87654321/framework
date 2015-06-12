package com.zl.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zl.client.user.TUserService;
import com.zl.common.util.token.TokenUtils;
import com.zl.pojo.TUser;
import com.zl.pojo.TUserInfo;
import com.zl.pojo.TUserProfile;
import com.zl.vo.TUserVO;

public class TUserServiceTest extends BaseServiceTest {
	private static final Logger log = LoggerFactory.getLogger(TUserServiceTest.class);

	@Autowired
	private TUserService tUserService;

	@Test
	public void testInsert() {
		TUserVO tUserVO = new TUserVO();
		TUser tUser = new TUser();
		tUser.setUserName("帅的一b");
		tUser.setPassword("123456");
		tUser.setStatus(1);
		TUserInfo tUserInfo = new TUserInfo();
		tUserInfo.setArea(1);
		tUserInfo.setBackground("1");
		tUserInfo.setDescription("描述");
		tUserInfo.setEmail("2222@qq.com");
		tUserInfo.setFaceImg("1");
		tUserInfo.setFriends(211);
		tUserInfo.setIndustry(111);
		tUserInfo.setPraise(11);
		tUserInfo.setMobile("13555555555");
		tUserInfo.setNickName("昵称");
		tUserInfo.setSex(true);
		tUserInfo.setTitle("title");

		TUserProfile tUserProfile1 = new TUserProfile();
		tUserProfile1.setAttributes("tUserProfile");
		tUserProfile1.setCompany("www.taobao.com");
		tUserProfile1.setIntroduce("专注java开发8年");
		tUserProfile1.setJobEndTime(2013);
		tUserProfile1.setJobStartTime(2011);
		tUserProfile1.setPosition("帅哥");

		TUserProfile tUserProfile2 = new TUserProfile();
		tUserProfile2.setAttributes("tUserProfile");
		tUserProfile2.setCompany("www.alibaba.com");
		tUserProfile2.setIntroduce("专注java开发8年");
		tUserProfile2.setJobEndTime(0);
		tUserProfile2.setJobStartTime(2014);
		tUserProfile2.setPosition("美女");

		List<TUserProfile> list = new ArrayList<TUserProfile>();
		list.add(tUserProfile1);
		list.add(tUserProfile2);

		tUserVO.settUser(tUser);
		tUserVO.settUserInfo(tUserInfo);
		tUserVO.settUserProfileList(list);

		TUserVO insertTUserVO = tUserService.insertUser(tUserVO);
		System.out.println("gettUser=" + ReflectionToStringBuilder.toString(insertTUserVO.gettUser()));
		System.out.println("gettUserInfo=" + ReflectionToStringBuilder.toString(insertTUserVO.gettUserInfo()));
		System.out.println(TokenUtils.checkToken(insertTUserVO.gettUser().getId(), insertTUserVO.gettUser().getPassword(), insertTUserVO.gettUser().getToken()));
	}

	@Test
	public void testSelect() {
		TUserVO tUserVO = this.tUserService.getUserVOById(5l, true, true);

		System.out.println("tUserVO.gettUser()=" + ReflectionToStringBuilder.toString(tUserVO.gettUser()));
		System.out.println("tUserVO.gettUserInfo()="
				+ ReflectionToStringBuilder.toString(tUserVO.gettUserInfo()));
		System.out.println("size()=" + tUserVO.gettUserProfileList().size());
		for (int i = 0; i < tUserVO.gettUserProfileList().size(); i++) {
			System.out.println("tUserVO.gettUserProfileList()="
					+ ReflectionToStringBuilder.toString(tUserVO.gettUserProfileList().get(i)));
		}
		
		
		System.out.println(TokenUtils.checkToken(tUserVO.gettUser().getId(), tUserVO.gettUser().getPassword(), tUserVO.gettUser().getToken()));
	}

	@Test
	public void testUpdate() {
		TUserVO tUserVO = this.tUserService.getUserVOById(5l, true, true);
		TUser tuser = tUserVO.gettUser();
		tuser.setUserName("帅的一bb");
		TUserInfo tUserInfo = tUserVO.gettUserInfo();
		tUserInfo.setNickName("帅的一bb");
		// List<TUserProfile> list = tUserVO.gettUserProfileList();
		TUserVO updateTUserVO = this.tUserService.updateUser(tUserVO, false);
		System.out.println("updateTUserVO=" + ReflectionToStringBuilder.toString(updateTUserVO));

	}

	@Test
	public void testUpdateProfile() {
		List<TUserProfile> list = this.tUserService.getTUserProfileList(5l, 0l,null);
		for (int i = 0; i < list.size(); i++) {
			TUserProfile tUserProfile = list.get(i);
			tUserProfile.setPosition("帅哥11");
			long count = this.tUserService.updateTUserProfile(tUserProfile, tUserProfile.getUserId(),
					tUserProfile.getId());
			System.out.println("count=" + count);
		}
	}
}
