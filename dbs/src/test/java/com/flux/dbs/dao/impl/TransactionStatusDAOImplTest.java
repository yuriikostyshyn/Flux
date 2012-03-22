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

import com.flux.domain.TransactionStatus;
import com.flux.persistence.entities.TransactionStatusEntity;

public class TransactionStatusDAOImplTest {
	private static final int STATUS_ID = 1;

	@Mock
	private EntityManager entityManagerMock;
	@Mock
	private Query getTransactionStatusQueryMock;
	@Mock
	private Mapper mapperMock;

	private TransactionStatusDAOImpl underTest;
	private List<TransactionStatusEntity> resultTransactionStatusEntities;
	private TransactionStatusEntity resultTransactionStatusEntity;

	@Before
	public void setUp() {
		underTest = new TransactionStatusDAOImpl();
		resultTransactionStatusEntities = new ArrayList<TransactionStatusEntity>();
		resultTransactionStatusEntity = new TransactionStatusEntity();
		resultTransactionStatusEntities.add(resultTransactionStatusEntity);

		MockitoAnnotations.initMocks(this);
		underTest.setEntityManager(entityManagerMock);
		underTest.setMapper(mapperMock);

		when(entityManagerMock.createNamedQuery(TransactionStatusDAOImpl.GET_STATUS_BY_ID_QUERY_NAME)).thenReturn(
				getTransactionStatusQueryMock);
		when(getTransactionStatusQueryMock.getResultList()).thenReturn(resultTransactionStatusEntities);
	}

	@Test
	public void shouldCallMapperForTransaction() {
		underTest.getStatusById(STATUS_ID);
		verify(mapperMock, times(1)).map(resultTransactionStatusEntity, TransactionStatus.class);
	}

	@Test
	public void shouldReturnTransactionStatusInstance() {
		when(mapperMock.map(resultTransactionStatusEntity, TransactionStatus.class))
				.thenReturn(new TransactionStatus());
		Object resultTransactionStatusInstance = underTest.getStatusById(STATUS_ID);
		Assert.assertEquals(TransactionStatus.class, resultTransactionStatusInstance.getClass());
	}
}
