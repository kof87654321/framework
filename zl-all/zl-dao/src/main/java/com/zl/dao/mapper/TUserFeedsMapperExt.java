package com.zl.dao.mapper;

import java.util.Map;

/**
 * 该类用于扩展com.zl.dao.mapper.TUserFeedsMapper接口 by is_zhoufeng@163.com 2015-06-05
 * 23:56:33
 */
public interface TUserFeedsMapperExt extends TUserFeedsMapper {
	void addLike(Map<String, Object> map);
}