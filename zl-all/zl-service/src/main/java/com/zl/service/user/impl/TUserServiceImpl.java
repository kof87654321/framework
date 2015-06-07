package com.zl.service.user.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.user.TUserService;
import com.zl.dao.mapper.TUserInfoMapperExt;
import com.zl.dao.mapper.TUserMapperExt;
import com.zl.dao.mapper.TUserProfileMapperExt;
import com.zl.pojo.TUser;
import com.zl.pojo.TUserInfo;
import com.zl.pojo.TUserInfoExample;
import com.zl.pojo.TUserProfile;
import com.zl.pojo.TUserProfileExample;
import com.zl.vo.TUserVO;

@Service
public class TUserServiceImpl implements TUserService {

	@Autowired
	private TUserInfoMapperExt userInfoMapperExt;

	@Autowired
	private TUserMapperExt userMapperExt;

	@Autowired
	private TUserProfileMapperExt userProfileMapperExt;

	public TUserVO getUserVOById(Long userId) {
		TUser tUser = this.userMapperExt.selectByPrimaryKey(userId);

		TUserInfoExample tUserInfoExample = new TUserInfoExample();
		tUserInfoExample.createCriteria().andUserIdEqualTo(userId);
		List<TUserInfo> tUserInfoList = this.userInfoMapperExt.selectByExample(tUserInfoExample);
		TUserInfo tUserInfo = null;
		if (tUserInfoList != null && tUserInfoList.size() > 0) {
			tUserInfo = tUserInfoList.get(0);
		}

		TUserProfileExample tUserProfileExample = new TUserProfileExample();
		tUserProfileExample.createCriteria().andUserIdEqualTo(userId + "");
		List<TUserProfile> tUserProfileList = this.userProfileMapperExt.selectByExample(tUserProfileExample);
		TUserVO tUserVO = new TUserVO();
		tUserVO.settUser(tUser);
		tUserVO.settUserInfo(tUserInfo);
		tUserVO.settUserProfileList(tUserProfileList);
		return tUserVO;

	}

	public int updateUser(TUserVO tUserVO) {
		if (tUserVO == null) {
			return 0;
		}
		this.userInfoMapperExt.updateByPrimaryKeySelective(tUserVO.gettUserInfo());
		this.userMapperExt.updateByPrimaryKeySelective(tUserVO.gettUser());
		if (tUserVO.gettUserProfileList() != null && tUserVO.gettUserProfileList().size() > 0) {
			for (int i = 0; i < tUserVO.gettUserProfileList().size(); i++) {
				this.userProfileMapperExt.updateByPrimaryKey(tUserVO.gettUserProfileList().get(i));
			}
		}
		return 1;
	}

	public int insertUser(TUserVO tUserVO) {
		if (tUserVO == null) {
			return 0;
		}
		Date date = new Date();
		TUser tUser = tUserVO.gettUser();
		tUser.setCreateTime(date);
		tUser.setLastLoginTime(date);
		int userId = this.userMapperExt.insert(tUser);

		TUserInfo tUserInfo = tUserVO.gettUserInfo();
		if (tUserInfo != null) {
			tUserInfo.setCreateTime(date);
			tUserInfo.setModifyTime(date);
			tUserInfo.setUserId(Long.parseLong(userId + ""));
			this.userInfoMapperExt.insert(tUserVO.gettUserInfo());
		}

		List<TUserProfile> tUserProfileList = tUserVO.gettUserProfileList();
		if (tUserProfileList == null || tUserProfileList.size() <= 0) {
			return userId;
		}

		TUserProfile tUserProfile = null;
		for (int i = 0; i < tUserProfileList.size(); i++) {
			tUserProfile = tUserProfileList.get(i);
			tUserProfile.setCreateTime(date);
			tUserProfile.setModifyTime(date);
			tUserProfile.setUserId(userId + "");
			this.userProfileMapperExt.insert(tUserProfile);
		}

		return userId;
	}
}