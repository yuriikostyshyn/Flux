package com.flux.provider;

import java.util.Map;

import com.flux.domain.Currency;

public abstract class CurrencyProvider {
	public abstract Map<String, Currency> getAllCurrenciesMap();
}
