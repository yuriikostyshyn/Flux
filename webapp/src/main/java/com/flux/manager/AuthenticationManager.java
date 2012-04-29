package com.flux.manager;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.flux.domain.User;
import com.flux.domain.utils.DomainUtil;
import com.flux.provider.UserProvider;

@Component
public class AuthenticationManager {

	public static final String USER_ATTRIBUTE_NAME = "user";
	public static final String INVALID_DATA_ERROR_ATTRIBUTE_NAME = "invalidDataError";
	public static final String INVALID_DATA_ERROR_MESSAGE = "Correct login and password is required";

	private static final Logger LOGGER = Logger.getLogger(AuthenticationManager.class);

	private UserProvider userProvider;

	public ModelMap authenticate(String login, String password, HttpSession session) {
		ModelMap result = new ModelMap();

		User user = userProvider.getUserByLoginAndPassword(login, password);

		if (!user.equals(DomainUtil.emptyUser())) {
			session.setAttribute(USER_ATTRIBUTE_NAME, user);
		} else {
			result.addAttribute(INVALID_DATA_ERROR_ATTRIBUTE_NAME, INVALID_DATA_ERROR_MESSAGE);
		}

		return result;
	}

	@Autowired
	public void setUserProvider(UserProvider userProvider) {
		this.userProvider = userProvider;
	}

}
