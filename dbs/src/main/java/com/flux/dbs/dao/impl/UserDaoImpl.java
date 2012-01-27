package com.flux.dbs.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flux.dbs.dao.UserDAO;
import com.flux.domain.User;
import com.flux.persistence.dto.UserDTO;

@Component
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class UserDAOImpl extends UserDAO {

	private Mapper mapper;

	@Override
	public User getUserByLoginAndPassword(String login,
			String password) {
		User resultUser = null;
		Query getUserQuery = entityManager.createNamedQuery("userByLoginPass");
		getUserQuery.setParameter(1, login);
		getUserQuery.setParameter(2, password);
		List<UserDTO> queryResultList = getUserQuery.getResultList();
		if (!queryResultList.isEmpty()) {
			UserDTO resultUserDTO = queryResultList.get(0);
			resultUser = mapper.map(resultUserDTO, User.class);
		}
		return resultUser;
	}

	@Autowired
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
