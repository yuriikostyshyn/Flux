package com.flux.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactionsManagerTest {
	
	private static final long SELECTED_ACCOUNT_ID = 1L;
	
	@Mock
	private TransactionProvider mockTransactionProvider;
	@Mock
	private List<Transaction> mockTransactions;
	@Mock
	private Transaction mockNewTransaction;

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
		when(mockTransactionProvider.getAllTransactions()).thenReturn(transactions);
		
		underTest.addAllTransactionsToModel(model);
		List<Transaction> result = (List<Transaction>)model.get(TransactionsManager.TRANSACTIONS_ATTRIBUTE_NAME); 
		
		assertEquals(transactions, result);
		
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldAddTransactionsToModelIfAccountIdIsCorrect(){
		when(mockTransactionProvider.getTransactionsByAccountId(SELECTED_ACCOUNT_ID)).thenReturn(mockTransactions);
		
		ModelMap actualMap = underTest.addTransactionsByAccountIdToModel(SELECTED_ACCOUNT_ID);
		List<Transaction> actualTransactions = (List<Transaction>) actualMap.get(TransactionsManager.TRANSACTIONS_ATTRIBUTE_NAME);
		
		assertEquals(mockTransactions, actualTransactions);
		
	}
	
	@Test
	public void shouldCallTransactionProviderToSaveNewTransaction(){
		underTest.saveNewTransaction(mockNewTransaction);
		
		verify(mockTransactionProvider).saveNewTransaction(mockNewTransaction);
	}
}
