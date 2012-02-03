package com.flux.provider;

import com.flux.domain.User;

public interface UserProvider {

	public User getUserByLoginAndPassword(String login, String password);

}
