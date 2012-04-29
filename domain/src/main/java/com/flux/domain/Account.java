package com.flux.domain;

import com.flux.domain.utils.HashCodeUtil;

import static com.flux.domain.utils.HashCodeUtil.hash;

public class Account {
	private User user;
	private long accountId;
	private int bankId;
	private Currency currency;
	private double amount;
	private String securityKey;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

					Account accountToCompare = (Account) instanceToCompare;

					if (accountToCompare.getAccountId() == getAccountId()) {
						result = true;
					}
				}
			}
		}
		return result;
	}

	public int hashCode() {
		return hash(HashCodeUtil.SEED, getAccountId());

	}
}
