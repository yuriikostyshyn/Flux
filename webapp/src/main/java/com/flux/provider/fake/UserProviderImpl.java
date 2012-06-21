package com.flux.provider.fake;

import java.util.List;

import org.springframework.stereotype.Component;

import com.flux.domain.User;
import com.flux.provider.UserProvider;

@Component
public class UserProviderImpl implements UserProvider {

	public User getUserByLoginAndPassword(String login, String password) {
		User userToReturn = null;
		final List<User> users = FakeDB.getUsers();
		for (User user : users) {
			if (user.getPassword().equals(password)
					&& user.getLogin().equals(login)) {
				userToReturn = user;
				break;
			}
		}
		return userToReturn;
	}

}
