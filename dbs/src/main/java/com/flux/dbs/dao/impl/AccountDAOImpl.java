package com.flux.dbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flux.dbs.dao.AccountDAO;
import com.flux.domain.Account;
import com.flux.domain.User;
import com.flux.persistence.entities.AccountEntity;
import com.flux.persistence.entities.UserEntity;

@Component
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class AccountDAOImpl extends AccountDAO {

	@Override
	public List<Account> getAllAccountForGivenUser(User givenUser) {
		List<Account> resultAccounts = new ArrayList<Account>();

		Query getAccountsQuery = entityManager.createNamedQuery("getAccountsByUserId");
		getAccountsQuery.setParameter(1, givenUser.getUserId());
		
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
