package com.flux.provider;

import com.flux.domain.User;

public interface UserProvider {

	User getUserByLoginAndPassword(String login, String password);

}
