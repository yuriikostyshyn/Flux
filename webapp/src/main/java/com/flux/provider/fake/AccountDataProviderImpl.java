package com.flux.provider.fake;

import org.springframework.stereotype.Component;

import com.flux.domain.Account;
import com.flux.provider.AccountDataProvider;

@Component
public class AccountDataProviderImpl implements AccountDataProvider {

	@Override
	public Account getAccountById(long accountId) {
		Account resultAccount = new Account();
		if (FakeDB.getAccountById(accountId) != null) {
			resultAccount = FakeDB.getAccountById(accountId);
		}
		return resultAccount;
	}

}
