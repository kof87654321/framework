package com.zl.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.zl.pojo.TVersion;

/**
 * 该类用于扩展com.zl.dao.mapper.TVersionMapper接口
 * by is_zhoufeng@163.com 2015-06-06 22:21:31
 */
public interface TVersionMapperExt extends TVersionMapper {
	
	TVersion selectNewVersionByAppId(@Param("appId") Integer appId); 
	
}