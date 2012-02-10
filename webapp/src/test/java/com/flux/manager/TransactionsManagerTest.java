package com.flux.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

public class TransactionsManagerTest {
	
	private static final long ACCOUNT_ID = 1;

	private TransactionsManager underTest;
	
	private Map model;
	
	@Mock
	private TransactionProvider mockTransactionProvider;
	
	@Before
	public void setUp(){
		underTest = new TransactionsManager();
		model = new HashMap<String,Object>();
		
		MockitoAnnotations.initMocks(this);
		underTest.setTransactionProvider(mockTransactionProvider);
	}
	
	@Test
	public void shouldAddTransactionsToModelIfAccountIdPresents(){
		List<Transaction> transactions = new ArrayList<Transaction>();
		model.put(TransactionsManager.ACCOUNT_ID_ATTRIBUTE_NAME, ACCOUNT_ID);
		Mockito.when(mockTransactionProvider.getTransactionByAccountToId(ACCOUNT_ID)).thenReturn(transactions);
		underTest.getTransactions(model);
		List<Transaction> resultTransactions = (List<Transaction>)model.get(TransactionsManager.TRANSACTIONS_ATTRIBUTE_NAME); 
		Assert.assertEquals(transactions, resultTransactions);
		
	}
	
	@Test
	public void shouldAddEmptyListToModelIfAccountIdIsAbsent(){
		underTest.getTransactions(model);
		List<Transaction> resultTransactions = (List<Transaction>)model.get(TransactionsManager.TRANSACTIONS_ATTRIBUTE_NAME); 
		Assert.assertEquals(Collections.EMPTY_LIST, resultTransactions);
	}
	
}
