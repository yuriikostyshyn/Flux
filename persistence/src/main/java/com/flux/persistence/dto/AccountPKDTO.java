package com.flux.persistence.dto;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the account database table.
 * 
 */
@Embeddable
public class AccountPKDTO implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String idAccount;

	private int idCurrency;

    public AccountPKDTO() {
    }
	public String getIdAccount() {
		return this.idAccount;
	}
	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}
	public int getIdCurrency() {
		return this.idCurrency;
	}
	public void setIdCurrency(int idCurrency) {
		this.idCurrency = idCurrency;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AccountPKDTO)) {
			return false;
		}
		AccountPKDTO castOther = (AccountPKDTO)other;
		return 
			this.idAccount.equals(castOther.idAccount)
			&& (this.idCurrency == castOther.idCurrency);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idAccount.hashCode();
		hash = hash * prime + this.idCurrency;
		
		return hash;
    }
}