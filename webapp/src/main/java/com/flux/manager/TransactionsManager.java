package com.flux.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.provider.TransactionProvider;

@Component
public class TransactionsManager {

	private TransactionProvider transactionProvider;

	@Autowired
	public void setTransactionProvider(TransactionProvider transactionProvider) {
		this.transactionProvider = transactionProvider;
	}
}
