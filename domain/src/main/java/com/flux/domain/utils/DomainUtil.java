package com.flux.domain.utils;

import com.flux.domain.User;

public class DomainUtil {

	private static final int ALMOST_INVALID_USER_ID = -25;

	public static User emptyUser() {
		User result = new User();

		result.setUserId(ALMOST_INVALID_USER_ID);

		return result;
	}
}
