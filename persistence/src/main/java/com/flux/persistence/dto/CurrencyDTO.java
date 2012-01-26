package com.flux.persistence.dto;

import java.io.Serializable;
import javax.persistence.*;

import com.flux.persistence.dto.AccountDTO;


import java.util.List;


/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@Table(name="currency")
public class CurrencyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCurrency;

	private String fullName;

	private String name;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="currency")
	private List<AccountDTO> accounts;

    public CurrencyDTO() {
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

	public List<AccountDTO> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}
	
}