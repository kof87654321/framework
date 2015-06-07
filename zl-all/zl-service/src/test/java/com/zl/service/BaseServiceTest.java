package com.zl.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类
 * @author is_zhoufeng@163.com
 * 2014年12月22日 下午11:29:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"classpath*:test-applicationContext.xml"}		
		)
public abstract class BaseServiceTest {
	
	
	
}
