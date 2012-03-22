package com.flux.provider.fake;

import java.util.List;

import org.springframework.stereotype.Component;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

@Component
public class TransactionProviderImpl implements TransactionProvider{

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> resultTransactions = FakeDB.getTransactions();
	return resultTransactions;
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(long accountId) {
		return FakeDB.getTransactionsByAccountId(accountId);
	}

	@Override
	public void saveNewTransaction(Transaction mockTransaction) {
		// TODO Auto-generated method stub		
	}
}
