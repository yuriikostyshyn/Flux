package com.flux.provider.fake;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flux.domain.Account;
import com.flux.domain.Currency;
import com.flux.domain.Transaction;
import com.flux.domain.TransactionStatus;
import com.flux.domain.User;

public class FakeDB {

	private static final int USER_ID = 1;
	private static final String EMAIL = "flux@flux.com";
	private static final String PASSWORD = "Flux1";
	private static final String FLUX = "Flux";
	private static final String SECURITY_KEY = "Security key";
	private static final String TRANSACTION_STATUS = "SUCCESS";
	private static List<User> users;
	private static List<Transaction> transactions;
	private static List<Account> accounts;
	private static List<Currency> currencies;
	static {
		setUsers(formUsers());
		setCurrencies(formCurrencies());
		setTransactions(formTransactions());
		setAccounts(formAccounts());
	}

	private static List<User> formUsers() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUserId(USER_ID);
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
		accountTo.setCurrency(currencies.get(1));
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
		secondAccountTo.setCurrency(currencies.get(0));
		Account secondAccountFrom = secondAccountTo;
		secondTransaction.setAccountTo(secondAccountTo);
		secondTransaction.setAccountFrom(secondAccountFrom);

		secondTransaction.setAmount(12.1212);

		Date secondStartDate = new Date(System.currentTimeMillis() - 50000000);
		Date secondEndDate = new Date(System.currentTimeMillis() + 50000000);
		secondTransaction.setStartDate(secondStartDate);
		secondTransaction.setEndDate(secondEndDate);
		transactions.add(secondTransaction);

		Transaction thirdTransaction = new Transaction();
		thirdTransaction.setTransactionId(3);

		TransactionStatus thirdStatus = new TransactionStatus();
		thirdStatus.setStatusId(1);
		thirdStatus.setStatusMessage(TRANSACTION_STATUS);
		thirdTransaction.setStatus(thirdStatus);

		Account thirdAccountTo = new Account();
		thirdAccountTo.setAccountId(1);
		thirdAccountTo.setAmount(12.11);
		thirdAccountTo.setBankId(1);
		thirdAccountTo.setCurrency(currencies.get(1));
		Account thirdAccountFrom = accountTo;
		thirdTransaction.setAccountTo(thirdAccountTo);
		thirdTransaction.setAccountFrom(thirdAccountFrom);

		thirdTransaction.setAmount(12.1212);

		Date thirdStartDate = new Date(System.currentTimeMillis());
		Date thirdEndDate = new Date(System.currentTimeMillis() + 1000000);
		thirdTransaction.setStartDate(thirdStartDate);
		thirdTransaction.setEndDate(thirdEndDate);

		transactions.add(thirdTransaction);

		Transaction forthTransaction = new Transaction();
		forthTransaction.setTransactionId(4);

		TransactionStatus forthStatus = new TransactionStatus();
		forthStatus.setStatusId(1);
		forthStatus.setStatusMessage(TRANSACTION_STATUS);
		forthTransaction.setStatus(forthStatus);

		Account forthAccountTo = new Account();
		forthAccountTo.setAccountId(2);
		forthAccountTo.setAmount(12.11);
		forthAccountTo.setBankId(2);
		forthAccountTo.setCurrency(currencies.get(0));
		Account forthAccountFrom = forthAccountTo;
		forthTransaction.setAccountTo(forthAccountTo);
		forthTransaction.setAccountFrom(forthAccountFrom);

		forthTransaction.setAmount(12.1212);

		Date forthStartDate = new Date(System.currentTimeMillis() - 50000000);
		Date forthEndDate = new Date(System.currentTimeMillis() + 50000000);
		forthTransaction.setStartDate(forthStartDate);
		forthTransaction.setEndDate(forthEndDate);
		transactions.add(forthTransaction);
		return transactions;
	}

	private static List<Account> formAccounts() {
		List<Account> resultAccounts = new ArrayList<Account>();

		Currency hryvnja = new Currency();
		hryvnja.setCurrencyId(1);
		hryvnja.setLongName("hryvnja");
		hryvnja.setName("UAH");

		Currency dollar = new Currency();
		dollar.setCurrencyId(1);
		dollar.setLongName("dollar");
		dollar.setName("USD");

		User firstUser = new User();
		firstUser.setUserId(1);

		User secondUser = new User();
		secondUser.setUserId(2);

		Account accountOne = new Account();
		accountOne.setAccountId(1);
		accountOne.setBankId(1);
		accountOne.setCurrency(dollar);
		accountOne.setSecurityKey("securityKey");
		accountOne.setUser(firstUser);
		accountOne.setAmount(1243);
		resultAccounts.add(accountOne);

		Account accountTwo = new Account();
		accountTwo.setAccountId(2);
		accountTwo.setBankId(2);
		accountTwo.setCurrency(hryvnja);
		accountTwo.setSecurityKey("securityKey");
		accountTwo.setUser(firstUser);
		accountTwo.setAmount(1222);
		resultAccounts.add(accountTwo);

		Account accountThree = new Account();
		accountThree.setAccountId(3);
		accountThree.setBankId(1);
		accountThree.setCurrency(hryvnja);
		accountThree.setSecurityKey("securityKey");
		accountThree.setUser(secondUser);
		accountThree.setAmount(3333);
		resultAccounts.add(accountThree);

		Account accountFour = new Account();
		accountFour.setAccountId(4);
		accountFour.setBankId(2);
		accountFour.setCurrency(dollar);
		accountFour.setSecurityKey("securityKey");
		accountFour.setUser(secondUser);
		accountFour.setAmount(44.33);
		resultAccounts.add(accountFour);
		return resultAccounts;
	}

	private static List<Currency> formCurrencies() {
		List<Currency> resultCurrencies = new ArrayList<Currency>();

		Currency uah = new Currency();
		uah.setCurrencyId(1);
		uah.setLongName("hryvna");
		uah.setName("UAH");
		resultCurrencies.add(uah);

		Currency usd = new Currency();
		usd.setCurrencyId(2);
		usd.setLongName("US dollar");
		usd.setName("USD");
		resultCurrencies.add(usd);

		return resultCurrencies;
	}

	public static List<Account> getAccountsByUserId(int userId) {
		List<Account> resultAccounts = new ArrayList<Account>();

		for (Account account : accounts) {
			if (account.getUser().getUserId() == userId) {
				resultAccounts.add(account);
			}
		}

		return resultAccounts;
	}

	public static Account getAccountById(long accountId) {
		Account resultAccount = new Account();

		for (Account account : accounts) {
			if (account.getAccountId() == accountId) {
				resultAccount = account;
			}
		}
		return resultAccount;
	}

	public static List<Currency> getAllCurrencies() {
		return currencies;
	}

	public static List<Transaction> getTransactionsByAccountId(long accountId) {
		List<Transaction> resultTransactions = new ArrayList<Transaction>();

		for (Transaction transaction : transactions) {
			Account accountFrom = transaction.getAccountFrom();
			if (accountFrom.getAccountId() == accountId) {
				resultTransactions.add(transaction);
			}
		}
		return resultTransactions;
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

	public static void setAccounts(List<Account> accounts) {
		FakeDB.accounts = accounts;
	}

	public static void setCurrencies(List<Currency> currencies) {
		FakeDB.currencies = currencies;
	}

}
