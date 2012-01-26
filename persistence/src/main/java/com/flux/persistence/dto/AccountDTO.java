package com.flux.persistence.dto;


import java.io.Serializable;
import javax.persistence.*;

import com.flux.persistence.dto.AccountPKDTO;
import com.flux.persistence.dto.CurrencyDTO;
import com.flux.persistence.dto.TransactionDTO;
import com.flux.persistence.dto.UserDTO;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name="account")
public class AccountDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccountPKDTO id;

	private BigInteger idBank;

	//bi-directional many-to-one association to Currency
    @ManyToOne
	@JoinColumn(name="idCurrency", insertable=false, updatable=false)
	private CurrencyDTO currency;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="idUser")
	private UserDTO user;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="account")
	private List<TransactionDTO> transactions;

    public AccountDTO() {
    }

	public AccountPKDTO getId() {
		return this.id;
	}

	public void setId(AccountPKDTO id) {
		this.id = id;
	}
	
	public BigInteger getIdBank() {
		return this.idBank;
	}

	public void setIdBank(BigInteger idBank) {
		this.idBank = idBank;
	}

	public CurrencyDTO getCurrency() {
		return this.currency;
	}

	public void setCurrency(CurrencyDTO currency) {
		this.currency = currency;
	}
	
	public UserDTO getUser() {
		return this.user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public List<TransactionDTO> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}
	
}