package com.flux.manager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.domain.User;
import com.flux.provider.UserProvider;

@Component
public class AuthenticationManager {
	private static final Logger LOGGER = Logger.getLogger(AuthenticationManager.class);

	private UserProvider userProvider;

	public User authenticate(String login, String password) {
		User user = userProvider.getUserByLoginAndPassword(login, password);
		if (user == null) {
			LOGGER.debug("User with loin: " + login
					+ "not found or password is wrong");
		} else {
			LOGGER.debug("User " + user + " logined");
		}

		return user;
	}

	@Autowired
	public void setUserProvider(UserProvider userProvider) {
		this.userProvider = userProvider;
	}

	public UserProvider getUserProvider() {
		return userProvider;
	}

}
