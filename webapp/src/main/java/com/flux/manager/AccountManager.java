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

@Component
public class AccountManager {

	public static final String SELECTED_ACCOUNT_ATTRIBUTE_NAME = "selectedAccount";
	public static final String SELECTED_ACCOUNT_ID_PARAMETER_NAME = "selectedAccountId";
	public static final String ACCOUNTS_ATTRIBUTE_NAME = "accounts";
	public static final String USER_ATTRIBUTE_NAME = "user";
	private static final Logger LOGGER = Logger.getLogger(UserManager.class);

	private AccountProvider accountProvider;
	private AccountDataProvider accountDataProvider;

	public Map<String, Object> addAccountsByUserIdToModel(HttpServletRequest request) {
		Map<String, Object> resultModel = new HashMap<String, Object>();

		addAccountsByUserIdToModel(resultModel, request);

		return resultModel;
	}

	public Map<String, Object> addAccountReviewByAccountIdToModel(HttpServletRequest request) {
		Map<String, Object> resultModel = new HashMap<String, Object>();

		addAccountsByUserIdToModel(resultModel, request);
		addAccountByIdToModel(request, resultModel);

		return resultModel;
	}

	private void addAccountsByUserIdToModel(Map<String, Object> resultModel, HttpServletRequest request) {
		List<Account> accounts = getAccountsByUserId(request);
		resultModel.put(ACCOUNTS_ATTRIBUTE_NAME, accounts);
	}

	private void addAccountByIdToModel(HttpServletRequest request, Map<String, Object> resultModel) {
		Account selectedAccount = getAccountById(request);
		resultModel.put(SELECTED_ACCOUNT_ATTRIBUTE_NAME, selectedAccount);
	}

	private List<Account> getAccountsByUserId(HttpServletRequest request) {
		List<Account> resultAccounts = Collections.EMPTY_LIST;
		try {
			int userId = getUserId(request);
			resultAccounts = accountProvider.getAccountsByUserId(userId);
		} catch (ClassCastException ex) {
			LOGGER.error("Incorrect type of user attribute in session", ex);
		}
		return resultAccounts;
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

	private int getUserId(HttpServletRequest request) throws ClassCastException{
		HttpSession session = request.getSession();
		int resultUserId = 0;
		User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
		if (user != null) {
			resultUserId = user.getUserId();
		}
		return resultUserId;
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
