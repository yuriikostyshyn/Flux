package com.flux.web.controller.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flux.manager.AuthenticationManager;

public class LoginControllerTest {

	@Mock
	private AuthenticationManager mockManager;

	private LoginController underTest;

	@Before
	public void setUp() {
		underTest = new LoginController();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnUserToLoginPageIfCredentialsWereNotCorrect() {

	}

	@Test
	public void shouldAddErrorMessageToModelIfCredentialsWereNotCorrect() {

	}

	@Test
	public void shouldAddUserToTheSessionIfCredentialsWereCorrect() {

	}

	@Test
	public void shouldReturnLinkToTheHomePageIfCredentialsWereCorrect() {

	}
}
