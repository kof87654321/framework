package com.zl.common;

import java.util.Date;

import org.junit.Test;

import com.zl.common.util.token.TokenUtils;

public class TokenTest {
	
	@Test
	public void test01(){
		String token = TokenUtils.getToken(1L, "111111", new Date()) ;
		System.out.println(token); 
	}

	@Test
	public void test02(){
		boolean result = TokenUtils.checkToken(1L, "111111", "RNLZsZo47oW6Yej+uYpbhqEwmU7Hol8Y");
		System.out.println(result); 
	}
	
}
