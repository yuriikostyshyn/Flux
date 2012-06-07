package com.flux.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import com.flux.domain.User;
import com.flux.domain.utils.DomainUtil;
import com.flux.provider.UserProvider;

public class AuthenticationManagerTest {

	private static final String INCORRECT_LOGIN = "incorrect login";
	private static final String INCORRECT_PASSWORD = "incorrect password";
	private static final String CORRECT_LOGIN = "correct login";
	private static final String CORRECT_PASSWORD = "correct password";

	@Mock
	private UserProvider mockUserProvider;
	@Mock
	private HttpSession mockSession;

	private AuthenticationManager underTest;

	@Before
	public void setUp() {
		underTest = new AuthenticationManager();

		MockitoAnnotations.initMocks(this);

		underTest.setUserProvider(mockUserProvider);
	}

	@Test
	public void shouldAddErrorMessageToModelIfCredentialsWereNotCorrect() {
		initUserProviderForIncorrectCredentials();

		ModelMap actualModelMap = underTest.authenticate(INCORRECT_LOGIN, INCORRECT_PASSWORD, mockSession);
		String actualErrorMessage = actualModelMap.get(AuthenticationManager.INVALID_DATA_ERROR_ATTRIBUTE_NAME)
				.toString();

		assertEquals(AuthenticationManager.INVALID_DATA_ERROR_MESSAGE, actualErrorMessage);
	}

	@Test
	public void shouldAddUserToTheSessionIfCredentialsWereCorrect() {
		User correctUser = new User();
		when(mockUserProvider.getUserByLoginAndPassword(CORRECT_LOGIN, CORRECT_PASSWORD)).thenReturn(correctUser);

		underTest.authenticate(CORRECT_LOGIN, CORRECT_PASSWORD, mockSession);

		verify(mockSession).setAttribute(AuthenticationManager.USER_ATTRIBUTE_NAME, correctUser);

	}

	private void initUserProviderForIncorrectCredentials() {
		when(mockUserProvider.getUserByLoginAndPassword(INCORRECT_LOGIN, INCORRECT_PASSWORD)).thenReturn(DomainUtil.emptyUser());
	}
}
