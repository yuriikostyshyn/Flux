package com.flux.manager;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flux.provider.TransactionProvider;

public class TransactionsManagerTest {
	private TransactionsManager underTest;
	
	private Map model;
	
	@Mock
	private TransactionProvider mockTransactionProvider;
	
	@Before
	public void setUp(){
		underTest = new TransactionsManager();
		
		MockitoAnnotations.initMocks(this);
		underTest.setTransactionProvider(mockTransactionProvider);
	}
	
	@Test
	public void shouldReturnTransactionsIfAccountIdPresents(){
		model.put("accountId", )
		
	}
	
	@Test
	public void shouldReturnEmptyListIfAccountIdIsAbsent(){
		
	}
	
}
