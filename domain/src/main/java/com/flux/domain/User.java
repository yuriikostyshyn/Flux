package com.flux.domain;

import com.flux.domain.utils.HashCodeUtil;

import static com.flux.domain.utils.HashCodeUtil.hash;

public class User {
	private int userId;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String securityKey;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	public boolean equals(Object instanceToCompare) {
		boolean result = false;

		if (instanceToCompare != null) {
			if (instanceToCompare.getClass().equals(getClass())) {
				if (instanceToCompare == this) {
					result = true;
				} else if (instanceToCompare.hashCode() == hashCode()) {

					User userToCompare = (User) instanceToCompare;

					if (userToCompare.getUserId() == getUserId()) {
						result = true;
					}
				}
			}
		}
		return result;

	}

	public int hashCode() {
		return hash(HashCodeUtil.SEED, getUserId());
	}
}
