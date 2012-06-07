package com.flux.web.controller.login;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.flux.domain.User;
import com.flux.domain.utils.DomainUtil;
import com.flux.manager.AuthenticationManager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class LoginControllerTest {

	private static final String INCORRECT_LOGIN = "incorrect login";
	private static final String INCORRECT_PASSWORD = "incorrect password";
	private static final String CORRECT_LOGIN = "correct login";
	private static final String CORRECT_PASSWORD = "correct password";

	@Mock
	private AuthenticationManager mockManager;
	@Mock
	private HttpSession mockSession;

	private LoginController underTest;
	private ModelMap errorModelMap;

	@Before
	public void setUp() {
		underTest = new LoginController();
		errorModelMap = new ModelMap();
		
		MockitoAnnotations.initMocks(this);
		underTest.setAuthenticationManager(mockManager);
	}

	
	@Test
	public void shouldReturnLinkToLoginJsp(){
		String actualPath = underTest.showLoginScreen();
		
		assertEquals(LoginController.LOGIN_JSP, actualPath);
	}
	
	@Test
	public void shouldReturnViewToLoginPageIFCredentialsWereIncorrect(){
		initAuthenticationManagerForIncorrectCredentials();
		
		ModelAndView actualModelAndView = underTest.authenticateUser(INCORRECT_LOGIN, INCORRECT_PASSWORD, mockSession);
		String actualPath = actualModelAndView.getViewName();

		assertEquals(LoginController.LOGIN_JSP, actualPath);

	}
	
	@Test
	public void shouldReturnViewToTheHomePageIfCredentialsWereCorrect() {
		initAuthenticationManagerForCorrectCredentials();

		ModelAndView actualModelAndView = underTest.authenticateUser(CORRECT_LOGIN, CORRECT_PASSWORD, mockSession);
		String actualPath = actualModelAndView.getViewName();

		assertEquals(LoginController.REDIRECT_HOME_DO, actualPath);

	}
	
		private void initAuthenticationManagerForIncorrectCredentials() {
		errorModelMap.addAttribute(AuthenticationManager.INVALID_DATA_ERROR_ATTRIBUTE_NAME,
				AuthenticationManager.INVALID_DATA_ERROR_MESSAGE);
		when(mockManager.authenticate(INCORRECT_LOGIN, INCORRECT_PASSWORD, mockSession)).thenReturn(errorModelMap);
	}

	private void initAuthenticationManagerForCorrectCredentials() {
		when(mockManager.authenticate(CORRECT_LOGIN, CORRECT_PASSWORD, mockSession)).thenReturn(errorModelMap);
	}

}
