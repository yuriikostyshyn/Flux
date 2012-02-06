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
