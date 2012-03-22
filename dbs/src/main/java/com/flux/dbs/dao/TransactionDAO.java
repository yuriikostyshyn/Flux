package com.flux.dbs.dao;

import java.util.List;

import com.flux.domain.Transaction;

public abstract class TransactionDAO extends AbstractDAO{
	
	public abstract List<Transaction> getTransactionsByAccountToId(Long accountId);
	
}
