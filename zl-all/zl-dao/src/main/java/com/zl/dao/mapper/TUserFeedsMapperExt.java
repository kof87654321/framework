package com.zl.dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 该类用于扩展com.zl.dao.mapper.TUserFeedsMapper接口 by is_zhoufeng@163.com 2015-06-05
 * 23:56:33
 */
public interface TUserFeedsMapperExt extends TUserFeedsMapper {
	void addLike(Map<String, Object> map);
	
	void addLikeCount(@Param("addLike") int addLike ,@Param("id") Long id);
	
}