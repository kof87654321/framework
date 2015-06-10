package com.zl.service.chat.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.chat.ChatService;
import com.zl.dao.mapper.TChatMapperExt;
import com.zl.pojo.TChat;
import com.zl.pojo.TChatExample;
import com.zl.pojo.TChatExample.Criteria;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Set<Long>         onLine = new HashSet<Long>();

    private static final Map<Long, String> tips   = new HashMap<Long, String>();

    @Autowired
    private TChatMapperExt                 tChatMapperExt;

    @Override
    public boolean sendMsg(TChat chat) {
        if (onLine.contains(chat.getToId())) {
            int result = tChatMapperExt.insert(chat);
            return result > 1;
        } else {
            //push msg
            return false;
        }
    }

    @Override
    public List<TChat> readMsg(TChat chat) {
        TChatExample query = new TChatExample();
        Criteria criteria = query.createCriteria();
        criteria.andToIdEqualTo(chat.getToId());
        criteria.andStatusEqualTo(chat.getStatus());
        return tChatMapperExt.selectByExample(query);
    }
}
