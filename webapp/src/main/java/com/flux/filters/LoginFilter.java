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

	private static final String USER_ATTRIBUTE_NAME = "user";
	private static final String FLUX_LOGIN_DO = "/flux/login.do";
	private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		final HttpSession httpSession = httpServletRequest.getSession();
		final String requestURI = httpServletRequest.getRequestURI();
		final boolean isLoginUri = requestURI.endsWith(FLUX_LOGIN_DO);
		final boolean isLoginedUser = httpSession
				.getAttribute(USER_ATTRIBUTE_NAME) != null;
		LOGGER.debug("URI:" + httpServletRequest.getRequestURI());

		if (isLoginUri) {
			chain.doFilter(request, response);
		} else if (!isLoginedUser) {
			httpServletResponse.sendRedirect(FLUX_LOGIN_DO);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
