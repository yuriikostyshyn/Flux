package com.flux.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

@Component
public class TransactionsManager {

	public static final String TRANSACTIONS_ATTRIBUTE_NAME = "transactions";
	public static final String ACCOUNT_ID_ATTRIBUTE_NAME = "accountId";

	private TransactionProvider transactionProvider;

	public void getAllTransactions(Map<String, Object> model) {
		List<Transaction> resultTransactions = transactionProvider.getAllTransactions();

		model.put(TRANSACTIONS_ATTRIBUTE_NAME, resultTransactions);
	}

	@Autowired
	public void setTransactionProvider(TransactionProvider transactionProvider) {
		this.transactionProvider = transactionProvider;
	}
}
