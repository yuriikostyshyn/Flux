package com.flux.provider;

import java.util.List;

import com.flux.domain.Transaction;

public interface TransactionProvider {
	public List<Transaction> getTransactionByAccountId(long accountToId);
}
