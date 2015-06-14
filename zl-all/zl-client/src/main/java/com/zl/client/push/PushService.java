package com.zl.client.push;

import java.util.List;

import com.zl.client.push.vo.PushMessage;

/**
 * 消息推送接口
 * @author is_zhoufeng
 *
 */
public interface PushService {
	
	/**
	 * 单播
	 * @param userId 接收消息的用户ID
	 * @param message 要推送的消息
	 */
	void unicast(Long userId , PushMessage message);

	/**
	 * 多播
	 * @param userIds 要接收消息的用户列表（友盟要求不超过500）
	 * @param message 要推送的消息
	 */
	void listcast(List<Long> userIds ,PushMessage message);
	
	/**
	 * 广播（所有用户手动啊）
	 * @param message 要推送的消息
	 */
	void groupcast(PushMessage message);
	
	
	
}
