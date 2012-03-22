package com.flux.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.flux.persistence.entities.AccountEntity;

import java.util.Date;

/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQueries({@NamedQuery(name="getTransactionsByAccountToId", query="FROM TransactionEntity transaction WHERE transaction.accountTo.accountId = ?")})
@Table(name = "transaction")
public class TransactionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long transactionId;

	private AccountEntity accountFrom;

	private AccountEntity accountTo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	private Double amount;

	private TransactionStatusEntity status;
	
	private CurrencyEntity currency;

	public TransactionEntity() {
	}

	@Id
	@Column(name = "transaction_id")
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	@ManyToOne
	@JoinColumn(name = "account_id_from", insertable = false, updatable = false)
	public AccountEntity getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(AccountEntity accountFrom) {
		this.accountFrom = accountFrom;
	}

	@ManyToOne
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
	public TransactionStatusEntity getStatus() {
		return this.status;
	}

	public void setStatus(TransactionStatusEntity status) {
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