package com.zl.client.chat;

import java.util.List;

import com.zl.pojo.ChatQuery;
import com.zl.pojo.TChat;

public interface ChatService {

    public boolean sendMsg(TChat chat);

    public List<TChat> readMsg(ChatQuery query);
}
