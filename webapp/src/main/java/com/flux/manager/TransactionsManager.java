package com.flux.manager;

import java.security.ProviderException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

@Component
public class TransactionsManager {

	public static final String TRANSACTIONS_ATTRIBUTE_NAME = "transactions";
	public static final String ACCOUNT_ID_ATTRIBUTE_NAME = "accountId";
	public static final Logger LOGGER = Logger.getLogger(TransactionsManager.class);

	private TransactionProvider transactionProvider;
	
	public void addAllTransactionsToModel(Map<String, Object> model) {
		List<Transaction> resultTransactions = transactionProvider.getAllTransactions();

		model.put(TRANSACTIONS_ATTRIBUTE_NAME, resultTransactions);
	}

	public ModelMap addTransactionsByAccountIdToModel(long selectedAccountId) {
		ModelMap result = new ModelMap();

		List<Transaction> resultTransactions = getTransactionsByAccountId(selectedAccountId);
		result.put(TRANSACTIONS_ATTRIBUTE_NAME, resultTransactions);

		return result;
	}

	public void saveNewTransaction(Transaction newTransaction) {
		try {
			transactionProvider.saveNewTransaction(newTransaction);
		} catch (ProviderException ex) {
			LOGGER.error(ex.getMessage(), ex);

		}
	}

	private List<Transaction> getTransactionsByAccountId(long selectedAccountId) {
		List<Transaction> result = transactionProvider.getTransactionsByAccountId(selectedAccountId);
		
		return result;
	}

	@Autowired
	public void setTransactionProvider(TransactionProvider transactionProvider) {
		this.transactionProvider = transactionProvider;
	}

}
