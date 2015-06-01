package com.zl.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.user.UserService;
import com.zl.dao.mapper.LvuserMapperExt;
import com.zl.pojo.Lvuser;
import com.zl.pojo.LvuserExample;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private LvuserMapperExt lvuserMapper ;
	
	@Override
	public List<Lvuser> selectAll() {
		return lvuserMapper.selectByExample(new LvuserExample());   
	}

}
