package com.zl.service.comment.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.comment.CommentService;
import com.zl.common.util.Constant;
import com.zl.common.util.ListUtil;
import com.zl.dao.mapper.TCommentMapperExt;
import com.zl.pojo.Page;
import com.zl.pojo.TComment;
import com.zl.pojo.TCommentExample;
import com.zl.pojo.TCommentExample.Criteria;
import com.zl.vo.TCommentCountVO;

/**
 * 
* @ClassName: CommentServiceImpl 
* @Description: 评论service
* @author youbush
* @date 2015年6月15日 下午8:32:39 
*
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private TCommentMapperExt commentMapperExt;

	/*
	 * 
	* <p>Title: insertTComment</p> 
	* <p>Description: 插入单条评论</p> 
	* @param tComment
	* @return 
	* @see com.zl.client.comment.CommentService#insertTComment(com.zl.pojo.TComment)
	 */
	@Override
	public TComment insertTComment(TComment tComment) {
		commentMapperExt.insert(tComment);
		return tComment;
	}

	/*
	 * 
	* <p>Title: getListTComment4UserFeedsId</p> 
	* <p>Description: 根据动态id，查询评论的列表</p> 
	* @param userFeedsId
	* @param page
	* @return 
	* @see com.zl.client.comment.CommentService#getListTComment4UserFeedsId(long, com.zl.pojo.Page)
	 */
	@Override
	public List<TComment> getListTComment4UserFeedsId(long userFeedsId, Integer[] types, Page page) {
		TCommentExample commentExample = new TCommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andParentIdEqualTo(userFeedsId);
		if (types!=null) {
			criteria.andTypeIn(Arrays.asList(types));
		}
		criteria.andStatusIn(ListUtil.getIntegerList(Constant.STATUS.CHECKED, Constant.STATUS.NOMARL));
		// criteria.and
		commentExample.setPage(page);
		return this.commentMapperExt.selectByExample(commentExample);
	}

	/*
	 * 
	* <p>Title: getCountTComment4UserFeedsIds</p> 
	* <p>Description: 根据动态id，查询评论的总数</p> 
	* @param userFeedsId
	* @return 
	* @see com.zl.client.comment.CommentService#getCountTComment4UserFeedsIds(long)
	 */
	@Override
	public int getCountTComment4UserFeedsIds(long userFeedsId) {
		TCommentExample commentExample = new TCommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andParentIdEqualTo(userFeedsId);
		criteria.andStatusIn(ListUtil.getIntegerList(Constant.STATUS.CHECKED, Constant.STATUS.NOMARL));
		// criteria.and
		return this.commentMapperExt.countByExample(commentExample);
	}

	/*
	 * 
	* <p>Title: getTCommentVOList4UserFeedsIds</p> 
	* <p>Description: 根据评论ids，查询每个ids的TCommentCountVO</p> 
	* @param userFeedsIds
	* @return 
	* @see com.zl.client.comment.CommentService#getTCommentVOList4UserFeedsIds(java.lang.Long[])
	 */
	@Override
	public List<TCommentCountVO> getTCommentVOList4UserFeedsIds(Long... userFeedsIds) {
		TCommentExample commentExample = new TCommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andParentIdIn(Arrays.asList(userFeedsIds));
		return this.commentMapperExt.countsByParentIdsAndExample(commentExample);
	}

	@Override
	public int getCountTComment4UserId(long userId, long modifyTime,Integer[] types) {
		TCommentExample commentExample = new TCommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andAuthorIdEqualTo(userId);
		if (types!=null) {
			criteria.andTypeIn(Arrays.asList(types));
		}
		if (modifyTime>0) {
			criteria.andModifyTimeGreaterThan(new Date(modifyTime));
		}
		
		criteria.andStatusIn(ListUtil.getIntegerList(Constant.STATUS.CHECKED, Constant.STATUS.NOMARL));
		// criteria.and
		return this.commentMapperExt.countByExample(commentExample);
	}

	@Override
	public List<TComment> getListTComment4UserId(long userId, long modifyTime,Integer[] types, Page page) {
		TCommentExample commentExample = new TCommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andAuthorIdEqualTo(userId);
		if (types!=null) {
			criteria.andTypeIn(Arrays.asList(types));
		}
		if (modifyTime>0) {
			criteria.andModifyTimeGreaterThan(new Date(modifyTime));
		}
		
		criteria.andStatusIn(ListUtil.getIntegerList(Constant.STATUS.CHECKED, Constant.STATUS.NOMARL));
		commentExample.setPage(page);
		return this.commentMapperExt.selectByExample(commentExample);
	}

}
