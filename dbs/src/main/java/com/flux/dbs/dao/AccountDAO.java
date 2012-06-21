package com.flux.dbs.dao;

import java.util.List;

import com.flux.domain.Account;

public interface AccountDAO {
	
	public List<Account> getAccountsByUserId(int userId);
	
}
