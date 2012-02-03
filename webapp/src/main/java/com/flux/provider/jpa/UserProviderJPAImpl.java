package com.flux.provider.jpa;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.dbs.dao.AccountDAO;
import com.flux.dbs.dao.CurrencyDAO;
import com.flux.dbs.dao.TransactionDAO;
import com.flux.dbs.dao.TransactionStatusDAO;
import com.flux.dbs.dao.UserDAO;
import com.flux.domain.Account;
import com.flux.domain.Currency;
import com.flux.domain.Transaction;
import com.flux.domain.TransactionStatus;
import com.flux.domain.User;
import com.flux.provider.UserProvider;

@Component
public class UserProviderJPAImpl implements UserProvider {
	private static final Logger LOGGER = Logger.getLogger(UserProviderJPAImpl.class);

	private UserDAO userDAO;

	public User getUserByLoginAndPassword(String login, String password) {

		User result = userDAO.getUserByLoginAndPassword(login, password);
		return result;
	}

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
