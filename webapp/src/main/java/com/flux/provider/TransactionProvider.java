package com.flux.provider;

import java.util.List;

import com.flux.domain.Transaction;

public interface TransactionProvider {
	public List<Transaction> getAllTransactions();
	public List<Transaction> getTransactionsByAccountId(long accountId);
	public void saveNewTransaction(Transaction newTransaction);
}
