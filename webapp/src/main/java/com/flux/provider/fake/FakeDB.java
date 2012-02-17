package com.flux.provider.fake;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.flux.domain.Account;
import com.flux.domain.Transaction;
import com.flux.domain.TransactionStatus;
import com.flux.domain.User;

public class FakeDB {

	private static final String EMAIL = "flux@flux.com";
	private static final String PASSWORD = "Flux1";
	private static final String FLUX = "Flux";
	private static final String SECURITY_KEY = "Security key";
	private static final String TRANSACTION_STATUS = "SUCCESS";
	private static List<User> users;
	private static List<Transaction> transactions;
	static {
		setUsers(formUsers());
		setTransactions(formTransactions());
	}

	private static List<User> formUsers() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setLogin(EMAIL);
		user.setPassword(PASSWORD);
		user.setName(FLUX);
		user.setSurname(FLUX);
		user.setSecurityKey(SECURITY_KEY);
		users.add(user);
		return users;
	}

	private static List<Transaction> formTransactions() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1);
		
		TransactionStatus status = new TransactionStatus();
		status.setStatusId(1);
		status.setStatusMessage(TRANSACTION_STATUS);
		transaction.setStatus(status);
		
		Account accountTo = new Account();
		accountTo.setAccountId(1);
		accountTo.setAmount(12.11);
		accountTo.setBankId(1);
		accountTo.setCurrency(null);
		Account accountFrom = accountTo;
		transaction.setAccountTo(accountTo);
		transaction.setAccountFrom(accountFrom);
		
		transaction.setAmount(12.1212);
		
		Date startDate = new Date(System.currentTimeMillis());
		Date endDate = new Date(System.currentTimeMillis() + 1000000);
		transaction.setStartDate(startDate);
		transaction.setEndDate(endDate);
		
		transactions.add(transaction);
		
		Transaction secondTransaction = new Transaction();
		secondTransaction.setTransactionId(2);
		
		TransactionStatus secondStatus = new TransactionStatus();
		secondStatus.setStatusId(1);
		secondStatus.setStatusMessage(TRANSACTION_STATUS);
		secondTransaction.setStatus(secondStatus);
		
		Account secondAccountTo = new Account();
		secondAccountTo.setAccountId(2);
		secondAccountTo.setAmount(12.11);
		secondAccountTo.setBankId(2);
		secondAccountTo.setCurrency(null);
		Account secondAccountFrom = secondAccountTo;
		secondTransaction.setAccountTo(secondAccountTo);
		secondTransaction.setAccountFrom(secondAccountFrom);
		
		secondTransaction.setAmount(12.1212);
		
		Date secondStartDate = new Date(System.currentTimeMillis() - 50000000);
		Date secondEndDate = new Date(System.currentTimeMillis() + 50000000);
		secondTransaction.setStartDate(secondStartDate);
		secondTransaction.setEndDate(secondEndDate);
		transactions.add(secondTransaction);
		return transactions;
	}

	public static void setUsers(List<User> users) {
		FakeDB.users = users;
	}

	public static List<User> getUsers() {
		return users;
	}

	public static List<Transaction> getTransactions() {
		return transactions;
	}

	public static void setTransactions(List<Transaction> transactions) {
		FakeDB.transactions = transactions;
	}
}
