package com.zl.service.userfeeds.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.userFeeds.UserFeedsService;
import com.zl.dao.mapper.TUserFeedsMapperExt;
import com.zl.pojo.Page;
import com.zl.pojo.TUserFeeds;
import com.zl.pojo.TUserFeedsExample;
import com.zl.pojo.TUserFeedsExample.Criteria;

@Service
public class UserFeedsServiceImpl implements UserFeedsService {

	@Autowired
	private TUserFeedsMapperExt userFeedsMapperExt;
 
	public Long insertTUserFeeds(TUserFeeds tUserFeeds) {
		Date date = new Date();
		tUserFeeds.setCreateTime(date);
		tUserFeeds.setModifyTime(date);
		this.userFeedsMapperExt.insert(tUserFeeds);
		return tUserFeeds.getId();
		
	}

	public int getUserFeedsCount4TUserFeedsExample(long userId, int greaterThanOrEqualToPicCount,long modifyTime) {

		TUserFeedsExample tUserFeedsExample = new TUserFeedsExample();
		Criteria criteria = tUserFeedsExample.createCriteria();
		criteria.andUserIdEqualTo(userId );
		if (greaterThanOrEqualToPicCount > 0) {
			criteria.andPageCountGreaterThanOrEqualTo(greaterThanOrEqualToPicCount);
		}
		if (modifyTime>0) {
			criteria.andModifyTimeGreaterThan(new Date(modifyTime));
		}
		tUserFeedsExample.setOrderByClause("CreateTime DESC");
		return this.userFeedsMapperExt.countByExample(tUserFeedsExample);
	}

	public List<TUserFeeds> getUserFeedsList4TUserFeedsExample(long userId, int greaterThanOrEqualToPicCount,long modifyTime, Page page) {
		TUserFeedsExample tUserFeedsExample = new TUserFeedsExample();
		Criteria criteria = tUserFeedsExample.createCriteria();
		criteria.andUserIdEqualTo(userId );
		if (greaterThanOrEqualToPicCount > 0) {
			criteria.andPageCountGreaterThanOrEqualTo(greaterThanOrEqualToPicCount);
		}
		if (modifyTime>0) {
			criteria.andModifyTimeGreaterThan(new Date(modifyTime));
		}
		tUserFeedsExample.setOrderByClause("CreateTime DESC");
		tUserFeedsExample.setPage(page);
		return this.userFeedsMapperExt.selectByExample(tUserFeedsExample);
	}

	public int addLike(Long id, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("addLike", count);
		this.userFeedsMapperExt.addLike(map);
		return 1;
	}

}
