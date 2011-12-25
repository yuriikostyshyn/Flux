package com.flux.web.controller.login;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login.do")
public class LoginController {

	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showLoginScreen() {
		LOGGER.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!test111111111111");
		return "login/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(String login, String password) {

		LOGGER.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!test111111111111"
				+ login + password);
		return "homepage";
	}
}
