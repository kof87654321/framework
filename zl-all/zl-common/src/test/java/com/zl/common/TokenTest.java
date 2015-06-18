package com.zl.common;

import java.util.Date;

import org.junit.Test;

import com.zl.common.util.token.TokenUtils;

public class TokenTest {
	
	@Test
	public void test01(){
		String token = TokenUtils.getToken(1L, "111111", new Date()) ;
		System.out.println(token.trim()); 
	}

	@Test
	public void test02(){
		boolean result = TokenUtils.checkToken(1L, "111111", "RNLZsZo47oWTt2u2aEOW4bv0msERMfpI");
		System.out.println(result); 
	}
	
}
