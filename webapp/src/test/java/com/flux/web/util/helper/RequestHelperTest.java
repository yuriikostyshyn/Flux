package com.flux.web.util.helper;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class RequestHelperTest {

	private static final long CORRECT_NUMBER_RESULT = 1L;

	private static final String CORRECT_PARAMETER_STRING = "1";

	private static final String NOT_NUMBER_STRING = "not number string";

	@Mock
	private HttpServletRequest mockRequest;

	private RequestHelper underTest;

	@Before
	public void setUp() {
		underTest = new RequestHelper();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnAccountIdIfRequestParameterIsCorrect() {
		Mockito.when(mockRequest.getParameter(RequestHelper.SELECTED_ACCOUNT_ID_PARAMETER_NAME)).thenReturn(CORRECT_PARAMETER_STRING);

		long resultAccountId = underTest.getSelectedAccountId(mockRequest);

		Assert.assertEquals(CORRECT_NUMBER_RESULT, resultAccountId);
	}

	@Test(expected = NumberFormatException.class)
	public void shouldThrowExceptionIfAccountIdParameterIsAbsent() {
		Mockito.when(mockRequest.getParameter(RequestHelper.SELECTED_ACCOUNT_ID_PARAMETER_NAME)).thenReturn(null);

		underTest.getSelectedAccountId(mockRequest);
	}

	@Test(expected = NumberFormatException.class)
	public void shouldThrowExceptionIfAccountIdParameterIsIncorrectNumberString() {
		Mockito.when(mockRequest.getParameter(RequestHelper.SELECTED_ACCOUNT_ID_PARAMETER_NAME)).thenReturn(NOT_NUMBER_STRING);

		underTest.getSelectedAccountId(mockRequest);
	}
}
