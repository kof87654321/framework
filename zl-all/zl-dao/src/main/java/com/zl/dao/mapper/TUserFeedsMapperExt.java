package com.zl.dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 该类用于扩展com.zl.dao.mapper.TUserFeedsMapper接口 by is_zhoufeng@163.com 2015-06-05
 * 23:56:33
 */
public interface TUserFeedsMapperExt extends TUserFeedsMapper {
	void addPraise(Map<String, Object> map);

	void addCommentCount(@Param("addCommentCount") int addCommentCount, @Param("id") Long id);

	void addPraiseCount(@Param("addPraise") int addPraise, @Param("id") Long id);

	void updateStatus4TComments(@Param("ids") Long[] ids, @Param("status") int status, @Param("oldstatus") int oldstatus);

	void updateStatus4TUserFeedsId(@Param("tUserFeedsId") long tUserFeedsId, @Param("status") int status,
			@Param("oldstatus") int oldstatus);

}