package com.flux.manager;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

@Component
public class TransactionsManager {

	public static final String TRANSACTIONS_ATTRIBUTE_NAME = "transactions";
	private static final Logger LOGGER = Logger.getLogger(TransactionsManager.class);
	public static final String ACCOUNT_ID_ATTRIBUTE_NAME = "accountId";

	private TransactionProvider transactionProvider;

	

	public void getTransactions(Map<String,Object> model) {
		List<Transaction> resultTransactions = Collections.emptyList();

		long accountId = getAccountIdFromModel(model);		
		if(accountId !=	0){
		resultTransactions = transactionProvider.getTransactionByAccountToId(accountId);
		}
		
		model.put(TRANSACTIONS_ATTRIBUTE_NAME, resultTransactions);
	}

	private long getAccountIdFromModel(Map<String,Object> model) {
		long result = 0;
		try {
			result = (Long) model.get(ACCOUNT_ID_ATTRIBUTE_NAME);
			} catch (Exception ex) {
			LOGGER.error("Account id is absent", ex);
		}
		return result;
	}
	
	@Autowired
	public void setTransactionProvider(TransactionProvider transactionProvider) {
		this.transactionProvider = transactionProvider;
	}
}
