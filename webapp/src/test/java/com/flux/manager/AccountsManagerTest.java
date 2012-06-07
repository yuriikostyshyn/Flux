package com.flux.manager;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import com.flux.domain.Account;
import com.flux.domain.Currency;
import com.flux.domain.User;
import com.flux.provider.CurrencyProvider;
import com.flux.provider.fake.AccountDataProviderImpl;
import com.flux.provider.fake.AccountProviderImpl;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static junit.framework.Assert.assertTrue;

public class AccountsManagerTest {

	private static final String INCORRECT_TYPED_ATTRIBUTE_NAME = "incorrectTypedAttribute";
	private static final int USER_ID = 1;
	private static final long SELECTED_ACCOUNT_ID = 1L;

	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private HttpSession mockSession;
	@Mock
	private List<Account> mockAccounts;
	@Mock
	private User mockUser;
	@Mock
	private Map<String, Currency> mockCurrencies;
	@Mock
	private Account mockAccount;
	@Mock
	private AccountProviderImpl mockAccountProvider;
	@Mock
	private AccountDataProviderImpl mockAccountDataProvider;
	@Mock
	private CurrencyProvider mockCurrencyProvider;

	private AccountsManager underTest;

	@Before
	public void setUp() {
		underTest = new AccountsManager();

		MockitoAnnotations.initMocks(this);
		underTest.setAccountProvider(mockAccountProvider);
		underTest.setAccountDataProvider(mockAccountDataProvider);
		underTest.setCurrencyProvider(mockCurrencyProvider);

		Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
	}

	@Test
	public void shouldAddAccountsIfUserIdIsCorrect() {
		Mockito.when(
				mockSession.getAttribute(AccountsManager.USER_ATTRIBUTE_NAME))
				.thenReturn(mockUser);
		Mockito.when(mockUser.getUserId()).thenReturn(USER_ID);
		Mockito.when(mockAccountProvider.getAccountsByUserId(USER_ID))
				.thenReturn(mockAccounts);

		underTest.addAccountsToSession(mockSession);

		Mockito.verify(mockSession).setAttribute(
				AccountsManager.ACCOUNTS_ATTRIBUTE_NAME, mockAccounts);
	}

	@Test
	public void shouldAddEmptyListToModelIfUserIdIsAbsent() {
		underTest.addAccountsToSession(mockSession);

		Mockito.verify(mockSession)
				.setAttribute(AccountsManager.ACCOUNTS_ATTRIBUTE_NAME,
						Collections.EMPTY_LIST);
	}

	@Test
	public void shouldAddEmptyListToModelIfUserIdHasIncorrectType() {
		Mockito.when(
				mockSession.getAttribute(AccountsManager.USER_ATTRIBUTE_NAME))
				.thenReturn(INCORRECT_TYPED_ATTRIBUTE_NAME);

		underTest.addAccountsToSession(mockSession);

		Mockito.verify(mockSession)
				.setAttribute(AccountsManager.ACCOUNTS_ATTRIBUTE_NAME,
						Collections.EMPTY_LIST);
	}

	@Test
	public void shouldCallAccountDataProviderToGetReviewAndPutItIntoResult() {
		when(mockAccountDataProvider.getAccountById(SELECTED_ACCOUNT_ID))
				.thenReturn(mockAccount);

		ModelMap actualModel = underTest.addAccountReviewByAccountIdToModel(SELECTED_ACCOUNT_ID);
		boolean isAccountPresentInModel = actualModel.get(AccountsManager.SELECTED_ACCOUNT_ATTRIBUTE_NAME) == mockAccount;
		
		verify(mockAccountDataProvider).getAccountById(SELECTED_ACCOUNT_ID);
		assertTrue(isAccountPresentInModel);

	}

	@Test
	public void shouldCallAccountProviderToSaveNewAccount() {
		underTest.saveNewAccount(mockAccount);
		Mockito.verify(mockAccountProvider).saveNewAccount(mockAccount);
	}

	@Test
	public void shouldCallCurrencyProviderToGetAllCurrenciesMap() {
		Mockito.when(mockCurrencyProvider.getAllCurrenciesMap()).thenReturn(
				mockCurrencies);

		Map<String, Currency> currencies = underTest.getCurrencies();

		Assert.assertEquals(mockCurrencies, currencies);
	}

}
