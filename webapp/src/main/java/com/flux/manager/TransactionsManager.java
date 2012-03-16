package com.flux.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;
import com.flux.web.util.helper.RequestHelper;

@Component
public class TransactionsManager {

	public static final String TRANSACTIONS_ATTRIBUTE_NAME = "transactions";
	public static final String ACCOUNT_ID_ATTRIBUTE_NAME = "accountId";
	private static final Logger LOGGER = Logger.getLogger(UserManager.class);

	private TransactionProvider transactionProvider;
	private RequestHelper requestHelper;

	public void addAllTransactionsToModel(Map<String, Object> model) {
		List<Transaction> resultTransactions = transactionProvider.getAllTransactions();

		model.put(TRANSACTIONS_ATTRIBUTE_NAME, resultTransactions);
	}

	public Map<String, Object> addTransactionsByAccountIdToModel(HttpServletRequest request) {
		Map<String, Object> resultModel = new HashMap<String, Object>();

		List<Transaction> resultTransactions = getTransactionsByAccountId(request);
		resultModel.put(TRANSACTIONS_ATTRIBUTE_NAME, resultTransactions);

		return resultModel;
	}

	private List<Transaction> getTransactionsByAccountId(HttpServletRequest request) {
		List<Transaction> resultTransactions = Collections.emptyList();
		try {

			long accountId = requestHelper.getSelectedAccountId(request);

			resultTransactions = transactionProvider.getTransactionsByAccountId(accountId);

		} catch (NumberFormatException ex) {
			LOGGER.error("Incorrect type of selected account id parameter", ex);
		}
		return resultTransactions;
	}

	@Autowired
	public void setTransactionProvider(TransactionProvider transactionProvider) {
		this.transactionProvider = transactionProvider;
	}

	@Autowired
	public void setRequestHelper(RequestHelper requestHelper) {
		this.requestHelper = requestHelper;
	}
}
