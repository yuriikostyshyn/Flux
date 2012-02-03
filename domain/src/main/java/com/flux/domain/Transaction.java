package com.flux.domain;

import java.util.Date;

public class Transaction {
	private long transctionId;
	private Account accountFrom;
	private Account accountTo;
	private Date startDate;
	private Date endDate;
	private Double amount;
	private TransactionStatus status;

	public long getTransctionId() {
		return transctionId;
	}

	public void setTransctionId(long transctionId) {
		this.transctionId = transctionId;
	}

	public Account getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(Account accountFrom) {
		this.accountFrom = accountFrom;
	}

	public Account getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
}
