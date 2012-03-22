package com.flux.dbs.dao.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flux.domain.Account;
import com.flux.persistence.entities.AccountEntity;

public class AccountDAOImplTest {

	private static final int USER_ID = 1;

	@Mock
	private EntityManager entityManagerMock;
	@Mock
	private Mapper mapperMock;
	@Mock
	private Query getAccountsQueryMock;
	
	private AccountDAOImpl underTest;
	private List<AccountEntity> resultAccountEntities;
	private AccountEntity resultAccountEntity;


	@Before
	public void setUp() {
		underTest = new AccountDAOImpl();
		resultAccountEntities = new ArrayList<AccountEntity>();
		resultAccountEntity = new AccountEntity();
		resultAccountEntities.add(resultAccountEntity);

		MockitoAnnotations.initMocks(this);
		underTest.setEntityManager(entityManagerMock);
		underTest.setMapper(mapperMock);

		when(entityManagerMock.createNamedQuery(AccountDAOImpl.GET_ACCOUNTS_BY_USER_ID_QUERY_NAME)).thenReturn(
				getAccountsQueryMock);
		when(getAccountsQueryMock.getResultList()).thenReturn(resultAccountEntities);
	}

	@Test
	public void shouldCallMapperForEntities() {
		underTest.getAccountsByUserId(USER_ID);
		verify(mapperMock, times(1)).map(resultAccountEntity, Account.class);
	}

	@Test
	public void shouldReturnListOfAccountInstances() {
		when(mapperMock.map(resultAccountEntity, Account.class)).thenReturn(new Account());
		Object resultList = underTest.getAccountsByUserId(USER_ID);
		Assert.assertEquals(ArrayList.class, resultList.getClass());

		Object resultAccountInstance = ((List) resultList).get(0);
		Assert.assertEquals(Account.class, resultAccountInstance.getClass());

	}
}
