package com.flux.web.controller.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	private HttpServletResponse mockResponse;
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
		String actualReturnedPath = underTest.provideLogout(mockRequest,
				mockResponse);
		Assert.assertEquals(LogoutController.LOGIN_PAGE_PATH, actualReturnedPath);
	}

	@Test
	public void shouldCloseSession() {
		underTest.provideLogout(mockRequest, mockResponse);
		Mockito.verify(mockSession,Mockito.times(1)).invalidate();
	}
}
