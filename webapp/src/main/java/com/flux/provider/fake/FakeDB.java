package com.flux.provider.fake;

import java.util.ArrayList;
import java.util.List;

import com.flux.domain.User;

public class FakeDB {

	private static final String EMAIL = "flux@flux.com";
	private static final String PASSWORD = "Flux1";
	private static final String FLUX = "Flux";
	private static final String SECURITY_KEY = "Security key";
	private static List<User> users;
	static {
		setUsers(formUsers());
	}

	private static List<User> formUsers() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setLogin(EMAIL);
		user.setPassword(PASSWORD);
		user.setName(FLUX);
		user.setSurname(FLUX);
		user.setSecurityKey(SECURITY_KEY);
		users.add(user);
		return users;
	}

	public static void setUsers(List<User> users) {
		FakeDB.users = users;
	}

	public static List<User> getUsers() {
		return users;
	}
}
