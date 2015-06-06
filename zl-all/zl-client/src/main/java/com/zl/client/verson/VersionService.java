package com.zl.client.verson;

import com.zl.pojo.TVersion;

/**
 * App版本服务
 * @author is_zhoufeng
 *
 */
public interface VersionService {

	void insertVersion(TVersion version);
	
	TVersion getNewVersionByAppId(Integer appId);
	
	
}
