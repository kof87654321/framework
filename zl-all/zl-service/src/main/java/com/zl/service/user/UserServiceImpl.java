package com.zl.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.user.UserService;
import com.zl.dao.mapper.LvuserMapperExt;
import com.zl.pojo.Lvuser;
import com.zl.pojo.LvuserExample;
@Deprecated
@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


	@Autowired
	private LvuserMapperExt lvuserMapper ;
	
	@Override
	public List<Lvuser> selectAll() {
		log.info("com.zl.service.user.UserServiceImpl.selectAll()...");
		return lvuserMapper.selectByExample(new LvuserExample());   
	}

}
