package com.zl.common.util.oss;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 互亿短信服务
 * @author is_zhoufeng
 */
public class HuYiSmsUtil {


	private static final Logger log = LoggerFactory.getLogger(HuYiSmsUtil.class);

	private static final String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

	private  String account  ;
	private  String password  ;
	
	public HuYiSmsUtil(String account , String password) {
		this.account = account ;
		this.password = password ;
	}

	
	/**
	 * 发送短信
	 * @param mobile 手机号
	 * @param content 内容（内容不能自定义，必须按照提交的模板格式发送）
	 * @return
	 */
	public boolean sendSms(String mobile , String content){
		/**
		 * 测试阶段只能使用下面格式的短信，其他格式会发送失败，审核通过后，将下面代码块注释掉
		 */
		{
			int mobile_code = (int)((Math.random()*9+1)*100000);
			content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
		}
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		NameValuePair[] data = {//提交短信
				new NameValuePair("account", account), 
				new NameValuePair("password", password), 
				new NameValuePair("mobile", mobile), 
				new NameValuePair("content", content),
		};
		method.setRequestBody(data);		
		try {
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			String info = String.format("code:[%s],msg:[%s],smsid:[%s]" ,code , msg , smsid);
			log.info(info);   
			if("2".equals(code)){  
				log.info("短信提交成功");
				return true ;
			}else{
				log.error("短信提交失败,{}" ,info);  
			}
		} catch (HttpException e) {
			log.error(e.getMessage() , e);
		} catch (IOException e) {
			log.error(e.getMessage() , e);
		} catch (DocumentException e) {
			log.error(e.getMessage() , e);
		}	
		return false ; 
	}

}
