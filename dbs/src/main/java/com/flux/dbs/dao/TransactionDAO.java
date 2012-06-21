package com.flux.dbs.dao;

import java.util.List;

import com.flux.domain.Transaction;

public interface TransactionDAO{
	
	public List<Transaction> getTransactionsByAccountToId(Long accountId);
	
}
