package com.flux.manager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flux.domain.Currency;
import com.flux.provider.CurrencyProvider;

@Component
public class CurrencyManager {
	private CurrencyProvider currencyProvider;

	public Map<String, Currency> getCurrencies() {
		Map<String, Currency> resultCurrencies = currencyProvider.getAllCurrenciesMap();

		return resultCurrencies;
	}

	@Autowired
	public void setCurrencyProvider(CurrencyProvider currencyProvider) {
		this.currencyProvider = currencyProvider;
	}
}
