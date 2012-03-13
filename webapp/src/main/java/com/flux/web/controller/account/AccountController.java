package com.flux.web.controller.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.flux.domain.Account;
import com.flux.domain.Currency;
import com.flux.manager.AccountManager;
import com.flux.web.util.propertyeditor.CurrencyPropertyEditor;
import com.flux.web.util.validator.AccountValidator;

@Controller
public class AccountController {

	public static final String CURRENCIES_ATTRIBUTE_NAME = "currencies";

	public static final String NEW_ACCOUNT_ATTRIBUTE_NAME = "newAccount";

	public static final String REDIRECT_PATH_PART = "redirect:";

	public static final String SHOW_ACCOUNTS_SERVLET_PATH = "/showAccounts.do";

	public static final String GET_ACCOUNT_REVIEW_SERVLET_PATH = "/getAccountReview.do";

	public static final String NEW_ACCOUNT_SERVLET_PATH = "/newAccount.do";

	public static final String ACCOUNTS_VIEW_PAGE_PATH = "homepage/accountsView";

	public static final String NEW_ACCOUNT_PAGE_PATH = "homepage/newAccountPage";

	private AccountManager accountManager;
	private CurrencyPropertyEditor currencyEditor;
	private AccountValidator accountValidator;

	@RequestMapping(value = SHOW_ACCOUNTS_SERVLET_PATH, method = RequestMethod.GET)
	public ModelAndView showAccountsByUserId(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView resultModelAndView = new ModelAndView(ACCOUNTS_VIEW_PAGE_PATH);

		accountManager.addAccountsToResult(request);
		model.addAttribute(NEW_ACCOUNT_ATTRIBUTE_NAME, new Account());
		return resultModelAndView;
	}

	@RequestMapping(value = GET_ACCOUNT_REVIEW_SERVLET_PATH, method = RequestMethod.GET)
	public ModelAndView showAccountReviewByAccountId(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> model = accountManager.addAccountReviewByAccountIdToModel(request);

		return getAccountsPageModelAndViewWithAttributes(model);
	}

	@RequestMapping(value = NEW_ACCOUNT_SERVLET_PATH, method = RequestMethod.GET)
	public String initNewAccountForm(ModelMap model) {

		Account newAccount = new Account();
		newAccount.setCurrency(new Currency());
		model.addAttribute(NEW_ACCOUNT_ATTRIBUTE_NAME, newAccount);

		return NEW_ACCOUNT_PAGE_PATH;
	}

	@RequestMapping(value = NEW_ACCOUNT_SERVLET_PATH, method = RequestMethod.POST)
	public String addNewAccount(@ModelAttribute(NEW_ACCOUNT_ATTRIBUTE_NAME) Account newAccount, BindingResult result,
			SessionStatus status) {
		String resultPath = REDIRECT_PATH_PART + SHOW_ACCOUNTS_SERVLET_PATH;
		
		accountValidator.validate(newAccount, result);
		
		if (!result.hasErrors()) {
			accountManager.saveNewAccount(newAccount);
		} else {
			resultPath = NEW_ACCOUNT_PAGE_PATH;
		}
		
		return resultPath;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		currencyEditor.setCurrencies(populateCurrencies());
		binder.registerCustomEditor(Currency.class, currencyEditor);
	}

	@ModelAttribute(CURRENCIES_ATTRIBUTE_NAME)
	public Map<String, Currency> populateCurrencies() {
		Map<String, Currency> resultCurrencies = accountManager.getCurrencies();
		return resultCurrencies;
	}

	private ModelAndView getAccountsPageModelAndViewWithAttributes(Map<String, Object> model) {
		ModelAndView resultModelAndView = new ModelAndView(ACCOUNTS_VIEW_PAGE_PATH);

		resultModelAndView.addAllObjects(model);

		return resultModelAndView;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@Autowired
	public void setCurrencyEditor(CurrencyPropertyEditor currencyEditor) {
		this.currencyEditor = currencyEditor;
	}

	@Autowired
	public void setAccountValidator(AccountValidator accountValidator) {
		this.accountValidator = accountValidator;
	}

}
