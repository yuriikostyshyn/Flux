package com.flux.dbs.dao;

import java.util.List;

import com.flux.domain.Account;

public abstract class AccountDAO extends AbstractDAO{
	
	public abstract List<Account> getAccountsByUserId(int userId);
	
}
