package com.zl.client.userFeeds;

import java.util.List;

import com.zl.pojo.Page;
import com.zl.pojo.TUserFeeds;

public interface UserFeedsService {
	public Long insertTUserFeeds(TUserFeeds tUserFeeds);
	public List<TUserFeeds> getUserFeedsList4TUserFeedsExample(long userId, int greaterThanOrEqualToPicCount,long modifyTime,Page page);
	public int getUserFeedsCount4TUserFeedsExample(long userId, int greaterThanOrEqualToPicCount,long modifyTime);
	public int addPraise(Long id, int count);
	
}
