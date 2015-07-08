package com.zl.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zl.vo.TUserVO;

/**
 * 该类用于扩展com.zl.dao.mapper.TUserMapper接口
 * by is_zhoufeng@163.com 2015-06-05 23:56:33
 */
public interface TUserMapperExt extends TUserMapper {
	
	List<TUserVO> getUserBaseInfoByUserNames(@Param("userNames") List<String> userNames);
	
}