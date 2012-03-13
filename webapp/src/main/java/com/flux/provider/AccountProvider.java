package com.flux.provider;

import java.util.List;

import com.flux.domain.Account;

public interface AccountProvider {
	public List<Account> getAccountsByUserId(int userId);
	public void saveNewAccount(Account account);
}
