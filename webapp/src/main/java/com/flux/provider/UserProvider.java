package com.flux.provider;

import java.util.Map;

import com.flux.domain.User;

public interface UserProvider {

	public User getUserByLoginAndPassword(String login, String password);

	public Map<String, User> getAllUsersMap();

}
