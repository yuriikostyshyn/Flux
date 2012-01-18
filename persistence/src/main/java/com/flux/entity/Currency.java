package com.flux.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.flux.entity.Account;


import java.util.List;


/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@Table(name="currency")
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCurrency;

	private String fullName;

	private String name;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="currency")
	private List<Account> accounts;

    public Currency() {
    }

	public int getIdCurrency() {
		return this.idCurrency;
	}

	public void setIdCurrency(int idCurrency) {
		this.idCurrency = idCurrency;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
}