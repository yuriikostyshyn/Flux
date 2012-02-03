package com.flux.dbs.dao.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flux.domain.User;
import com.flux.persistence.entities.UserEntity;

public class UserDAOImplTest {
	private static final String LOGIN = "login";

	private static final String PASSWORD = "password";

	private UserDAOImpl underTest;
	private List<UserEntity> resultUserEntities;
	private UserEntity resultUserEntity;

	@Mock
	private EntityManager entityManagerMock;
	@Mock
	private Query getUserQueryMock;
	@Mock
	private Mapper mapperMock;

	
	@Before
	public void setUp() {
		underTest = new UserDAOImpl();
		resultUserEntities = new ArrayList<UserEntity>();
		resultUserEntity = new UserEntity();
		resultUserEntities.add(resultUserEntity);

		MockitoAnnotations.initMocks(this);
		underTest.setEntityManager(entityManagerMock);
		underTest.setMapper(mapperMock);
	}

	@Test
	public void shouldCallMapperForTransaction() {
		when(entityManagerMock.createNamedQuery(UserDAOImpl.GET_USER_BY_LOGIN_PASSWORD_QUERY_NAME)).thenReturn(
				getUserQueryMock);
		when(getUserQueryMock.getResultList()).thenReturn(resultUserEntities);
		underTest.getUserByLoginAndPassword(LOGIN, PASSWORD);
		verify(mapperMock, times(1)).map(resultUserEntity, User.class);
	}
}
