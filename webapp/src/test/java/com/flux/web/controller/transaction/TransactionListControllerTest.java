package com.flux.web.controller.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.flux.domain.Transaction;
import com.flux.manager.TransactionsManager;

public class TransactionListControllerTest {
		
	private static final long ACCOUNT_ID = 1;
	private TransactionListController underTest;
	private Transaction transaction;
	
	private ModelAndView modelAndView;
	@Mock 
	private TransactionsManager mockManager;

	
	@Before
	public void setUp(){
		underTest = new TransactionListController();
		modelAndView = new ModelAndView();		
		
		MockitoAnnotations.initMocks(this);
		underTest.setTransactionsManager(mockManager);
	}
	
	@Test
	public void shouldRedirectToHomePage(){
		underTest.showTransactionsForAccount(modelAndView);
	
		Assert.assertEquals(TransactionListController.HOMEPAGE_PATH, modelAndView.getViewName());
	}
	
	@Test
	public void shouldAddTransactionsToModel(){
		initManagerMockWithTransactions();
		ModelAndView result = underTest.showTransactionsForAccount(modelAndView);
		Map resultModel = result.getModel();
		List<Transaction> transactions = (List<Transaction>)resultModel.get(TransactionListController.TRANSACTIONS_ATTRIBUTE_NAME);
		
		Assert.assertEquals(transaction, transactions.get(0));
	}

	private void initManagerMockWithTransactions() {
		List<Transaction> transactions = initTransactionsList();	
		Map model = modelAndView.getModel();
		
		Mockito.when(mockManager.getTransactions(model)).thenReturn(transactions);
		}
	
	private List<Transaction> initTransactionsList(){		
		List<Transaction> transactions = new ArrayList<Transaction>();
		transaction = new Transaction();
		transactions.add(transaction);
		return transactions;
}
}
