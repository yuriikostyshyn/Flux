package com.flux.web.util.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.flux.domain.Currency;

@Component
public class CurrencyPropertyEditor extends PropertyEditorSupport{
	
	Map<String,Currency> currencies;
	
	public String getAsText(){
		String result = "empty currency";
		
		if(getValue()!=null){
		Currency currencyInstance = (Currency)getValue();
		result = currencyInstance.getName();
		}
		return result;
	}
	
	public void setAsText(String name){
		Currency currency = currencies.get(name);
		setValue(currency);
	}
	
	public void setCurrencies(Map<String, Currency> currencies) {
		this.currencies = currencies;
	}
}
