package com.flux.provider.fake;

import java.util.List;

import org.springframework.stereotype.Component;

import com.flux.domain.Transaction;
import com.flux.provider.TransactionProvider;

@Component
public class TransactionProviderImpl implements TransactionProvider{

	public List<Transaction> getTransactionByAccountToId(long accountToId) {
		
	return null;
	}

}
