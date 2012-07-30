package com.flux.web.controller.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.flux.domain.Account;
import com.flux.domain.Currency;
import com.flux.domain.User;
import com.flux.manager.AccountsManager;
import com.flux.manager.CurrencyManager;
import com.flux.manager.UsersManager;
import com.flux.web.util.propertyeditor.CurrencyPropertyEditor;
import com.flux.web.util.propertyeditor.UserPropertyEditor;
import com.flux.web.util.validator.AccountValidator;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static junit.framework.Assert.assertEquals;

public class AccountControllerTest {

	private static final long SELECTED_ACCOUNT_ID = 1L;
	private static final String MAP_DUMMY_ELEMENT = "mapDummyElement";
	
	@Mock
	private AccountsManager mockAccountManager;
	@Mock
	private CurrencyManager mockCurrencyManager;
	@Mock 
	private UsersManager mockUsersManager;
	@Mock
	private ModelMap mockModel;
	@Mock
	private WebDataBinder mockBinder;
	@Mock
	private CurrencyPropertyEditor mockCurrencyEditor;
	@Mock
	private UserPropertyEditor mockUserEditor;
	@Mock
	private Account mockNewAccount;
	@Mock
	private BindingResult mockBindingResult;
	@Mock
	private SessionStatus mockSessionStatus;
	@Mock
	private AccountValidator mockAccountValidator;
	@Mock
	private HttpSession mockSession;
	
	private AccountController underTest;
	private ModelMap modelMap;
	
	@Before
	public void setUp() {
		underTest = new AccountController();
		modelMap = new ModelMap();

		MockitoAnnotations.initMocks(this);

		underTest.setAccountManager(mockAccountManager);
		underTest.setCurrencyManager(mockCurrencyManager);
		underTest.setUsersManager(mockUsersManager);
		underTest.setCurrencyEditor(mockCurrencyEditor);
		underTest.setUserEditor(mockUserEditor);
		underTest.setAccountValidator(mockAccountValidator);
	}

	@Test
	public void shouldReturnViewWithLinkToAccountsListPage() {
		String expectedPath = AccountController.ACCOUNTS_VIEW_PAGE_PATH;
		
		String actualPath =  underTest.showAccountsByUserId(mockSession, mockModel);
			
		assertEquals(expectedPath, actualPath);
	}

	@Test
	public void shouldCallAddAccountsByUserIdMethodOfAccountsManagerInstance() {
		underTest.showAccountsByUserId(mockSession, mockModel);
		verify(mockAccountManager, Mockito.times(1)).addAccountsToSession(mockSession);
	}

	@Test
	public void shouldCallAddAccountReviewByAccountIdMethodOfAccountsManagerInstance() {
		underTest.showAccountReviewByAccountId(SELECTED_ACCOUNT_ID);
		verify(mockAccountManager, Mockito.times(1)).addAccountReviewByAccountIdToModel(SELECTED_ACCOUNT_ID);
	}

	@Test
	public void shouldAddModelReturnedByAccountsManagerInstanceToResult() {
		modelMap.put(MAP_DUMMY_ELEMENT, MAP_DUMMY_ELEMENT);
		when(mockAccountManager.addAccountReviewByAccountIdToModel(SELECTED_ACCOUNT_ID)).thenReturn(modelMap);
		ModelAndView actualModelAndView = underTest.showAccountReviewByAccountId(SELECTED_ACCOUNT_ID);

		Map<String, Object> actualModel = actualModelAndView.getModel();
		assertTrue(actualModel.containsKey(MAP_DUMMY_ELEMENT));
		
		String actualMapDummyElement = (String) actualModel.get(MAP_DUMMY_ELEMENT);
		assertEquals(MAP_DUMMY_ELEMENT, actualMapDummyElement);
	}

	@Test
	public void shouldCallCurrenciesManagerToGetCurrenciesToPopulate() {		
		Map<String, Currency> expectedResult = new HashMap<String, Currency>();
		when(mockCurrencyManager.getCurrencies()).thenReturn(expectedResult);
		
		Map<String, Currency> actualResult = underTest.populateCurrencies();
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void shouldCallUsersManagerToGetUsersToPopulate() {
		Map<String, User> expectedResult = new HashMap<String, User>();
		when(mockUsersManager.getUsers()).thenReturn(expectedResult);
		
		Map<String, User> actualResult = underTest.populateUsers();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldInitBinderWithPropertyEditors() {
		when(mockCurrencyManager.getCurrencies()).thenReturn(new HashMap<String, Currency>());
		when(mockUsersManager.getUsers()).thenReturn(new HashMap<String, User>());
		underTest.initBinder(mockBinder);
		Map<String, Currency> currencies = underTest.populateCurrencies();
		Map<String, User> users = underTest.populateUsers();
		verify(mockCurrencyEditor).setCurrencies(currencies);
		verify(mockUserEditor).setUsers(users);
		verify(mockBinder).registerCustomEditor(Currency.class, mockCurrencyEditor);
		verify(mockBinder).registerCustomEditor(User.class, mockUserEditor);
	}

	@Test
	public void shouldAddNewAccountAttributeToModelWhileInitializeForm() {
		underTest.initNewAccountForm(mockModel, mockSession);
		verify(mockModel).addAttribute(Mockito.eq(AccountController.NEW_ACCOUNT_ATTRIBUTE_NAME),
				Mockito.any(Account.class));
	}

	@Test
	public void shouldReturnRedirectToAccountsServletIfNewAccountInstanceIsCorrect() {
		when(mockBindingResult.hasErrors()).thenReturn(false);		
		String expectedPath = AccountController.REDIRECT_PATH_PART + AccountController.SHOW_ACCOUNTS_SERVLET_PATH;
		
		String actualPath =  underTest.addNewAccount(mockNewAccount, mockBindingResult, mockSessionStatus, mockSession);
		
		assertEquals(expectedPath, actualPath);
	}
	
	@Test
	public void shouldReturnNewAccountPagePathIfNewAccountInstanceIsIncorrect() {
		Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);
		String expectedPath = AccountController.NEW_ACCOUNT_PAGE_PATH;
		
		String actualPath = underTest.addNewAccount(mockNewAccount, mockBindingResult, mockSessionStatus, mockSession);
		
		assertEquals(expectedPath, actualPath);
	}

}
