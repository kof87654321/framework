package com.zl.service;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zl.client.chat.ChatService;
import com.zl.pojo.ChatQuery;
import com.zl.pojo.Page;
import com.zl.pojo.TChat;

/**
 * 测试VersionService示例
 * @author is_zhoufeng
 *
 */
public class ChatServiceTest extends BaseServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ChatServiceTest.class);

    @Autowired
    private ChatService         chatService;

    @Test
    public void testSend() {
        TChat chat = new TChat();
        chat.setFromId(5L);
        chat.setToId(6L);
        chat.setStatus(0);
        chat.setMessage("hi,约吗");
        boolean result = chatService.sendMsg(chat);
        System.out.println(result);
    }

    @Test
    public void testRead() {
        ChatQuery query = new ChatQuery();
        query.setUserId(6L);
        query.setPage(new Page().setPageByPageNoAndPageSize(1, 10));
        List<TChat> result = chatService.readMsg(query);
        System.out.println(result == null);
        if (result != null) {
            System.out.println(result.size());
            for (TChat chat : result) {
                System.out.println(chat.getCreateTime());
            }
        }
    }

}
