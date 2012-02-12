package com.flux.provider.fake;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.dbs.dao.TransactionDAO;
import com.flux.dbs.dao.impl.TransactionDAOImpl;
import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

@Component
public class TransactionProviderImpl implements TransactionProvider{

	public List<Transaction> getTransactionByAccountId(long accountToId) {
		List<Transaction> resultTransactions = FakeDB.getTransactions();
	return resultTransactions;
	}
}
