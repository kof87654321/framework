package com.zl.client.userFeeds;

import java.util.List;

import com.zl.pojo.Page;
import com.zl.pojo.TUserFeeds;

public interface UserFeedsService {
	public int insertTUserFeeds(TUserFeeds tUserFeeds);
	public List<TUserFeeds> getUserFeedsList4TUserFeedsExample(long userId, int bigPageCount, Page page);
	public int getUserFeedsCount4TUserFeedsExample(long userId, int bigPageCount);
	public int addLike(Long id, int count);
	
}