package com.zl.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.emchat.EMUserService;
import com.zl.client.user.TUserService;
import com.zl.common.util.Constant;
import com.zl.common.util.ListUtil;
import com.zl.common.util.token.TokenUtils;
import com.zl.dao.mapper.TUserInfoMapperExt;
import com.zl.dao.mapper.TUserMapperExt;
import com.zl.dao.mapper.TUserProfileMapperExt;
import com.zl.pojo.Page;
import com.zl.pojo.TUser;
import com.zl.pojo.TUserExample;
import com.zl.pojo.TUserInfo;
import com.zl.pojo.TUserInfoExample;
import com.zl.pojo.TUserProfile;
import com.zl.pojo.TUserProfileExample;
import com.zl.pojo.TUserProfileExample.Criteria;
import com.zl.vo.IMUserVO;
import com.zl.vo.TUserVO;

/**
 * 
 * @ClassName: TUserServiceImpl
 * @Description: 用户信息的service 底层调用Mapper
 * @author youbush
 * @date 2015年6月15日 下午8:07:15
 *
 */
@Service
public class TUserServiceImpl implements TUserService {

	private Logger log = LoggerFactory.getLogger(TUserServiceImpl.class);

	@Autowired
	/* 用户扩展信息底层 */private TUserInfoMapperExt userInfoMapperExt;

	@Autowired
	/* 用户基本信息底层 */private TUserMapperExt userMapperExt;

	@Autowired
	/* 用户职业经历底层 */private TUserProfileMapperExt userProfileMapperExt;

	@Autowired
	/* 环信服务 */ private EMUserService emUserService ;

	/*
	 * <p>Title: getUserVOById</p> <p>Description:获取用户的vo对象，用于前台展示使用 </p>
	 * 
	 * @param userId 用户id
	 * 
	 * @param profile 是否查询工作经历
	 * 
	 * @param token 是否返回token
	 * 
	 * @return
	 * 
	 * @see com.zl.client.user.TUserService#getUserVOById(java.lang.Long,
	 * boolean, boolean)
	 */
	public TUserVO getUserVOById(Long userId, boolean profile, boolean token) {
		TUser tUser = this.userMapperExt.selectByPrimaryKey(userId);

		if (tUser == null) {
			return null;
		}

		if (token) {  //如果token为true表示调用该方法是为了用户登录

			//修改用户最后登录时间
			Date lastLoginTime = new Date();
			updateLastLoginTime(userId, lastLoginTime); 
			tUser.setLastLoginTime(lastLoginTime); 

			String strToken = TokenUtils.getToken(tUser.getId(), tUser.getPassword(), tUser.getLastLoginTime());
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

	/*
	 * <p>Title: updateUser</p> <p>Description: 更新用户信息</p>
	 * 
	 * @param tUserVO
	 * 
	 * @param profile 是否需要更新个人经历
	 * 
	 * @return
	 * 
	 * @see com.zl.client.user.TUserService#updateUser(com.zl.vo.TUserVO,
	 * boolean)
	 */
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

	/*
	 * 
	 * <p>Title: insertUser</p> <p>Description:用户注册使用接口 会操作三个数据表，并添加环信用户</p>
	 * 
	 * @param tUserVO
	 * 
	 * @return
	 * 
	 * @see com.zl.client.user.TUserService#insertUser(com.zl.vo.TUserVO)
	 */
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
		if (tUserProfileList != null && tUserProfileList.size() > 0) {
			TUserProfile tUserProfile = null;
			for (int i = 0; i < tUserProfileList.size(); i++) {
				tUserProfile = tUserProfileList.get(i);
				tUserProfile.setCreateTime(date);
				tUserProfile.setModifyTime(date);
				tUserProfile.setUserId(userId);
				this.userProfileMapperExt.insert(tUserProfile);
			}
		}

		//添加环信用户
		IMUserVO imUser = new IMUserVO() ;
		imUser.setUsername(tUser.getUserName());
		imUser.setPassword("123456");  
		boolean imUserResult = emUserService.register(imUser);
		if(!imUserResult){
			log.error("添加环信用户失败,username:{}" , tUser.getUserName()); 
		}

		return this.getUserVOById(userId, false, true);
	}

	/*
	 * 
	 * <p>Title: updateTUserProfile</p> <p>Description:
	 * 根据用户id，以及用户经历的主键，更新用户经历</p>
	 * 
	 * @param tUserProfile 需要更新的对象
	 * 
	 * @param userId 用户id
	 * 
	 * @param id 更新对象的主键
	 * 
	 * @return
	 * 
	 * @see
	 * com.zl.client.user.TUserService#updateTUserProfile(com.zl.pojo.TUserProfile
	 * , long, long)
	 */
	@Override
	public Long updateTUserProfile(TUserProfile tUserProfile, long userId, long id) {
		if (userId <= 0 || id <= 0 || tUserProfile == null || tUserProfile.getUserId() != userId
				|| tUserProfile.getId() != id) {
			return 0l;
		}

		tUserProfile.setModifyTime(new Date());

		// TUserProfileExample tUserProfileExample = new TUserProfileExample();
		// Criteria criteria = tUserProfileExample.createCriteria();
		// criteria.andIdEqualTo(id);
		this.userProfileMapperExt.updateByPrimaryKeySelective(tUserProfile);
		return id;
	}

	/*
	 * 
	 * <p>Title: getTUserProfileList</p> <p>Description:获取用户经历list </p>
	 * 
	 * @param userId
	 * 
	 * @param id 传入id主键的时候，查询单条数据
	 * 
	 * @param page 分页对象
	 * 
	 * @return
	 * 
	 * @see com.zl.client.user.TUserService#getTUserProfileList(long, long,
	 * com.zl.pojo.Page)
	 */
	@Override
	public List<TUserProfile> getTUserProfileList(long userId, long id, Page page) {

		if (userId <= 0 && id <= 0) {
			return null;
		}
		if (page == null) {
			page = new Page().setPageByPageNoAndPageSize(1, 10);
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
		criteria.andStatusIn(ListUtil.getIntegerList(Constant.STATUS.CHECKED, Constant.STATUS.NOMARL));
		tUserProfileExample.setOrderByClause("CreateTime DESC");
		tUserProfileExample.setPage(page);
		return this.userProfileMapperExt.selectByExample(tUserProfileExample);
	}

	/*
	 * 
	 * <p>Title: getUserById</p> <p>Description: 获取用户基本对象信息</p>
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 * @see com.zl.client.user.TUserService#getUserById(java.lang.Long)
	 */
	@Override
	public TUser getUserById(Long userId) {
		if (userId == null) {
			return null;
		}
		TUser tUser = this.userMapperExt.selectByPrimaryKey(userId);
		return tUser;
	}

	/*
	 * 
	 * <p>Title: getListByAreaAndIndustry</p> <p>Description:
	 * 根据区域以及行业关键词查询用户</p>
	 * 
	 * @param area 区域id
	 * 
	 * @param industry 行业id
	 * 
	 * @param key 关键词
	 * 
	 * @param page
	 * 
	 * @return
	 * 
	 * @see com.zl.client.user.TUserService#getListByAreaAndIndustry(int, int,
	 * java.lang.String, com.zl.pojo.Page)
	 */
	@Override
	public List<TUserVO> getListByAreaAndIndustry(int area, int industry, String key, Page page) {
		TUserInfoExample tUserInfoExample = new TUserInfoExample();
		com.zl.pojo.TUserInfoExample.Criteria criteria = tUserInfoExample.createCriteria();
		if (area > 0) {
			criteria.andAreaEqualTo(area);
		}
		if (industry > 0) {
			criteria.andIndustryEqualTo(industry);
		}
		tUserInfoExample.setOrderByClause("CreateTime DESC");
		tUserInfoExample.setPage(page);
		List<TUserInfo> list = this.userInfoMapperExt.selectByExample(tUserInfoExample);
		if (list == null || list.size() <= 0) {
			return null;
		}
		List<TUserVO> returnlist = new ArrayList<TUserVO>();
		for (TUserInfo tUserInfo : list) {
			TUserVO tUserVO = this.getUserVOById(tUserInfo.getUserId(), false, false);
			returnlist.add(tUserVO);
		}
		return returnlist;
	}

	/*
	 * 
	 * <p>Title: insertTUserProfile</p> <p>Description: 插入单条职业经历</p>
	 * 
	 * @param tUserProfileList
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 * @see com.zl.client.user.TUserService#insertTUserProfile(java.util.List,
	 * long)
	 */
	@Override
	public Long insertTUserProfile(List<TUserProfile> tUserProfileList, long userId) {
		if (userId <= 0 || tUserProfileList == null || tUserProfileList.size() <= 0) {
			return 0l;
		}
		Long id = 0l;
		for (TUserProfile tUserProfile : tUserProfileList) {
			if (tUserProfile.getUserId() == null || tUserProfile.getUserId().longValue() != userId) {
				continue;
			}
			tUserProfile.setCreateTime(new Date());
			tUserProfile.setModifyTime(new Date());
			this.userProfileMapperExt.insert(tUserProfile);
			id = tUserProfile.getId();
		}

		return id;
	}

	/*
	 * 
	 * <p>Title: deleteTUserProfileByIdAndUserId</p> <p>Description:
	 * 删除单条职业经历</p>
	 * 
	 * @param userId
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.zl.client.user.TUserService#deleteTUserProfileByIdAndUserId(java.
	 * lang.Long, long)
	 */
	@Override
	public int deleteTUserProfileByIdAndUserId(Long userId, long id) {
		// return this.userProfileMapperExt.;
		if (userId == null || userId <= 0 || id <= 0) {
			return 0;
		}
		TUserProfile tUserProfile = this.userProfileMapperExt.selectByPrimaryKey(id);
		if (tUserProfile == null || tUserProfile.getUserId().longValue() != userId) {
			return 0;
		}

		tUserProfile.setStatus(Constant.STATUS.DELETE);
		return this.userProfileMapperExt.updateByPrimaryKey(tUserProfile);
	}

	/*
	 * 
	 * <p>Title: getUserInfoByMobile</p> <p>Description: 根据电话查询用户对象，用于判断是否注册</p>
	 * 
	 * @param mobile
	 * 
	 * @return
	 * 
	 * @see
	 * com.zl.client.user.TUserService#getUserInfoByMobile(java.lang.String)
	 */
	public TUserInfo getUserInfoByMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return null;
		}
		TUserInfoExample tUserInfoExample = new TUserInfoExample();
		com.zl.pojo.TUserInfoExample.Criteria criteria = tUserInfoExample.createCriteria();
		criteria.andMobileEqualTo(mobile.trim());
		List<TUserInfo> list = this.userInfoMapperExt.selectByExample(tUserInfoExample);
		if (list == null || list.size() <= 0) {
			return null;
		}
		return list.get(0);
	}

	/*
	 * 
	 * <p>Title: getTUserByLogin</p> 
	 * <p>Description: 用户登录接口</p> 
	 * @param userName
	 * @param passWord
	 * @return 
	 * @see com.zl.client.user.TUserService#getTUserByLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public TUserVO getTUserByLogin(String userName, String passWord) {
		TUserExample tUserExample = new TUserExample();
		com.zl.pojo.TUserExample.Criteria  criteria = tUserExample.createCriteria();
		criteria.andUserNameEqualTo(userName.trim());
		criteria.andPasswordEqualTo(passWord.trim());
		List<TUser> list = this.userMapperExt.selectByExample(tUserExample);
		if (list == null || list.size()<=0) {
			return null;
		}
		TUser tUser = list.get(0);
		return this.getUserVOById(tUser.getId(), false, true);
	}

	/**
	 * 修改用户最后登录时间 by:is_zhoufeng
	 * @param userId
	 * @param lastLoginTime
	 */
	private void updateLastLoginTime(Long userId , Date lastLoginTime){
		TUser updateUser = new TUser() ;
		updateUser.setId(userId);
		updateUser.setLastLoginTime(lastLoginTime); 
		userMapperExt.updateByPrimaryKeySelective(updateUser);
	}

	@Override
	public List<TUserVO> getUserBaseInfoByUserNames(List<String> ids) {
		if(ids == null || ids.size() <= 0){
			return null ;
		} 
		return userMapperExt.getUserBaseInfoByUserNames(ids);
	}
}
