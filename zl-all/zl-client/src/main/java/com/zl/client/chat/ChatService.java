package com.zl.client.chat;

import java.util.List;

import com.zl.pojo.ChatQuery;
import com.zl.pojo.TChat;
import com.zl.vo.TChatVO;

public interface ChatService {

    public boolean sendMsg(TChat chat);

    public List<TChatVO> readMsg(ChatQuery query);
}
