package com.flux.web.controller.login;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flux.manager.AuthenticationManager;
@Controller
@RequestMapping("/login.do")
public class LoginController {

	public static final String LOGIN_JSP = "login/login";
	public static final String REDIRECT_HOME_DO = "redirect:/home.do";

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	private AuthenticationManager authenticationManager;

	@RequestMapping(method = RequestMethod.GET)
	public String showLoginScreen() {
		return LOGIN_JSP;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView authenticateUser(@RequestParam String login, @RequestParam String password, HttpSession session) {
		ModelAndView resultModelAndView = new ModelAndView(LOGIN_JSP);

		ModelMap errorModelMap = authenticationManager.authenticate(login, password,session);

		if (!errorModelMap.containsKey(AuthenticationManager.INVALID_DATA_ERROR_ATTRIBUTE_NAME)) {
			resultModelAndView.setViewName(REDIRECT_HOME_DO);
		} else {
			resultModelAndView.addAllObjects(errorModelMap);
		}

		return resultModelAndView;
	}

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
