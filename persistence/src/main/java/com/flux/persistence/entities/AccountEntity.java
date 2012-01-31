package com.flux.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.flux.persistence.entities.CurrencyEntity;
import com.flux.persistence.entities.UserEntity;

import java.math.BigInteger;

/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQueries({@NamedQuery(name="getAccountsByUserId", query="FROM AccountEntity accountEntity WHERE accountEntity.user.id = ?")})
@Table(name = "account")
public class AccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigInteger accountId;

	private BigInteger bankId;

	// bi-directional many-to-one association to Currency
	private CurrencyEntity currency;

	// bi-directional many-to-one association to User
	private UserEntity user;

	private Double amount;

	private String securityKey;

	public AccountEntity() {
	}

	@Id
	@Column(name = "account_id")
	public BigInteger getAccountId() {
		return accountId;
	}

	public void setAccountId(BigInteger accountid) {
		this.accountId = accountid;
	}

	@Column(name = "bank_id")
	public BigInteger getBankId() {
		return bankId;
	}

	public void setBankId(BigInteger bankId) {
		this.bankId = bankId;
	}

	@ManyToOne
	@JoinColumn(name = "currency_id", insertable = false, updatable = false)
	public CurrencyEntity getCurrency() {
		return this.currency;
	}

	public void setCurrency(CurrencyEntity currency) {
		this.currency = currency;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "security_key")
	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
}