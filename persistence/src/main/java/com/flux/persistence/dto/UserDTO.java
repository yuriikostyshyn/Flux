package com.flux.persistence.dto;

import java.io.Serializable;
import javax.persistence.*;

import com.flux.persistence.dto.AccountDTO;

import java.util.List;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "userByLoginPass", query = "FROM UserDTO u WHERE u.login=? AND u.password=?") })
@Table(name = "user")
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String securityKey;

	private String login;

	private String name;

	private String password;

	private String surname;

	// bi-directional many-to-one association to Account
	@OneToMany(mappedBy = "user")
	private List<AccountDTO> accounts;

	public UserDTO() {
	}


	@Column(name = "idUser")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "keyBank")
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

	public List<AccountDTO> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}

}