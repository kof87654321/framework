package com.zl.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.user.TUserService;
import com.zl.common.util.token.TokenUtils;
import com.zl.dao.mapper.TUserInfoMapperExt;
import com.zl.dao.mapper.TUserMapperExt;
import com.zl.dao.mapper.TUserProfileMapperExt;
import com.zl.pojo.TUser;
import com.zl.pojo.TUserInfo;
import com.zl.pojo.TUserInfoExample;
import com.zl.pojo.TUserProfile;
import com.zl.pojo.TUserProfileExample;
import com.zl.pojo.TUserProfileExample.Criteria;
import com.zl.vo.TUserVO;

@Service
public class TUserServiceImpl implements TUserService {

	private Logger log = LoggerFactory.getLogger(TUserServiceImpl.class);

	@Autowired
	private TUserInfoMapperExt userInfoMapperExt;

	@Autowired
	private TUserMapperExt userMapperExt;

	@Autowired
	private TUserProfileMapperExt userProfileMapperExt;

	public TUserVO getUserVOById(Long userId, boolean profile, boolean token) {
		TUser tUser = this.userMapperExt.selectByPrimaryKey(userId);

		if (tUser == null) {
			return null;
		}

		if (token) {
			String strToken = TokenUtils.getToken(tUser.getId(), tUser.getPassword(),
					tUser.getLastLoginTime());
			if (StringUtils.isNotBlank(strToken)) {
				tUser.setToken(strToken);
			}
		}
		TUserInfoExample tUserInfoExample = new TUserInfoExample();
		tUserInfoExample.createCriteria().andUserIdEqualTo(userId);
		List<TUserInfo> tUserInfoList = this.userInfoMapperExt.selectByExample(tUserInfoExample);
		TUserInfo tUserInfo = null;
		if (tUserInfoList != null && tUserInfoList.size() > 0) {
			tUserInfo = tUserInfoList.get(0);
		}
		List<TUserProfile> tUserProfileList = null;
		if (profile) {
			TUserProfileExample tUserProfileExample = new TUserProfileExample();
			tUserProfileExample.createCriteria().andUserIdEqualTo(userId);
			tUserProfileList = this.userProfileMapperExt.selectByExample(tUserProfileExample);
		}

		TUserVO tUserVO = new TUserVO();
		tUserVO.settUser(tUser);
		tUserVO.settUserInfo(tUserInfo);
		tUserVO.settUserProfileList(tUserProfileList);
		return tUserVO;

	}

	public TUserVO updateUser(TUserVO tUserVO, boolean profile) {
		if (tUserVO == null) {
			return null;
		}
		this.userInfoMapperExt.updateByPrimaryKeySelective(tUserVO.gettUserInfo());
		this.userMapperExt.updateByPrimaryKeySelective(tUserVO.gettUser());
		if (profile && tUserVO.gettUserProfileList() != null && tUserVO.gettUserProfileList().size() > 0) {
			for (int i = 0; i < tUserVO.gettUserProfileList().size(); i++) {
				this.userProfileMapperExt.updateByPrimaryKey(tUserVO.gettUserProfileList().get(i));
			}
		}
		return this.getUserVOById(tUserVO.gettUser().getId(), false, true);
	}

	public TUserVO insertUser(TUserVO tUserVO) {
		if (tUserVO == null) {
			return null;
		}
		Date date = new Date();
		TUser tUser = tUserVO.gettUser();
		tUser.setCreateTime(date);
		tUser.setLastLoginTime(date);
		int insertCount = this.userMapperExt.insert(tUser);
		if (insertCount <= 0) {
			log.warn("插入用户信息失败");
			return null;
		}
		Long userId = tUser.getId();
		TUserInfo tUserInfo = tUserVO.gettUserInfo();
		if (tUserInfo != null) {
			tUserInfo.setCreateTime(date);
			tUserInfo.setModifyTime(date);
			tUserInfo.setUserId(Long.parseLong(userId + ""));
			this.userInfoMapperExt.insert(tUserVO.gettUserInfo());
		}

		List<TUserProfile> tUserProfileList = tUserVO.gettUserProfileList();
		if (tUserProfileList == null || tUserProfileList.size() <= 0) {
			return this.getUserVOById(userId, false, true);
		}

		TUserProfile tUserProfile = null;
		for (int i = 0; i < tUserProfileList.size(); i++) {
			tUserProfile = tUserProfileList.get(i);
			tUserProfile.setCreateTime(date);
			tUserProfile.setModifyTime(date);
			tUserProfile.setUserId(userId);
			this.userProfileMapperExt.insert(tUserProfile);
		}

		return this.getUserVOById(userId, false, true);
	}

	@Override
	public int updateTUserProfile(TUserProfile tUserProfile, long userId, long id) {
		if (userId <= 0 || id <= 0 || tUserProfile == null || tUserProfile.getUserId() != userId
				|| tUserProfile.getId() != id) {
			return 0;
		}

		// TUserProfileExample tUserProfileExample = new TUserProfileExample();
		// Criteria criteria = tUserProfileExample.createCriteria();
		// criteria.andIdEqualTo(id);
		return this.userProfileMapperExt.updateByPrimaryKeySelective(tUserProfile);
	}

	@Override
	public List<TUserProfile> getTUserProfileList(long userId, long id) {

		if (userId <= 0 && id <= 0) {
			return null;
		}
		List<TUserProfile> list = new ArrayList<TUserProfile>();
		if (id > 0) {
			TUserProfile tUserProfile = this.userProfileMapperExt.selectByPrimaryKey(id);
			list.add(tUserProfile);
			return list;
		}

		TUserProfileExample tUserProfileExample = new TUserProfileExample();
		Criteria criteria = tUserProfileExample.createCriteria();
		if (id > 0) {
			criteria.andIdEqualTo(id);
		}
		criteria.andUserIdEqualTo(userId);
		return this.userProfileMapperExt.selectByExample(tUserProfileExample);
	}
}
