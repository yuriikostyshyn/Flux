package com.flux.dbs.dao;

import java.util.List;

import com.flux.domain.User;

public interface UserDAO {

	public User getUserByLoginAndPassword(String login, String password);

	public List<User> getAllUsers();
}
