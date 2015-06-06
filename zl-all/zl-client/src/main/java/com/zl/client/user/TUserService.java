package com.zl.client.user;

import com.zl.vo.TUserVO;

public interface TUserService {

	public TUserVO getUserVOById(Long userId);

	public int updateUser(TUserVO tUserVO);

	public int insertUser(TUserVO tUserVO);

}
