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

import com.flux.domain.Account;
import com.flux.domain.User;
import com.flux.provider.fake.AccountDataProviderImpl;
import com.flux.provider.fake.AccountProviderImpl;

public class AccountManagerTest {

	private static final String INCORRECT_TYPED_ATTRIBUTE_NAME = "incorrectTypedAttribute";
	private static final int USER_ID = 1;
	private static final int EMPTY_ACCOUNT_ID = 0;
	
	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private HttpSession mockSession;
	@Mock
	private AccountProviderImpl mockAccountProvider;
	@Mock
	private AccountDataProviderImpl mockAccountDataProvider;
	@Mock
	private List<Account> mockAccounts;
	@Mock
	private User mockUser;

	private AccountManager underTest;

	@Before
	public void setUp() {
		underTest = new AccountManager();

		MockitoAnnotations.initMocks(this);
		underTest.setAccountProvider(mockAccountProvider);
		underTest.setAccountDataProvider(mockAccountDataProvider);
		Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
	}

	@Test
	public void shouldAddAccountsIfUserIdIsCorrect() {
		Mockito.when(mockSession.getAttribute(AccountManager.USER_ATTRIBUTE_NAME)).thenReturn(mockUser);
		Mockito.when(mockUser.getUserId()).thenReturn(USER_ID);
		Mockito.when(mockAccountProvider.getAccountsByUserId(USER_ID)).thenReturn(mockAccounts);
		Map<String, Object> resultModel = underTest.addAccountsByUserIdToModel(mockRequest);
		Assert.assertEquals(mockAccounts, resultModel.get(AccountManager.ACCOUNTS_ATTRIBUTE_NAME));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldAddEmptyListToModelIfUserIdIsAbsent() {
		Map<String, Object> resultModel = underTest.addAccountsByUserIdToModel(mockRequest);
		List<Account> resultAccounts = (List<Account>) resultModel.get(AccountManager.ACCOUNTS_ATTRIBUTE_NAME);
		Assert.assertEquals(Collections.EMPTY_LIST, resultAccounts);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldAddEmptyListToModelIfUserIdHasIncorrectType() {
		Mockito.when(mockSession.getAttribute(AccountManager.USER_ATTRIBUTE_NAME)).thenReturn(
				INCORRECT_TYPED_ATTRIBUTE_NAME);
		Map<String, Object> resultModel = underTest.addAccountsByUserIdToModel(mockRequest);
		List<Account> resultAccounts = (List<Account>) resultModel.get(AccountManager.ACCOUNTS_ATTRIBUTE_NAME);
		Assert.assertEquals(Collections.EMPTY_LIST, resultAccounts);
	}

	@Test
	public void shouldAddAccountToModelIfSelectedIdParameterIsCorrect() {
		Mockito.when(mockRequest.getParameter(AccountManager.SELECTED_ACCOUNT_ID_PARAMETER_NAME)).thenReturn("1");
		Account dummyAccount = new Account();
		Mockito.when(mockAccountDataProvider.getAccountById(1)).thenReturn(dummyAccount);
		Map<String, Object> resultModel = underTest.addAccountReviewByAccountIdToModel(mockRequest);
		Account selectedAccount = (Account) resultModel.get(AccountManager.SELECTED_ACCOUNT_ATTRIBUTE_NAME);
		Assert.assertEquals(dummyAccount, selectedAccount);
	}

	@Test
	public void shouldAddEmptyAccountIfSelectedIdParameterIsAbsent() {
		Map<String, Object> resultModel = underTest.addAccountReviewByAccountIdToModel(mockRequest);
		Account selectedAccount = (Account) resultModel.get(AccountManager.SELECTED_ACCOUNT_ATTRIBUTE_NAME);
		Assert.assertEquals(EMPTY_ACCOUNT_ID, selectedAccount.getAccountId());
	}

	@Test
	public void shouldAddEmptyAccountIfSelectedIdParameterCannotBeParsed() {
		Mockito.when(mockRequest.getParameter(AccountManager.SELECTED_ACCOUNT_ID_PARAMETER_NAME)).thenReturn(
				INCORRECT_TYPED_ATTRIBUTE_NAME);
		Map<String, Object> resultModel = underTest.addAccountReviewByAccountIdToModel(mockRequest);
		Account selectedAccount = (Account) resultModel.get(AccountManager.SELECTED_ACCOUNT_ATTRIBUTE_NAME);
		Assert.assertEquals(EMPTY_ACCOUNT_ID, selectedAccount.getAccountId());
	}
}
