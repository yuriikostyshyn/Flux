package com.flux.web.controller.account;

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

import com.flux.manager.AccountManager;

public class AccountControllerTest {

	public static final String MAP_DUMMY_ELEMENT = "mapDummyElement";
	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private HttpServletResponse mockResponse;
	@Mock
	private AccountManager mockAccountManager;

	private AccountController underTest;
	private Map<String, Object> model;

	@Before
	public void setUp() {
		underTest = new AccountController();
		model = new HashMap<String, Object>();

		MockitoAnnotations.initMocks(this);

		underTest.setAccountManager(mockAccountManager);
	}

	@Test
	public void shouldReturnViewWithLinkToAccountsListPage() {
		ModelAndView resultModelAndView = underTest.showAccountsByUserId(mockRequest, mockResponse);
		String resultPagePath = resultModelAndView.getViewName();
		Assert.assertEquals(AccountController.ACCOUNTS_VIEW_PAGE_PATH, resultPagePath);
	}

	@Test
	public void shouldCallAddAccountsByUserIdMethodOfAccountsManagerInstance() {
		underTest.showAccountsByUserId(mockRequest, mockResponse);
		Mockito.verify(mockAccountManager, Mockito.times(1)).addAccountsByUserIdToModel(mockRequest);
	}
	
	@Test
	public void shouldCallAddAccountReviewByAccountIdMethodOfAccountsManagerInstance() {
		underTest.showAccountReviewByAccountId(mockRequest, mockResponse);
		Mockito.verify(mockAccountManager, Mockito.times(1)).addAccountReviewByAccountIdToModel(mockRequest);
	}
	@Test
	public void shouldAddModelReturnedByAccountsManagerInstanceToResult() {
		model.put(MAP_DUMMY_ELEMENT, MAP_DUMMY_ELEMENT);
		Mockito.when(mockAccountManager.addAccountsByUserIdToModel(mockRequest)).thenReturn(model);
		ModelAndView resultModelAndView = underTest.showAccountsByUserId(mockRequest, mockResponse);

		Map<String, Object> resultModel = resultModelAndView.getModel();
		Assert.assertTrue(resultModel.containsKey(MAP_DUMMY_ELEMENT));
		String resultMapDummyElement = (String) resultModelAndView.getModel().get(MAP_DUMMY_ELEMENT);
		Assert.assertEquals(MAP_DUMMY_ELEMENT, resultMapDummyElement);
	}
	
}
