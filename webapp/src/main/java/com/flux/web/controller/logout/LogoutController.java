package com.flux.web.controller.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout.do")
public class LogoutController {

	public static final String LOGIN_PAGE_PATH = "redirect:/login.do";

	@RequestMapping(method = RequestMethod.GET)
	public String provideLogout (HttpServletRequest request, HttpServletResponse response){
		sessionInvalidating(request);
		return LOGIN_PAGE_PATH;
	}

	private void sessionInvalidating(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
}
