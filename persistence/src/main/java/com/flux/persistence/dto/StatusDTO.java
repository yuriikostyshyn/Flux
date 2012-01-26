package com.flux.persistence.dto;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@Table(name="status")
public class StatusDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStatus;

	private String message;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="status")
	private List<TransactionDTO> transactions;

    public StatusDTO() {
    }

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TransactionDTO> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}
	
}