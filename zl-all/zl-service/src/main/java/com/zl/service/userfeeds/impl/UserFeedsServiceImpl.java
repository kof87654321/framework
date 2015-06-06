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

@Service
public class UserFeedsServiceImpl implements UserFeedsService {

	@Autowired
	private TUserFeedsMapperExt userFeedsMapperExt;

	public int insertTUserFeeds(TUserFeeds tUserFeeds) {
		Date date = new Date();
		tUserFeeds.setCreateTime(date);
		tUserFeeds.setModifyTime(date);
		return this.userFeedsMapperExt.insert(tUserFeeds);
	}

	public int getUserFeedsCount4TUserFeedsExample(TUserFeedsExample tUserFeedsExample) {
		return this.userFeedsMapperExt.countByExample(tUserFeedsExample);
	}

	public List<TUserFeeds> getUserFeedsList4TUserFeedsExample(TUserFeedsExample tUserFeedsExample) {
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
