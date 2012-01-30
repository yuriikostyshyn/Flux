package com.flux.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.flux.persistence.entities.AccountEntity;

import java.math.BigInteger;
import java.util.Date;

/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigInteger transactionId;

	private AccountEntity accountFrom;

	private AccountEntity accountTo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	private Double amount;

	private StatusEntity status;
	
	private CurrencyEntity currency;

	public TransactionEntity() {
	}

	@Id
	@Column(name = "transaction_id")
	public BigInteger getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(BigInteger transactionId) {
		this.transactionId = transactionId;
	}

	@JoinColumn(name = "account_id_from", insertable = false, updatable = false)
	public AccountEntity getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(AccountEntity accountFrom) {
		this.accountFrom = accountFrom;
	}

	@JoinColumn(name = "account_id_to", insertable = false, updatable = false)
	public AccountEntity getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(AccountEntity accountTo) {
		this.accountTo = accountTo;
	}
	
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@ManyToOne
	@JoinColumn(name = "status_id", insertable = false, updatable = false)
	public StatusEntity getStatus() {
		return this.status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name = "currency_id", insertable = false, updatable = false)
	public CurrencyEntity getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEntity currency) {
		this.currency = currency;
	}



}