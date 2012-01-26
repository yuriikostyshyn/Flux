package com.flux.dbs.dao;

import com.flux.domain.User;

public abstract class UserDao extends AbstractDao {

	public abstract User getUserByLoginAndPassword(String login, String password);
}
