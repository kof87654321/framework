package com.zl.client.user;

import java.util.List;

import com.zl.pojo.TUserProfile;
import com.zl.vo.TUserVO;

public interface TUserService {

	public TUserVO getUserVOById(Long userId);

	public int updateUser(TUserVO tUserVO,boolean profile);
	
	public int updateTUserProfile(TUserProfile tUserProfile,long userId,long id);
	
	public List<TUserProfile> getTUserProfileList(long userId,long id);

	public long insertUser(TUserVO tUserVO);

}
