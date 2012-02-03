package com.flux.dbs.dao.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flux.domain.Account;
import com.flux.domain.User;
import com.flux.persistence.entities.AccountEntity;

public class AccountDAOImplTest {
	
	private static final int USER_ID = 1;
	private static final int ACCOUNT_ID= 1;
	
	private AccountDAOImpl underTest;
	private User givenUser;
	private List<AccountEntity> resultAccountEntities;
	private AccountEntity resultAccountEntity;
	
	@Mock
	private EntityManager entityManagerMock;
	@Mock
	private Mapper mapperMock;
	@Mock
	private Query getAccountsQueryMock;
	
	
	@Before
	public void setUp(){
		underTest = new AccountDAOImpl();
		resultAccountEntities = new ArrayList<AccountEntity>();
		resultAccountEntity = new AccountEntity();
		resultAccountEntities.add(resultAccountEntity);
		
		MockitoAnnotations.initMocks(this);
		underTest.setEntityManager(entityManagerMock);
		underTest.setMapper(mapperMock);
	}
	
	@Test
	public void shouldCallMapperForEntities(){
		when(entityManagerMock.createNamedQuery(AccountDAOImpl.GET_ACCOUNTS_BY_USER_ID_QUERY_NAME)).thenReturn(getAccountsQueryMock);
		when(getAccountsQueryMock.getResultList()).thenReturn(resultAccountEntities);
		underTest.getAccountsByUserId(USER_ID);
		verify(mapperMock, times(1)).map(resultAccountEntity, Account.class);
	}
}
