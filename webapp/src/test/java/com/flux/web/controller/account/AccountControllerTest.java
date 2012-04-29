package com.flux.web.controller.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import junit.framework.Assert;

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
import com.flux.manager.AccountsManager;
import com.flux.web.util.propertyeditor.CurrencyPropertyEditor;
import com.flux.web.util.validator.AccountValidator;

public class AccountControllerTest {

	private static final long SELECTED_ACCOUNT_ID = 1L;
	private static final String MAP_DUMMY_ELEMENT = "mapDummyElement";
	
	@Mock
	private AccountsManager mockAccountManager;
	@Mock
	private ModelMap mockModel;
	@Mock
	private WebDataBinder mockBinder;
	@Mock
	private CurrencyPropertyEditor mockCurrencyEditor;
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
		underTest.setCurrencyEditor(mockCurrencyEditor);
		underTest.setAccountValidator(mockAccountValidator);
	}

	@Test
	public void shouldReturnViewWithLinkToAccountsListPage() {
		String expectedPath = AccountController.ACCOUNTS_VIEW_PAGE_PATH;
		
		String actualPath =  underTest.showAccountsByUserId(mockSession, mockModel);
			
		Assert.assertEquals(expectedPath, actualPath);
	}

	@Test
	public void shouldCallAddAccountsByUserIdMethodOfAccountsManagerInstance() {
		underTest.showAccountsByUserId(mockSession, mockModel);
		Mockito.verify(mockAccountManager, Mockito.times(1)).addAccountsToSession(mockSession);
	}

	@Test
	public void shouldCallAddAccountReviewByAccountIdMethodOfAccountsManagerInstance() {
		underTest.showAccountReviewByAccountId(SELECTED_ACCOUNT_ID);
		Mockito.verify(mockAccountManager, Mockito.times(1)).addAccountReviewByAccountIdToModel(SELECTED_ACCOUNT_ID);
	}

	@Test
	public void shouldAddModelReturnedByAccountsManagerInstanceToResult() {
		modelMap.put(MAP_DUMMY_ELEMENT, MAP_DUMMY_ELEMENT);
		Mockito.when(mockAccountManager.addAccountReviewByAccountIdToModel(SELECTED_ACCOUNT_ID)).thenReturn(modelMap);
		ModelAndView actualModelAndView = underTest.showAccountReviewByAccountId(SELECTED_ACCOUNT_ID);

		Map<String, Object> actualModel = actualModelAndView.getModel();
		Assert.assertTrue(actualModel.containsKey(MAP_DUMMY_ELEMENT));
		
		String actualMapDummyElement = (String) actualModel.get(MAP_DUMMY_ELEMENT);
		Assert.assertEquals(MAP_DUMMY_ELEMENT, actualMapDummyElement);
	}

	@Test
	public void shouldCallAccountManagerToGetCurrenciesToPopulate() {
		underTest.populateCurrencies();
		Mockito.verify(mockAccountManager).getCurrencies();
	}

	@Test
	public void shouldInitBinderWithCurrencyPropertyEditor() {
		Mockito.when(mockAccountManager.getCurrencies()).thenReturn(new HashMap<String, Currency>());
		underTest.initBinder(mockBinder);
		Map<String, Currency> currencies = underTest.populateCurrencies();
		Mockito.verify(mockCurrencyEditor).setCurrencies(currencies);
		Mockito.verify(mockBinder).registerCustomEditor(Currency.class, mockCurrencyEditor);
	}

	@Test
	public void shouldAddNewAccountAttributeToModelWhileInitializeForm() {
		underTest.initNewAccountForm(mockModel);
		Mockito.verify(mockModel).addAttribute(Mockito.eq(AccountController.NEW_ACCOUNT_ATTRIBUTE_NAME),
				Mockito.any(Account.class));
	}

	@Test
	public void shouldReturnRedirectToAccountsServletIfNewAccountInstanceIsCorrect() {
		Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);		
		String expectedPath = AccountController.REDIRECT_PATH_PART + AccountController.SHOW_ACCOUNTS_SERVLET_PATH;
		
		String actualPath =  underTest.addNewAccount(mockNewAccount, mockBindingResult, mockSessionStatus);
		
		Assert.assertEquals(expectedPath, actualPath);
	}
	
	@Test
	public void shouldReturnNewAccountPagePathIfNewAccountInstanceIsIncorrect() {
		Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);
		String expectedPath = AccountController.NEW_ACCOUNT_PAGE_PATH;
		
		String actualPath = underTest.addNewAccount(mockNewAccount, mockBindingResult, mockSessionStatus);
		
		Assert.assertEquals(expectedPath, actualPath);
	}
	
	@Test
	public void shouldCallAccountManagerToSaveNewAccount() {
		underTest.addNewAccount(mockNewAccount, mockBindingResult, mockSessionStatus);
		Mockito.verify(mockAccountManager).saveNewAccount(mockNewAccount);

	}

}
