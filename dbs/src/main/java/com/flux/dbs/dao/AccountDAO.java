package com.flux.dbs.dao;

import java.util.List;

import com.flux.domain.Account;
import com.flux.domain.User;

public abstract class AccountDAO extends AbstractDAO{
	
	public abstract List<Account> getAllAccountForGivenUser(User givenUser);
	
}
