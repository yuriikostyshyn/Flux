package com.flux.dbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flux.dbs.dao.AccountDAO;
import com.flux.domain.Account;
import com.flux.persistence.entities.AccountEntity;

@Component
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class AccountDAOImpl extends AccountDAO {

	public static final String GET_ACCOUNTS_BY_USER_ID_QUERY_NAME = "getAccountsByUserId";

	@Override
	public List<Account> getAccountsByUserId(int userId) {
		List<Account> resultAccounts = new ArrayList<Account>();

		Query getAccountsQuery = entityManager.createNamedQuery(GET_ACCOUNTS_BY_USER_ID_QUERY_NAME);
		getAccountsQuery.setParameter(1, userId);
		
		List<AccountEntity> resultAccountEntities = getAccountsQuery.getResultList();

		if (!resultAccountEntities.isEmpty()) {
			for (AccountEntity accountEntityItem : resultAccountEntities) {
				Account resultAccountItem = mapper.map(accountEntityItem, Account.class);
				resultAccounts.add(resultAccountItem);
			}
		}
		return resultAccounts;

	}
}
