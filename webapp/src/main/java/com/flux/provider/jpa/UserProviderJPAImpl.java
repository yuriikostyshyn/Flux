package com.flux.provider.jpa;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public Map<String, User> getAllUsersMap() {
		Map<String, User> result = Collections.emptyMap();

		List<User> usersList = userDAO.getAllUsers();
		if (!usersList.isEmpty()) {
			result = new HashMap<String, User>();
			for (User user : usersList) {
				result.put(user.getLogin(), user);
			}
		}
		return result;
	}

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
