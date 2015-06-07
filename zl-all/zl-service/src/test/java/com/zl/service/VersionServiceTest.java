package com.zl.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zl.client.verson.VersionService;
import com.zl.pojo.TVersion;

/**
 * 测试VersionService示例
 * @author is_zhoufeng
 *
 */
public class VersionServiceTest extends BaseServiceTest{
	
	private static final Logger log = LoggerFactory.getLogger(VersionServiceTest.class);

	@Autowired
	private VersionService versionService ;
	
	@Test
	public void testGetNewVersionByAppId(){
		TVersion version = versionService.getNewVersionByAppId(1);
		if(version == null){
			log.info("没查询到结果");
		}else{
			log.info("appId:{},appName:{},content:{},downUrl:{},version:{}"
					,version.getAppId(),version.getAppnName(),version.getContent()
					,version.getDownUrl(),version.getVersion()); 
		}
	}
	
	@Test
	public void testInsertVersion(){
		TVersion version = new TVersion() ;
		version.setAppId(1);
		version.setAppnName("有样App");
		version.setAttributes("{a:\"1\",b:\"2\"}");
		version.setContent("测试内容。。。。");
		version.setDownUrl("http://www.aclili.com");
		version.setForceUpdate(false);
		version.setVersion("2.0"); 
		versionService.insertVersion(version);
		if(version.getId() != null ){
			log.info("插入数据成功");
		}else{
			log.error("插入数据失败");  
		}
	}
	
}
