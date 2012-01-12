package com.flux.web.controller.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	public static final String LOGIN_PAGE_PATH = "/login.do";

	public String provideLogout (HttpServletRequest request, HttpServletResponse response){
		return LOGIN_PAGE_PATH;
	}
}
