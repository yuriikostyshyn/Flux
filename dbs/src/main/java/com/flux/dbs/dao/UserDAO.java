package com.flux.dbs.dao;

import com.flux.domain.User;

public interface UserDAO {

	public User getUserByLoginAndPassword(String login, String password);
}
