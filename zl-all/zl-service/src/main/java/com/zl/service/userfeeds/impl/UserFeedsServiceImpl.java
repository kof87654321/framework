package com.zl.service.userfeeds.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.userFeeds.UserFeedsService;
import com.zl.common.util.Constant;
import com.zl.dao.mapper.TUserFeedsMapperExt;
import com.zl.pojo.Page;
import com.zl.pojo.TUserFeeds;
import com.zl.pojo.TUserFeedsExample;
import com.zl.pojo.TUserFeedsExample.Criteria;

/**
 * 
 * @ClassName: UserFeedsServiceImpl
 * @Description: 用户动态service
 * @author youbush
 * @date 2015年6月15日 下午8:28:49
 *
 */
@Service
public class UserFeedsServiceImpl implements UserFeedsService {

	@Autowired
	private TUserFeedsMapperExt userFeedsMapperExt;

	/*
	 * 
	 * <p>Title: insertTUserFeeds</p> <p>Description: 插入单条用户动态</p>
	 * 
	 * @param tUserFeeds
	 * 
	 * @return
	 * 
	 * @see
	 * com.zl.client.userFeeds.UserFeedsService#insertTUserFeeds(com.zl.pojo
	 * .TUserFeeds)
	 */
	public Long insertTUserFeeds(TUserFeeds tUserFeeds) {
		Date date = new Date();
		tUserFeeds.setCreateTime(date);
		tUserFeeds.setModifyTime(date);
		this.userFeedsMapperExt.insert(tUserFeeds);
		return tUserFeeds.getId();

	}

	/*
	 * 
	 * <p>Title: getUserFeedsCount4TUserFeedsExample</p> <p>Description:
	 * 根据查询属性查询用户动态总数</p>
	 * 
	 * @param userId
	 * 
	 * @param greaterThanOrEqualToPicCount
	 * 
	 * @param modifyTime
	 * 
	 * @return
	 * 
	 * @see
	 * com.zl.client.userFeeds.UserFeedsService#getUserFeedsCount4TUserFeedsExample
	 * (long, int, long)
	 */
	public int getUserFeedsCount4TUserFeedsExample(long userId, int greaterThanOrEqualToPicCount, long modifyTime) {

		TUserFeedsExample tUserFeedsExample = new TUserFeedsExample();
		Criteria criteria = tUserFeedsExample.createCriteria();
		if (userId > 0) {
			criteria.andUserIdEqualTo(userId);
		}
		if (greaterThanOrEqualToPicCount > 0) {
			criteria.andPageCountGreaterThanOrEqualTo(greaterThanOrEqualToPicCount);
		}
		if (modifyTime > 0) {
			criteria.andModifyTimeGreaterThan(new Date(modifyTime));
		}
		criteria.andStatusNotEqualTo(Constant.STATUS.DELETE) ;
		tUserFeedsExample.setOrderByClause("ModifyTime DESC");
		return this.userFeedsMapperExt.countByExample(tUserFeedsExample);
	}

	/*
	 * 
	 * <p>Title: getUserFeedsList4TUserFeedsExample</p> <p>Description:
	 * 根据查询属性，查询用户动态列表</p>
	 * 
	 * @param userId
	 * 
	 * @param greaterThanOrEqualToPicCount
	 * 
	 * @param modifyTime
	 * 
	 * @param page
	 * 
	 * @return
	 * 
	 * @see
	 * com.zl.client.userFeeds.UserFeedsService#getUserFeedsList4TUserFeedsExample
	 * (long, int, long, com.zl.pojo.Page)
	 */
	public List<TUserFeeds> getUserFeedsList4TUserFeedsExample(long userId, int greaterThanOrEqualToPicCount,
			long modifyTime, Page page) {
		TUserFeedsExample tUserFeedsExample = new TUserFeedsExample();
		Criteria criteria = tUserFeedsExample.createCriteria();
		if (userId > 0) {
			criteria.andUserIdEqualTo(userId);
		}
		if (greaterThanOrEqualToPicCount > 0) {
			criteria.andPageCountGreaterThanOrEqualTo(greaterThanOrEqualToPicCount);
		}
		if (modifyTime > 0) {
			criteria.andModifyTimeGreaterThan(new Date(modifyTime));
		}
		criteria.andStatusNotEqualTo(Constant.STATUS.DELETE) ;
		tUserFeedsExample.setOrderByClause("ModifyTime DESC");
		tUserFeedsExample.setPage(page);
		return this.userFeedsMapperExt.selectByExample(tUserFeedsExample);
	}

	/*
	 * 
	 * <p>Title: addPraise</p> <p>Description:点赞异步接口 </p>
	 * 
	 * @param id
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see com.zl.client.userFeeds.UserFeedsService#addPraise(java.lang.Long,
	 * int)
	 */
	public int addPraise(Long id, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("addPraise", count);
		this.userFeedsMapperExt.addPraise(map);
		return 1;
	}

	/*
	 * 
	 * <p>Title: addCommentCount</p> <p>Description: 增加动态的评论总数接口</p>
	 * 
	 * @param id
	 * 
	 * @param count
	 * 
	 * @return
	 * 
	 * @see
	 * com.zl.client.userFeeds.UserFeedsService#addCommentCount(java.lang.Long,
	 * int)
	 */
	public int addCommentCount(Long id, int count) {
		this.userFeedsMapperExt.addCommentCount(count, id);
		return 1;
	}

	/*
	 * 
	 * <p>Title: getTUserFeedsById</p> <p>Description: 根据主键查询动态对象</p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see com.zl.client.userFeeds.UserFeedsService#getTUserFeedsById(long)
	 */
	@Override
	public TUserFeeds getTUserFeedsById(long id) {
		return this.userFeedsMapperExt.selectByPrimaryKey(id);
	}

	@Override
	public void updateStatus4TComments(int status, int oldstatus, Long... ids) {
		this.userFeedsMapperExt.updateStatus4TComments(ids, status, oldstatus);
	}

	@Override
	public int updateStatus4TUserFeedsId(Long userId , long tUserFeedsId ,int status) {
		return this.userFeedsMapperExt.updateStatus4TUserFeedsId(tUserFeedsId, userId , status); 
	}


}
