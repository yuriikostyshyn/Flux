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
	public void shouldAddAllTransactionsToModel(){
		List<Transaction> transactions = new ArrayList<Transaction>();
		Mockito.when(mockTransactionProvider.getAllTransactions()).thenReturn(transactions);
		underTest.addAllTransactionsToModel(model);
		List<Transaction> resultTransactions = (List<Transaction>)model.get(TransactionsManager.TRANSACTIONS_ATTRIBUTE_NAME); 
		Assert.assertEquals(transactions, resultTransactions);
		
	}	
}
