package com.zl.client.push.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 要推送的消息
 * @author is_zhoufeng
 *
 */
public class PushMessage {

	/**
	 * 消息类型
	 */
	private int type ;
	
	/**
	 * 必填，通知栏文字描述
	 */
	private String ticker ;
	
	/**
	 * 必填，通知栏标题
	 */
	private String title ;
	
	/**
	 * 必填，通知文字描述
	 */
	private String text ;
	
	/**
	 * 消息其他参数
	 */
	private Map<String, String> attributes = new HashMap<String, String>();

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	} 
	
}
