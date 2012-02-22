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
	private TransactionsManager mockTransactionsManager;
	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private HttpServletResponse mockResponse;
	
	private TransactionListController underTest;	
	
	
	@Before
	public void setUp(){
		underTest = new TransactionListController();
		MockitoAnnotations.initMocks(this);
		underTest.setTransactionsManager(mockTransactionsManager);
	}
	
	@Test
	public void shouldRedirectToHomePage(){
		ModelAndView resultModelAndView = underTest.showAllTransactions(mockRequest, mockResponse);	
		Assert.assertEquals(TransactionListController.HOMEPAGE_PATH, resultModelAndView.getViewName());
	}	
	@Test
	public void shouldCallManager(){
		ModelAndView resultModelAndView = underTest.showAllTransactions(mockRequest, mockResponse);
		Map<String, Object> model = resultModelAndView.getModel();
		Mockito.verify(mockTransactionsManager).addAllTransactionsToModel(model);
	}	
}
