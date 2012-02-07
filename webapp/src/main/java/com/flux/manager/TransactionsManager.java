package com.flux.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

@Component
public class TransactionsManager {

	private TransactionProvider transactionProvider;

	@Autowired
	public void setTransactionProvider(TransactionProvider transactionProvider) {
		this.transactionProvider = transactionProvider;
	}

	public List<Transaction> getTransactions(Map accountId) {
		// TODO Auto-generated method stub
		return null;
	}
}
