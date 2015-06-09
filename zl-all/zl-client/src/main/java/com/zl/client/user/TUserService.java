package com.zl.client.user;

import java.util.List;

import com.zl.pojo.TUserProfile;
import com.zl.vo.TUserVO;

public interface TUserService {

	public TUserVO getUserVOById(Long userId,boolean profile,boolean token);

	public TUserVO updateUser(TUserVO tUserVO,boolean profile);
	
	public int updateTUserProfile(TUserProfile tUserProfile,long userId,long id);
	
	public List<TUserProfile> getTUserProfileList(long userId,long id);

	public TUserVO insertUser(TUserVO tUserVO);

}
