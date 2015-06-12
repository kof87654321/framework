package com.zl.common;

import org.junit.Test;

import com.zl.common.util.oss.HuYiSmsUtil;

public class HuYiSmsTest {
	
	@Test
	public void testSend(){
		HuYiSmsUtil smsUtil = new HuYiSmsUtil("cf_kof123", "a12345678") ;
		boolean result = smsUtil.sendSms("18367131552", "测试账号无法自定义内容，只能发送固定格式的验证码") ;
		if(result){
			System.out.println("发送短信成功");
		}else{
			System.out.println("发送短信失败");  
		}
	}

}
