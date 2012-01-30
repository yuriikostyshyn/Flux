package com.flux.dbs.dao.impl;

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
public class AccountDAOImpl	extends AccountDAO {

	@Override
	public List<Account> getAllAccountForGivenUser(User givenUser) {
		List<Account> result =null;
		
		Query getAccountsQuery = entityManager.createNamedQuery("getAccountsByUserId");
		UserEntity givenUserEntity = mapper.map(givenUser, UserEntity.class);
		getAccountsQuery.setParameter(1, givenUserEntity);
		List<AccountEntity> resultList = getAccountsQuery.getResultList();
		
		if(!resultList.isEmpty()){
			result = mapper.map(resultList, List.class);
		}
		
		return result;
	}

}
