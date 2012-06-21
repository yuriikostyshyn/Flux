package com.flux.dbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.flux.dbs.dao.TransactionDAO;
import com.flux.domain.Transaction;
import com.flux.persistence.entities.TransactionEntity;

@Component
public class TransactionDAOImpl extends AbstractDAO implements TransactionDAO {

	public static final String GET_TRANSACTIONS_BY_ACCOUNT_TO_ID_QUERY_NAME = "getTransactionsByAccountToId";

	@Override
	public List<Transaction> getTransactionsByAccountToId(Long accountId) {
		List<Transaction> resultTransactions = new ArrayList<Transaction>();

		Query getTransactionsQuery = entityManager.createNamedQuery(GET_TRANSACTIONS_BY_ACCOUNT_TO_ID_QUERY_NAME);
		getTransactionsQuery.setParameter(1, accountId);

		List<TransactionEntity> resultTransactionEntities = getTransactionsQuery.getResultList();

		if (!resultTransactionEntities.isEmpty()) {
			for (TransactionEntity transactionEntityItem : resultTransactionEntities) {
				Transaction resultTransactionItem = mapper.map(transactionEntityItem, Transaction.class);
				resultTransactions.add(resultTransactionItem);
			}
		}
		return resultTransactions;
	}

}
