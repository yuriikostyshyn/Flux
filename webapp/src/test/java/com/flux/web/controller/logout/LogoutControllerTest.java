package com.flux.web.controller.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LogoutControllerTest {

	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private HttpServletResponse mockResponse;
	private LogoutController underTest;
	
	@Before
	public void setUp() {
		underTest = new LogoutController();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnLoginPagePathString() {
		String actualReturnedPath = underTest.provideLogout(mockRequest,mockResponse);
		Assert.assertEquals(LogoutController.LOGIN_PAGE_PATH, actualReturnedPath);
	}
	
	@Test
	public void shouldRemoveUserFromRequest() {
		underTest.provideLogout(mockRequest, mockResponse);
		
	}
	@Test
	public void shouldCloseSession(){
		
	}
}
