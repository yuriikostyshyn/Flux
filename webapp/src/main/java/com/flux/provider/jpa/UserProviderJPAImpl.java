package com.flux.provider.jpa;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.dbs.dao.AccountDAO;
import com.flux.dbs.dao.CurrencyDAO;
import com.flux.dbs.dao.UserDAO;
import com.flux.domain.Account;
import com.flux.domain.Currency;
import com.flux.domain.User;
import com.flux.provider.UserProvider;

@Component
public class UserProviderJPAImpl implements UserProvider {
	private static final Logger LOGGER = Logger
			.getLogger(UserProviderJPAImpl.class);

	private UserDAO userDAO;

	private CurrencyDAO currencyDAO;
	
	private AccountDAO accountDAO;

	public User getUserByLoginAndPassword(String login, String password) {

		User result = userDAO.getUserByLoginAndPassword(login, password);
		Currency currency = currencyDAO.getCurrencyById(1);

		LOGGER.info("Currency instance we got:");
		LOGGER.info("Id:" + currency.getCurrencyId());
		LOGGER.info("Name:" + currency.getName());
		LOGGER.info("Long name:" + currency.getLongName());
		List<Account> accounts = accountDAO.getAllAccountForGivenUser(result);
		return result;
	}

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setCurrencyDAO(CurrencyDAO currencyDAO) {
		this.currencyDAO = currencyDAO;
	}

	@Autowired
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

}
