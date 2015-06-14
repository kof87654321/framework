package com.zl.service.comment.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.comment.CommentService;
import com.zl.dao.mapper.TCommentMapperExt;
import com.zl.pojo.Page;
import com.zl.pojo.TComment;
import com.zl.pojo.TCommentExample;
import com.zl.pojo.TCommentExample.Criteria;
import com.zl.vo.TCommentCountVO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private TCommentMapperExt commentMapperExt;

	@Override
	public TComment insertTComment(TComment tComment) {
		int id = commentMapperExt.insert(tComment);
		tComment.setId((long) id);
		return tComment;
	}

	@Override
	public List<TComment> getListTComment4UserFeedsId(long userFeedsId, Page page) {
		TCommentExample commentExample = new TCommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andParentIdEqualTo(userFeedsId);
		// criteria.and
		commentExample.setPage(page);
		return this.commentMapperExt.selectByExample(commentExample);
	}

	@Override
	public int getCountTComment4UserFeedsIds(long userFeedsId) {
		TCommentExample commentExample = new TCommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andParentIdEqualTo(userFeedsId);
		// criteria.and
		return this.commentMapperExt.countByExample(commentExample);
	}

	@Override
	public List<TCommentCountVO> getTCommentVOList4UserFeedsIds(Long... userFeedsIds) {
		TCommentExample commentExample = new TCommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andParentIdIn(Arrays.asList(userFeedsIds));
		return this.commentMapperExt.countsByParentIdsAndExample(commentExample);
	}

}
