package com.flux.domain;

import java.util.Date;
import com.flux.domain.utils.HashCodeUtil;

import static com.flux.domain.utils.HashCodeUtil.hash;

public class Transaction {
	private long transactionId;
	private Account accountFrom;
	private Account accountTo;
	private Date startDate;
	private Date endDate;
	private Double amount;
	private TransactionStatus status;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
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

	public boolean equals(Object instanceToCompare) {
		boolean result = false;

		if (instanceToCompare != null) {
			if (instanceToCompare.getClass().equals(getClass())) {
				if (instanceToCompare == this) {
					result = true;
				} else if (instanceToCompare.hashCode() == hashCode()) {

					Transaction transactionToCompare = (Transaction) instanceToCompare;

					if (transactionToCompare.getTransactionId() == getTransactionId()) {
						result = true;
					}
				}
			}
		}

		return result;

	}

	public int hashCode() {
		return hash(HashCodeUtil.SEED, getTransactionId());
	}
}
