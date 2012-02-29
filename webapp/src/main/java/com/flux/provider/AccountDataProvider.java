package com.flux.provider;

import com.flux.domain.Account;

public interface AccountDataProvider {
	public Account getAccountById(long accountId);
}
