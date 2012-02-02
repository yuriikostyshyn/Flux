package com.flux.dbs.dao.impl;

import javax.persistence.EntityManager;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AccountDAOImplTest {
	
	private static final int ACCOUNT_ID= 1;
	
	private AccountDAOImpl underTest;
	
	@Mock
	private EntityManager entityManager;
	@Mock
	private Mapper mapper;
	
	
	@Before
	public void setUp(){
		underTest = new AccountDAOImpl();
		
		MockitoAnnotations.initMocks(this);
		
		underTest.setEntityManager(entityManager);
		underTest.setMapper(mapper);
	}
	
	@Test
	public void shouldCallMapperForEntities(){
	}
}
