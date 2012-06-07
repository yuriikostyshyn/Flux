package com.flux.manager;

import org.junit.Before;
import org.junit.Test;

public class AuthenticationManagerTest {
	private AuthenticationManager underTest;
	
	@Before
	public void setUp(){
		underTest = new AuthenticationManager();
	}
	
	@Test
	public void shouldAddErrorMessageToModelIfCredentialsAreNotCorrect(){
		
	}
}
