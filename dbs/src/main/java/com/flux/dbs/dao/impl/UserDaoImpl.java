package com.flux.dbs.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flux.dbs.dao.UserDao;
import com.flux.entity.User;

@Component
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class UserDaoImpl extends UserDao {

	private Mapper mapper;

	@Override
	public com.flux.domain.User getUserByLoginAndPassword(String login,
			String password) {
		com.flux.domain.User resultUser = null;
		Query getUserQuery = entityManager.createNamedQuery("userByLoginPass");
		getUserQuery.setParameter(1, login);
		getUserQuery.setParameter(2, password);
		List<User> queryResultList = getUserQuery.getResultList();
		if (!queryResultList.isEmpty()) {
			com.flux.entity.User resultUserDTO = queryResultList.get(0);
			resultUser = mapper.map(resultUserDTO, com.flux.domain.User.class);
		}
		return resultUser;
	}

	@Autowired
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
