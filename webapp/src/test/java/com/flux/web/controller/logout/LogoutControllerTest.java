package com.flux.web.controller.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LogoutControllerTest {

	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private HttpSession mockSession;

	private LogoutController underTest;

	@Before
	public void setUp() {
		underTest = new LogoutController();
		MockitoAnnotations.initMocks(this);
		Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
	}

	@Test
	public void shouldReturnLoginPagePathString() {
		String actualPath = underTest.provideLogout(mockSession);
		Assert.assertEquals(LogoutController.REDIRECT_LOGIN_PATH, actualPath);
	}

	@Test
	public void shouldCloseSession() {
		underTest.provideLogout(mockSession);
		Mockito.verify(mockSession,Mockito.times(1)).invalidate();
	}
}
