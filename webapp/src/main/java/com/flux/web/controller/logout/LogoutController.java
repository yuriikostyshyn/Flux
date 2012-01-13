package com.flux.web.controller.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logout.do")
public class LogoutController {

	public static final String LOGIN_PAGE_PATH = "redirect:/login.do";

	@RequestMapping(method = RequestMethod.GET)
	public String provideLogout (HttpServletRequest request, HttpServletResponse response){
		return LOGIN_PAGE_PATH;
	}
}
