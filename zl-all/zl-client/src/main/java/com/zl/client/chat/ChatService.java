package com.zl.client.chat;

import java.util.List;

import com.zl.pojo.ChatQuery;
import com.zl.pojo.TChat;
import com.zl.vo.TChatVO;

/**
 * 聊天服务
 * 
 * @author zhangxianjun
 * @version $Id: ChatService.java, v 0.1 2015年6月15日 下午7:46:24 zhangxianjun Exp $
 */
public interface ChatService {

    /**
     * 发送消息
     * 
     * @param chat
     * @return
     */
    public boolean sendMsg(TChat chat);

    /**
     * 读消息
     * 聊天记录按时间戳来增量查询 app端记录上一次获取记录的第一条记录的modifyTime，作为since参数上传 max参数为当前时间？
     * 
     * @param query
     * @return
     */
    public List<TChatVO> readMsg(ChatQuery query);
}
