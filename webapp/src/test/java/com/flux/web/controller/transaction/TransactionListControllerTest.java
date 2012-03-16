package com.flux.web.controller.transaction;

import java.util.HashMap;
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

	private static final String DUMMY_ELEMENT = "element ha-ha-ha";
	private static final String DUMMY_ELEMENT_NAME = "element name";
	@Mock
	private TransactionsManager mockTransactionsManager;
	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private HttpServletResponse mockResponse;

	private TransactionListController underTest;


	@Before
	public void setUp() {
		underTest = new TransactionListController();
		MockitoAnnotations.initMocks(this);
		underTest.setTransactionsManager(mockTransactionsManager);
	}

	@Test
	public void shouldRedirectToTransactionsListPage() {
		ModelAndView resultModelAndView = underTest.showTransactionsByAccountId(mockRequest, mockResponse);
		Assert.assertEquals(TransactionListController.HOMEPAGE_PATH, resultModelAndView.getViewName());
	}

	@Test
	public void shouldAddTransactionsAttributeToResultModelAndView() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(DUMMY_ELEMENT_NAME, DUMMY_ELEMENT);
		
		Mockito.when(mockTransactionsManager.addTransactionsByAccountIdToModel(mockRequest)).thenReturn(model);
		
		ModelAndView resultModelAndView = underTest.showTransactionsByAccountId(mockRequest, mockResponse);
		Map<String,Object> resultModel = resultModelAndView.getModel();
		
		Assert.assertTrue(resultModel.containsKey(DUMMY_ELEMENT_NAME));
		
	}
}
