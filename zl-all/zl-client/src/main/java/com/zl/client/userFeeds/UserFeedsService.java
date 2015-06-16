package com.zl.client.userFeeds;

import java.util.List;

import com.zl.pojo.Page;
import com.zl.pojo.TUserFeeds;

/**
 * 用户动态service
 * 
 * @author youbush
 *
 */
public interface UserFeedsService {
	public Long insertTUserFeeds(TUserFeeds tUserFeeds);

	public List<TUserFeeds> getUserFeedsList4TUserFeedsExample(long userId, int greaterThanOrEqualToPicCount,
			long modifyTime, Page page);

	public int getUserFeedsCount4TUserFeedsExample(long userId, int greaterThanOrEqualToPicCount, long modifyTime);

	public int addPraise(Long id, int count);

	public TUserFeeds getTUserFeedsById(long id);

	public int addCommentCount(Long id, int count);

	public void updateStatus4TComments(int status, int oldstatus, Long... ids);

	public void updateStatus4TUserFeedsId(int status, int oldstatus, long tUserFeedsId);

}
