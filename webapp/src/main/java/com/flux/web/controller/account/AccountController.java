package com.flux.web.controller.account;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.flux.domain.Account;
import com.flux.domain.Currency;
import com.flux.manager.AccountsManager;
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

	private AccountsManager accountsManager;
	private CurrencyPropertyEditor currencyEditor;
	private AccountValidator accountValidator;

	@RequestMapping(value = SHOW_ACCOUNTS_SERVLET_PATH, method = RequestMethod.GET)
	public String showAccountsByUserId(HttpSession session, ModelMap model) {
		String result = ACCOUNTS_VIEW_PAGE_PATH;

		accountsManager.addAccountsToSession(session);
		initNewAccountInModel(model);

		return result;
	}

	@RequestMapping(value = GET_ACCOUNT_REVIEW_SERVLET_PATH, method = RequestMethod.GET)
	public ModelAndView showAccountReviewByAccountId(@RequestParam long selectedAccountId) {
		ModelAndView result = new ModelAndView(ACCOUNTS_VIEW_PAGE_PATH);

		ModelMap modelMap = accountsManager.addAccountReviewByAccountIdToModel(selectedAccountId);
		result.addAllObjects(modelMap);

		return result;
	}

	@RequestMapping(value = NEW_ACCOUNT_SERVLET_PATH, method = RequestMethod.GET)
	public String initNewAccountForm(ModelMap model) {

		Account newAccount = new Account();
		newAccount.setCurrency(new Currency());
		model.addAttribute(NEW_ACCOUNT_ATTRIBUTE_NAME, newAccount);

		return NEW_ACCOUNT_PAGE_PATH;
	}

	@RequestMapping(value = NEW_ACCOUNT_SERVLET_PATH, method = RequestMethod.POST)
	public String addNewAccount(@ModelAttribute(NEW_ACCOUNT_ATTRIBUTE_NAME) Account newAccount,
			BindingResult bindingResult, SessionStatus status) {
		String result = REDIRECT_PATH_PART + SHOW_ACCOUNTS_SERVLET_PATH;

		accountValidator.validate(newAccount, bindingResult);

		if (!bindingResult.hasErrors()) {
			accountsManager.saveNewAccount(newAccount);
		} else {
			result = NEW_ACCOUNT_PAGE_PATH;
		}

		return result;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		currencyEditor.setCurrencies(populateCurrencies());
		binder.registerCustomEditor(Currency.class, currencyEditor);
	}

	@ModelAttribute(CURRENCIES_ATTRIBUTE_NAME)
	public Map<String, Currency> populateCurrencies() {
		Map<String, Currency> result = accountsManager.getCurrencies();

		return result;
	}

	private void initNewAccountInModel(ModelMap model) {
		model.addAttribute(NEW_ACCOUNT_ATTRIBUTE_NAME, new Account());
	}

	@Autowired
	public void setAccountManager(AccountsManager accountsManager) {
		this.accountsManager = accountsManager;
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
