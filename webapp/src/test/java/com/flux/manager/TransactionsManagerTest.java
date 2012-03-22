package com.flux.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;
import com.flux.web.util.helper.RequestHelper;

public class TransactionsManagerTest {
	
	private static final long ACCOUNT_ID = 1L;
	
	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private TransactionProvider mockTransactionProvider;
	@Mock
	private RequestHelper mockRequestHelper;
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
		underTest.setRequestHelper(mockRequestHelper);
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldAddTransactionsToModelIfAccountIdIsCorrect(){
		Mockito.when(mockRequestHelper.getSelectedAccountId(mockRequest)).thenReturn(ACCOUNT_ID);
		Mockito.when(mockTransactionProvider.getTransactionsByAccountId(ACCOUNT_ID)).thenReturn(mockTransactions);
		Map<String, Object> resultMap = underTest.addTransactionsByAccountIdToModel(mockRequest);
		List<Transaction> resultTransactions = (List<Transaction>) resultMap.get(TransactionsManager.TRANSACTIONS_ATTRIBUTE_NAME);
		Assert.assertEquals(mockTransactions, resultTransactions);
		
	}
	
	@Test
	public void shouldCallTransactionProviderToSaveNewTransaction(){
		underTest.saveNewTransaction(mockNewTransaction);
		Mockito.verify(mockTransactionProvider).saveNewTransaction(mockNewTransaction);
	}
}
