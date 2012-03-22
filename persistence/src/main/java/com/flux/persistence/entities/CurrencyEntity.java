package com.flux.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@NamedQueries( {@NamedQuery (name ="getCurrencyById", query = " FROM CurrencyEntity currency WHERE currency.currencyId = ?") })
@Table(name="currency")
public class CurrencyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer currencyId;

	private String longName;

	private String name;

    public CurrencyEntity() {
    }

	@Id
	@Column(name = "currency_id")
    public Integer getCurrencyId() {
		return currencyId;
	}


	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	@Column (name="long_name")
	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}