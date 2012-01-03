package com.flux.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LoginFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		final HttpSession httpSession = httpServletRequest.getSession();
		final boolean isLoginUri = httpServletRequest.getRequestURI().endsWith(
				"login.do");
		final boolean isLoginedUser = httpSession.getAttribute("user") != null;
		LOGGER.debug("URI:" + httpServletRequest.getRequestURI());
		if (isLoginUri && isLoginedUser) {
			httpServletResponse.sendRedirect("/flux/home.do");
		} else if (!isLoginUri && !isLoginedUser) {
			httpServletResponse.sendRedirect("/flux/login.do");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
