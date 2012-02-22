package com.flux.web.controller.account;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flux.manager.AccountManager;

@Controller
public class AccountController {
	
	public static final String ACCOUNTS_VIEW_PAGE_PATH = "homepage/accountsView";
	
	private AccountManager accountManager;

	@RequestMapping(value="/showAccounts.do" , method=RequestMethod.GET)
	public ModelAndView showAccountsByUserId(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView resultModelAndView = new ModelAndView(ACCOUNTS_VIEW_PAGE_PATH);
		
		 Map<String, Object> model = accountManager.addAccountsByUserIdToModel(request);				
		
		 resultModelAndView.addAllObjects(model);
		return resultModelAndView;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
}
