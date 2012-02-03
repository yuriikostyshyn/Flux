package com.flux.dbs.dao;

import com.flux.domain.Currency;

public abstract class CurrencyDAO extends AbstractDAO{
	
	public abstract Currency getCurrencyById(Integer currencyId);
	
}
