package com.zl.dao.mapper;

import java.util.List;

import com.zl.pojo.TCommentExample;
import com.zl.vo.TCommentCountVO;

/**
 * 该类用于扩展com.zl.dao.mapper.TCommentMapper接口
 * by is_zhoufeng@163.com 2015-06-05 23:56:33
 */
public interface TCommentMapperExt extends TCommentMapper {
	 List<TCommentCountVO> countsByParentIdsAndExample(TCommentExample example);
}