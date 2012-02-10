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
		
	private TransactionListController underTest;
	
	private ModelAndView modelAndView;
	
	
	@Before
	public void setUp(){
		underTest = new TransactionListController();
		modelAndView = new ModelAndView();		
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldRedirectToHomePage(){
		underTest.showTransactionsForAccount(modelAndView);
	
		Assert.assertEquals(TransactionListController.HOMEPAGE_PATH, modelAndView.getViewName());
	}	
}