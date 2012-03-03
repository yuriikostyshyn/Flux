package com.flux.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.domain.Account;
import com.flux.domain.User;
import com.flux.provider.AccountDataProvider;
import com.flux.provider.AccountProvider;
import com.web.utils.exception.InvalidUserException;

@Component
public class AccountManager {

	public static final String SELECTED_ACCOUNT_ATTRIBUTE_NAME = "selectedAccount";
	public static final String SELECTED_ACCOUNT_ID_PARAMETER_NAME = "selectedAccountId";
	public static final String ACCOUNTS_ATTRIBUTE_NAME = "accounts";
	public static final String USER_ATTRIBUTE_NAME = "user";
	private static final Logger LOGGER = Logger.getLogger(UserManager.class);

	private AccountProvider accountProvider;
	private AccountDataProvider accountDataProvider;

	public void addAccountsToResult(HttpServletRequest request) {
		HttpSession session = request.getSession();
		addAccountsToSession(session);
	}

	public Map<String, Object> addAccountReviewByAccountIdToModel(HttpServletRequest request) {
		Map<String, Object> resultModel = new HashMap<String, Object>();

		addAccountByIdToModel(request, resultModel);

		return resultModel;
	}

	private void addAccountsToSession(HttpSession session) {
		List<Account> accounts = getAccounts(session);
		session.setAttribute(ACCOUNTS_ATTRIBUTE_NAME, accounts);
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

	private void addAccountByIdToModel(HttpServletRequest request, Map<String, Object> resultModel) {
		Account selectedAccount = getAccountById(request);
		resultModel.put(SELECTED_ACCOUNT_ATTRIBUTE_NAME, selectedAccount);
	}

	private Account getAccountById(HttpServletRequest request) {
		Account resultSelectedAccount = new Account();
		try {
			long selectedAccountId = getSelectedAccountId(request);

			resultSelectedAccount = accountDataProvider.getAccountById(selectedAccountId);
		} catch (NumberFormatException ex) {
			LOGGER.error("Incorrect type of selected account id parameter", ex);
		}
		return resultSelectedAccount;
	}

	private long getSelectedAccountId(HttpServletRequest request) throws NumberFormatException {
		String selectedIdParameter = request.getParameter(SELECTED_ACCOUNT_ID_PARAMETER_NAME);
		long resultSelectedAccountId = Long.valueOf(selectedIdParameter);
		return resultSelectedAccountId;
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
