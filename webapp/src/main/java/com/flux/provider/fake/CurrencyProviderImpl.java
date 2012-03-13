package com.flux.provider.fake;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.flux.domain.Currency;
import com.flux.provider.CurrencyProvider;

@Component
public class CurrencyProviderImpl extends CurrencyProvider {

	@Override
	public Map<String, Currency> getAllCurrenciesMap() {
		Map<String, Currency> resultCurrenciesMap = new HashMap<String, Currency>();
		
		List<Currency> resultCurrencies = FakeDB.getAllCurrencies();
		
		for(Currency currency:resultCurrencies){
			resultCurrenciesMap.put(currency.getName(), currency);	
		}
		
		return resultCurrenciesMap;
	}

}
