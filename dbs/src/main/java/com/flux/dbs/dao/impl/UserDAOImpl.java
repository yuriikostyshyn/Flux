package com.flux.dbs.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flux.dbs.dao.UserDAO;
import com.flux.domain.User;
import com.flux.domain.utils.DomainUtil;
import com.flux.persistence.entities.UserEntity;

@Component
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class UserDAOImpl extends AbstractDAO implements UserDAO {

	public static final String GET_USER_BY_LOGIN_PASSWORD_QUERY_NAME = "userByLoginPass";

	@Override
	public User getUserByLoginAndPassword(String login, String password) {
		User resultUser = DomainUtil.emptyUser();
		
		Query getUserQuery = entityManager.createNamedQuery(GET_USER_BY_LOGIN_PASSWORD_QUERY_NAME);
		getUserQuery.setParameter(1, login);
		getUserQuery.setParameter(2, password);
		
		List<UserEntity> resultUserEntities = getUserQuery.getResultList();
		
		if (!resultUserEntities.isEmpty()) {
			UserEntity resultUserEntity = resultUserEntities.get(0);
			resultUser = mapper.map(resultUserEntity, User.class);
		}
		return resultUser;
	}
}
