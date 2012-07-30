package com.flux.manager;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.domain.User;
import com.flux.provider.UserProvider;

@Component
public class UsersManager {
	private UserProvider userProvider;

	public Map<String, User> getUsers() {
		Map<String, User> result = userProvider.getAllUsersMap();
		return result;
	}

	@Autowired
	public void setUserProvider(UserProvider userProvider) {
		this.userProvider = userProvider;
	}

}
