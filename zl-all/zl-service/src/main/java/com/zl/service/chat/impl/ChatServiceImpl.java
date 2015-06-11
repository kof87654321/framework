package com.zl.service.chat.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.chat.ChatService;
import com.zl.dao.mapper.TChatMapperExt;
import com.zl.pojo.ChatQuery;
import com.zl.pojo.Page;
import com.zl.pojo.TChat;
import com.zl.pojo.TChatExample;
import com.zl.pojo.TChatExample.Criteria;

/**
 * 聊天服务
 * 
 * @author zhangxianjun
 * @version $Id: ChatServiceImpl.java, v 0.1 2015年6月11日 下午3:32:41 zhangxianjun Exp $
 */
@Service
public class ChatServiceImpl implements ChatService {

    private static final Set<Long>         onLine = new HashSet<Long>();

    private static final Map<Long, String> tips   = new HashMap<Long, String>();

    @Autowired
    private TChatMapperExt                 tChatMapperExt;

    @Override
    public boolean sendMsg(TChat chat) {
        if (chat == null) {
            return false;
        }
        if (chat.getFromId() == null || chat.getToId() == null) {
            return false;
        }
        if (chat.getType() == null) {
            chat.setType(1);
        }
        Date now = new Date();
        chat.setCreateTime(now);
        chat.setModifyTime(now);
        return tChatMapperExt.insert(chat) > 0;
    }

    /**
     * 聊天记录按时间戳来增量查询
     * app端记录上一次获取记录的第一条记录的modifyTime，作为since参数上传
     * max参数为当前时间？
     * @see com.zl.client.chat.ChatService#readMsg(com.zl.pojo.ChatQuery)
     */
    @Override
    public List<TChat> readMsg(ChatQuery query) {
        if (query.getUserId() == null) {
            return null;
        }
        TChatExample condition = new TChatExample();
        Criteria criteria = condition.createCriteria();
        criteria.andToIdEqualTo(query.getUserId());
        if (query.getSince() != null) {
            criteria.andModifyTimeGreaterThanOrEqualTo(query.getSince());
        }
        if (query.getMax() != null) {
            criteria.andModifyTimeLessThanOrEqualTo(query.getMax());
        }
        Page page = new Page();
        page.setBegin(query.getOffset());
        page.setLength(query.getLimit());
        condition.setPage(page);
        condition.setOrderByClause("CreateTime DESC");
        return tChatMapperExt.selectByExample(condition);
    }
}
