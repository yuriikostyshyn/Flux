package com.flux.web.util.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.flux.domain.Account;

@Component
public class AccountPropertyEditor extends PropertyEditorSupport {
	Map<String, Account> accounts;

	public String getAsText() {
		String result = "empty account";

		if (getValue() != null) {
			Account account = (Account) getValue();
			
			Long accountId = account.getAccountId();
			result = accountId.toString();
		}

		return result;
	}

	public void setAsText(String name) {
		Account account = accounts.get(name);
		setValue(account);
	}

	public void setAccounts(List<Account> accounts) {
		Map<String, Account> result = new HashMap<String, Account>();

		ListIterator<Account> accountsIterator = accounts.listIterator();

		while (accountsIterator.hasNext()) {
			Account currentAccount = accountsIterator.next();

			Long currentAccountId = currentAccount.getAccountId();
			
			result.put(currentAccountId.toString(), currentAccount);
		}
		this.accounts = result;
	}

}
