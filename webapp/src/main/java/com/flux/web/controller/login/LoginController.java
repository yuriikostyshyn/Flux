package com.flux.web.controller.login;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flux.domain.User;
import com.flux.provider.UserProvider;

@Controller
@RequestMapping("/login.do")
public class LoginController {

	private UserProvider userProvider;

	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showLoginScreen() {
		return "login/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(String login, String password, HttpSession session) {

		String returnString = "login/login";
		User user = userProvider.getUserByLoginAndPassword(login, password);
		if (user != null) {
			session.setAttribute("user", user);
			LOGGER.debug("User " + user + " loggined");
			returnString = "redirect:/home.do";
		}
		return returnString;
	}

	@Autowired
	public void setUserProvider(UserProvider userProvider) {
		this.userProvider = userProvider;
	}

	public UserProvider getUserProvider() {
		return userProvider;
	}
}
