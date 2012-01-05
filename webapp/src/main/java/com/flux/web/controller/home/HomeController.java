package com.flux.web.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home.do")
public class HomeController {

	private static final Logger LOGGER = Logger.getLogger(HomeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showHomeScreen(HttpServletRequest request) {
		LOGGER.debug("Home page");
		request.setAttribute("user", request.getSession().getAttribute("user"));
		return "homepage/homepage";
	}

}
