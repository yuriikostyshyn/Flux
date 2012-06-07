package com.flux.web.controller.logout;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout.do")
public class LogoutController {

	public static final String REDIRECT_LOGIN_PATH = "redirect:/login.do";

	@RequestMapping(method = RequestMethod.GET)
	public String provideLogout (HttpSession session){
		
		session.invalidate();
		
		return REDIRECT_LOGIN_PATH;
	}
}
