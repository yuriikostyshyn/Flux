package com.flux.dbs.dao;

import com.flux.domain.User;

public abstract class UserDAO extends AbstractDAO {

	public abstract User getUserByLoginAndPassword(String login, String password);
}
