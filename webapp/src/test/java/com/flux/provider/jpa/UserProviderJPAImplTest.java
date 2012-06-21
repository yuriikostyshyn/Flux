package com.flux.provider.jpa;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flux.dbs.dao.UserDAO;
import com.flux.domain.User;

import static org.mockito.Mockito.when;

public class UserProviderJPAImplTest {
	private static final String LOGIN = "login string";
	private static final String PASSWORD = "password string";


	@Mock
	private UserDAO mockUserDAO;
	@Mock
	private User mockUser;

	
	UserProviderJPAImpl underTest;
	
	@Before
	public void setUp(){
		underTest = new UserProviderJPAImpl();
		
		MockitoAnnotations.initMocks(this);
		
	}
	
	
	@Test
	public void shouldReturnUserInstanceThatWasReturnedByDAO(){
		
	}
}
