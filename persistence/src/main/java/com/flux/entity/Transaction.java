package com.flux.entity;


import java.io.Serializable;
import javax.persistence.*;

import com.flux.entity.Account;


import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@Table(name="transaction")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idTransaction;

    @Temporal( TemporalType.TIMESTAMP)
	private Date dateEnd;

    @Temporal( TemporalType.TIMESTAMP)
	private Date dateStart;

	private BigInteger idAccountFrom;

	private BigInteger idAccountTo;

	private int idStatus;

	//bi-directional many-to-one association to Account
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="idAccount", referencedColumnName="idAccount"),
		@JoinColumn(name="idCurrency", referencedColumnName="idCurrency")
		})
	private Account account;

	//bi-directional many-to-one association to Status
    @ManyToOne
	@JoinColumn(name="Status_idStatus")
	private Status status;

    public Transaction() {
    }

	public String getIdTransaction() {
		return this.idTransaction;
	}

	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Date getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public BigInteger getIdAccountFrom() {
		return this.idAccountFrom;
	}

	public void setIdAccountFrom(BigInteger idAccountFrom) {
		this.idAccountFrom = idAccountFrom;
	}

	public BigInteger getIdAccountTo() {
		return this.idAccountTo;
	}

	public void setIdAccountTo(BigInteger idAccountTo) {
		this.idAccountTo = idAccountTo;
	}

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}