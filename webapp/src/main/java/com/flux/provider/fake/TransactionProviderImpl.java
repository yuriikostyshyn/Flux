package com.flux.provider.fake;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.connection.jms.JmsConnector;
import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

@Component
public class TransactionProviderImpl implements TransactionProvider{
	private JmsConnector jmsConnector;

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
	public void saveNewTransaction(Transaction transaction) {
		jmsConnector.sendTransactionMessage(transaction);
		}
	
	@Autowired
	public void setJmsConnector(JmsConnector jmsConnector) {
		this.jmsConnector = jmsConnector;
	}


}
