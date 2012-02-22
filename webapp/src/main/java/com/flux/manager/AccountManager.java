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
import com.flux.provider.AccountProvider;

@Component
public class AccountManager {

	public static final String ACCOUNTS_ATTRIBUTE_NAME = "accounts";
	public static final String USER_ATTRIBUTE_NAME = "user";
	private static final Logger LOGGER = Logger.getLogger(UserManager.class);
	
	private AccountProvider accountProvider;

	public Map<String, Object> addAccountsByUserIdToModel(HttpServletRequest request) {
		Map<String, Object> resultModel = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		try {
			User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
			
			if (user != null) {
				int userId = user.getUserId();
				List<Account> resultAccounts = accountProvider.getAccountsByUserId(userId);
				resultModel.put(ACCOUNTS_ATTRIBUTE_NAME, resultAccounts);
			} else {
				resultModel.put(ACCOUNTS_ATTRIBUTE_NAME, Collections.EMPTY_LIST);
			}
			
		} catch (ClassCastException ex) {
			LOGGER.error("Incorrect type of user attribute in session",ex);
			resultModel.put(ACCOUNTS_ATTRIBUTE_NAME, Collections.EMPTY_LIST);
		}
		
		return resultModel;
	}

	@Autowired
	public void setAccountProvider(AccountProvider accountProvider) {
		this.accountProvider = accountProvider;

	}

}
