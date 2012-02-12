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
import com.flux.persistence.entities.CurrencyEntity;

public class CurrencyDAOImplTest {

	private static final int CURRENCY_ID = 1;

	@Mock
	private EntityManager entityManagerMock;
	@Mock
	private Query getCurrencyQueryMock;
	@Mock
	private Mapper mapperMock;

	private CurrencyDAOImpl underTest;
	private List<CurrencyEntity> resultCurrencyEntities;
	private CurrencyEntity resultCurrencyEntity;

	@Before
	public void setUp() {
		underTest = new CurrencyDAOImpl();
		resultCurrencyEntities = new ArrayList<CurrencyEntity>();
		resultCurrencyEntity = new CurrencyEntity();
		resultCurrencyEntities.add(resultCurrencyEntity);

		MockitoAnnotations.initMocks(this);
		underTest.setEntityManager(entityManagerMock);
		underTest.setMapper(mapperMock);

		when(entityManagerMock.createNamedQuery(CurrencyDAOImpl.GET_CURRENCY_BY_ID_QUERY_NAME)).thenReturn(
				getCurrencyQueryMock);
		when(getCurrencyQueryMock.getResultList()).thenReturn(resultCurrencyEntities);
	}

	@Test
	public void shouldCallMapperForCurrency() {
		underTest.getCurrencyById(CURRENCY_ID);
		verify(mapperMock, times(1)).map(resultCurrencyEntity, Currency.class);
	}

	@Test
	public void shouldReturnCurrencyInstance() {
		when(mapperMock.map(resultCurrencyEntity, Currency.class)).thenReturn(new Currency());
		Object resultCurrencyInstance = underTest.getCurrencyById(CURRENCY_ID);
		Assert.assertEquals(Currency.class, resultCurrencyInstance.getClass());

	}
}
