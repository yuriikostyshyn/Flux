package com.flux.web.util.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class RequestHelper {
	public static final String SELECTED_ACCOUNT_ID_PARAMETER_NAME = "selectedAccountId";
	
	public long getSelectedAccountId(HttpServletRequest request) throws NumberFormatException {
		String selectedIdParameter = request.getParameter(SELECTED_ACCOUNT_ID_PARAMETER_NAME);
		long resultSelectedAccountId = Long.valueOf(selectedIdParameter);
		
		return resultSelectedAccountId;
	}
}
