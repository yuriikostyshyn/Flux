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
import com.flux.domain.Currency;
import com.flux.domain.Transaction;
import com.flux.persistence.entities.TransactionEntity;

public class TransactionDAOImplTest {
	private static final long ACCOUNT_ID = 1;

	@Mock
	private EntityManager entityManagerMock;
	@Mock
	private Query getTransactionsQueryMock;
	@Mock
	private Mapper mapperMock;

	private TransactionDAOImpl underTest;
	private List<TransactionEntity> resultTransactionEntities;
	private TransactionEntity resultTransactionEntity;

	@Before
	public void setUp() {
		underTest = new TransactionDAOImpl();
		resultTransactionEntities = new ArrayList<TransactionEntity>();
		resultTransactionEntity = new TransactionEntity();
		resultTransactionEntities.add(resultTransactionEntity);

		MockitoAnnotations.initMocks(this);
		underTest.setEntityManager(entityManagerMock);
		underTest.setMapper(mapperMock);

		when(entityManagerMock.createNamedQuery(TransactionDAOImpl.GET_TRANSACTIONS_BY_ACCOUNT_TO_ID_QUERY_NAME))
				.thenReturn(getTransactionsQueryMock);
		when(getTransactionsQueryMock.getResultList()).thenReturn(resultTransactionEntities);
	}

	@Test
	public void shouldCallMapperForTransaction() {
		underTest.getTransactionsByAccountToId(ACCOUNT_ID);
		verify(mapperMock, times(1)).map(resultTransactionEntity, Transaction.class);
	}

	@Test
	public void shouldReturnListOfTransactionInstances() {
		when(mapperMock.map(resultTransactionEntity, Transaction.class)).thenReturn(new Transaction());
		Object resultList = underTest.getTransactionsByAccountToId(ACCOUNT_ID);
		Assert.assertEquals(ArrayList.class, resultList.getClass());

		Object resultTransactionInstance = ((List) resultList).get(0);
		Assert.assertEquals(Transaction.class, resultTransactionInstance.getClass());

	}
}
