package com.flux.persistence.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import com.flux.persistence.entities.AccountEntity;

import java.util.List;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "userByLoginPass", query = "FROM UserEntity u WHERE u.login=? AND u.password=?") })
@Table(name = "user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigInteger id;

	private String securityKey;

	private String login;

	private String name;

	private String password;

	private String surname;

	// bi-directional many-to-one association to Account
	private List<AccountEntity> accounts;

	public UserEntity() {
	}

	@Id
	@Column(name = "user_id")
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Column(name = "security_key")
	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@OneToMany(mappedBy = "user")
	public List<AccountEntity> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}

}