package com.flux.provider.fake;

import java.util.List;

import org.springframework.stereotype.Component;

import com.flux.domain.Account;
import com.flux.provider.AccountProvider;

@Component
public class AccountProviderImpl implements AccountProvider{

	@Override
	public List<Account> getAccountsByUserId(int userId) {
		List<Account> resultAccounts = FakeDB.getAccountsByUserId(userId);
		return resultAccounts;
	}

}
