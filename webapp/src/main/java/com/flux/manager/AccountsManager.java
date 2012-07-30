package com.flux.manager;

import java.security.ProviderException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.flux.domain.Account;
import com.flux.domain.Currency;
import com.flux.domain.User;
import com.flux.provider.AccountDataProvider;
import com.flux.provider.AccountProvider;
import com.flux.provider.CurrencyProvider;
import com.flux.web.util.exception.InvalidUserException;

@Component
public class AccountsManager {

	public static final String SELECTED_ACCOUNT_ATTRIBUTE_NAME = "selectedAccount";
	public static final String ACCOUNTS_ATTRIBUTE_NAME = "accounts";
	public static final String USER_ATTRIBUTE_NAME = "user";
	private static final Logger LOGGER = Logger.getLogger(AccountsManager.class);

	private AccountProvider accountProvider;
	private AccountDataProvider accountDataProvider;
	
	public void addAccountsToSession(HttpSession session) {
		List<Account> accounts = getAccounts(session);
		session.setAttribute(ACCOUNTS_ATTRIBUTE_NAME, accounts);
	}

	public ModelMap addAccountReviewByAccountIdToModel(long selectedAccountId) {
		ModelMap result = addAccountByIdToModel(selectedAccountId);
		
		return result;
	}

	public void saveNewAccount(Account newAccount) {
		try {
			accountProvider.saveNewAccount(newAccount);
		} catch (ProviderException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

		private List<Account> getAccounts(HttpSession session) {
		List<Account> accounts = Collections.emptyList();
		try {
			int userId = getUserId(session);
			accounts = getAccountsByUserId(userId);
		} catch (InvalidUserException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return accounts;
	}

	private int getUserId(HttpSession session) {
		int resultUserId = 0;
		try {
			User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
			if (user != null) {
				resultUserId = user.getUserId();
			} else {
				throw new InvalidUserException("User is null");
			}
		} catch (ClassCastException ex) {
			throw new InvalidUserException("Incorrect type of user attribute in session", ex);
		}
		return resultUserId;
	}

	private List<Account> getAccountsByUserId(int userId) {
		List<Account> resultAccounts = accountProvider.getAccountsByUserId(userId);
		return resultAccounts;
	}

	private ModelMap addAccountByIdToModel(long selectedAccountId) {
		ModelMap result = new ModelMap();
		
		Account selectedAccount = getAccountById(selectedAccountId);
		result.put(SELECTED_ACCOUNT_ATTRIBUTE_NAME, selectedAccount);
		
		return result;
	}

	private Account getAccountById(long selectedAccountId) {		
		Account	result = accountDataProvider.getAccountById(selectedAccountId); 
			
		return result;
	}

	@Autowired
	public void setAccountProvider(AccountProvider accountProvider) {
		this.accountProvider = accountProvider;
	}

	@Autowired
	public void setAccountDataProvider(AccountDataProvider accountDataProvider) {
		this.accountDataProvider = accountDataProvider;
	}

	
	
}
