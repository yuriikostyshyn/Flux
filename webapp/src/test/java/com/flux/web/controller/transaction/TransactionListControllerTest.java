package com.flux.web.controller.transaction;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.flux.manager.TransactionsManager;

public class TransactionListControllerTest {
		
	@Mock
	private TransactionsManager transactionsManager;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	
	private TransactionListController underTest;	
	
	
	@Before
	public void setUp(){
		underTest = new TransactionListController();
		MockitoAnnotations.initMocks(this);
		underTest.setTransactionsManager(transactionsManager);
	}
	
	@Test
	public void shouldRedirectToHomePage(){
		ModelAndView resultModelAndView = underTest.showAllTransactions(request, response);	
		Assert.assertEquals(TransactionListController.HOMEPAGE_PATH, resultModelAndView.getViewName());
	}	
	@Test
	public void shouldCallManager(){
		ModelAndView resultModelAndView = underTest.showAllTransactions(request, response);
		Map<String, Object> model = resultModelAndView.getModel();
		Mockito.verify(transactionsManager).getAllTransactions(model);
	}	
}
