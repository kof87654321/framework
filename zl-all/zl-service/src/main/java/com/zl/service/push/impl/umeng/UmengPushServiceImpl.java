package com.zl.service.push.impl.umeng;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import push.android.AndroidUnicast;

import com.zl.client.push.PushService;
import com.zl.client.push.vo.PushMessage;
import com.zl.common.util.bit.PropertiesConfigure;

/**
 * 友盟消息推送实现
 * @author is_zhoufeng
 *
 */
@Service
public class UmengPushServiceImpl implements PushService , InitializingBean{

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(UmengPushServiceImpl.class);

	@Autowired
	private PropertiesConfigure propertiesConfigure ;

	//--------------相关配置参数------------
	private String androidKey ;
	private String androidKeyMaster ;
	private String iosKey ;
	private String iosKeyMaster ;
	private String productionMode ;
	//             ----------

	@Override
	public void unicast(Long userId, PushMessage message) {
		if(userId == null || message == null){
			return ;
		}
		try {
			
			//发送Android
			{
				AndroidUnicast unicast = new AndroidUnicast();
				unicast.setAppMasterSecret(androidKeyMaster);
				unicast.setPredefinedKeyValue("appkey", androidKey);
				unicast.setPredefinedKeyValue("timestamp", String.valueOf(new Date().getTime()));  
				unicast.setPredefinedKeyValue("device_tokens", String.valueOf(userId)); 
				unicast.setPredefinedKeyValue("ticker", message.getTicker());
				unicast.setPredefinedKeyValue("title",  message.getTitle());
				unicast.setPredefinedKeyValue("text",   message.getText());
				unicast.setPredefinedKeyValue("production_mode", productionMode);
				
				if(message.getAttributes() != null){
					Iterator<Entry<String, String>>  attrIt = message.getAttributes().entrySet().iterator();
					Entry<String, String> entry ;
					String key ;
					String value ;
					while(attrIt.hasNext()){
						entry = attrIt.next();
						key = entry.getKey();
						value = entry.getValue();
						unicast.setPredefinedKeyValue(key, value); 
					}
				}
				unicast.send();
			}
			
			//发送Ios
			{
				//TODO
			}
		} catch (Exception e) {
			log.error("Umeng消息推送失败" , e); 
		}

	}

	@Override
	public void listcast(List<Long> userIds, PushMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcast(PushMessage message) {

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		androidKey = propertiesConfigure.getProperties("umeng.push.android.appkey");
		androidKeyMaster = propertiesConfigure.getProperties("umeng.push.android.keymaster");
		iosKey = propertiesConfigure.getProperties("umeng.push.ios.appkey");
		iosKeyMaster = propertiesConfigure.getProperties("umeng.push.ios.keymaster");
		productionMode = propertiesConfigure.getProperties("umeng.push.production.mode");
	}



}
