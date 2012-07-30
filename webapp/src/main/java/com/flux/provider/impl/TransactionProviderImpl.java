package com.flux.provider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.flux.connection.jms.JmsConnector;
import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

public class TransactionProviderImpl implements TransactionProvider{
	private JmsConnector jmsConnector;
	
	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewTransaction(Transaction newTransaction) {
		jmsConnector.sendTransactionMessage(newTransaction);
	}

	@Autowired
	public void setJmsConnector(JmsConnector jmsConnector) {
		this.jmsConnector = jmsConnector;
	}

}
