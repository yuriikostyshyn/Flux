package com.flux.provider.jpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.dbs.dao.UserDAO;
import com.flux.domain.User;
import com.flux.provider.UserProvider;

@Component
public class UserProviderJPAImpl implements UserProvider {
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
