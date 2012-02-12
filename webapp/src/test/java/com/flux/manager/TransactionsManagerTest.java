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

	private static final String INCORRECT_ACCOUNT_ID = "incorrect account id";

	@Mock
	private TransactionProvider mockTransactionProvider;
	
	private TransactionsManager underTest;
	
	private Map<String,Object> model;
	

	@Before
	public void setUp(){
		underTest = new TransactionsManager();
		model = new HashMap<String,Object>();
		
		MockitoAnnotations.initMocks(this);
		underTest.setTransactionProvider(mockTransactionProvider);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldAddTransactionsToModelIfAccountIdPresents(){
		List<Transaction> transactions = new ArrayList<Transaction>();
		model.put(TransactionsManager.ACCOUNT_ID_ATTRIBUTE_NAME, ACCOUNT_ID);
		Mockito.when(mockTransactionProvider.getTransactionByAccountId(ACCOUNT_ID)).thenReturn(transactions);
		underTest.getTransactions(model);
		List<Transaction> resultTransactions = (List<Transaction>)model.get(TransactionsManager.TRANSACTIONS_ATTRIBUTE_NAME); 
		Assert.assertEquals(transactions, resultTransactions);
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldAddEmptyListToModelIfAccountIdIsAbsent(){
		underTest.getTransactions(model);
		List<Transaction> resultTransactions = (List<Transaction>)model.get(TransactionsManager.TRANSACTIONS_ATTRIBUTE_NAME); 
		Assert.assertEquals(Collections.EMPTY_LIST, resultTransactions);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void shouldAddEmptyListToModelIfAccountIdHasIncorrectFormat(){
		underTest.getTransactions(model);
		model.put(TransactionsManager.ACCOUNT_ID_ATTRIBUTE_NAME, INCORRECT_ACCOUNT_ID);
		List<Transaction> resultTransactions = (List<Transaction>)model.get(TransactionsManager.TRANSACTIONS_ATTRIBUTE_NAME); 
		Assert.assertEquals(Collections.EMPTY_LIST, resultTransactions);
	}
	
}
