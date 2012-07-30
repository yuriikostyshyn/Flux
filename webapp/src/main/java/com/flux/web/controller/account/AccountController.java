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
import com.flux.domain.User;
import com.flux.manager.AccountsManager;
import com.flux.manager.AuthenticationManager;
import com.flux.manager.CurrencyManager;
import com.flux.manager.UsersManager;
import com.flux.web.util.propertyeditor.CurrencyPropertyEditor;
import com.flux.web.util.propertyeditor.UserPropertyEditor;
import com.flux.web.util.validator.AccountValidator;

@Controller
public class AccountController {

	public static final String CURRENCIES_ATTRIBUTE_NAME = "currencies";

	public static final String USERS_ATTRIBUTE_NAME = "users";

	public static final String NEW_ACCOUNT_ATTRIBUTE_NAME = "newAccount";

	public static final String REDIRECT_PATH_PART = "redirect:";

	public static final String SHOW_ACCOUNTS_SERVLET_PATH = "/showAccounts.do";

	public static final String GET_ACCOUNT_REVIEW_SERVLET_PATH = "/getAccountReview.do";

	public static final String NEW_ACCOUNT_SERVLET_PATH = "/newAccount.do";

	public static final String ACCOUNTS_VIEW_PAGE_PATH = "homepage/accountsView";

	public static final String NEW_ACCOUNT_PAGE_PATH = "homepage/newAccountPage";

	private AccountsManager accountsManager;
	private CurrencyManager currenciesManager;
	private CurrencyPropertyEditor currencyEditor;
	private AccountValidator accountValidator;
	private UserPropertyEditor userEditor;
	private UsersManager usersManager;

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
	public String initNewAccountForm(ModelMap model, HttpSession session) {

		Account newAccount = new Account();
		newAccount.setCurrency(new Currency());
		
		User currentUser = (User) session.getAttribute(AuthenticationManager.USER_ATTRIBUTE_NAME);
		newAccount.setUser(currentUser);
		
		model.addAttribute(NEW_ACCOUNT_ATTRIBUTE_NAME, newAccount);

		return NEW_ACCOUNT_PAGE_PATH;
	}

	@RequestMapping(value = NEW_ACCOUNT_SERVLET_PATH, method = RequestMethod.POST)
	public String addNewAccount(@ModelAttribute(NEW_ACCOUNT_ATTRIBUTE_NAME) Account newAccount,
			BindingResult bindingResult, SessionStatus status, HttpSession session) {
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
		userEditor.setUsers(populateUsers());
		binder.registerCustomEditor(Currency.class, currencyEditor);
		binder.registerCustomEditor(User.class, userEditor);
	}

	

	@ModelAttribute(CURRENCIES_ATTRIBUTE_NAME)
	protected Map<String, Currency> populateCurrencies() {
		Map<String, Currency> result = currenciesManager.getCurrencies();

		return result;
	}
	
	@ModelAttribute(USERS_ATTRIBUTE_NAME)
	protected Map<String, User> populateUsers() {
		Map<String, User> result = usersManager.getUsers();
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
	public void setCurrencyManager(CurrencyManager currenciesManager) {
		this.currenciesManager = currenciesManager;
	}

	@Autowired
	public void setCurrencyEditor(CurrencyPropertyEditor currencyEditor) {
		this.currencyEditor = currencyEditor;
	}

	@Autowired
	public void setAccountValidator(AccountValidator accountValidator) {
		this.accountValidator = accountValidator;
	}
	
	@Autowired
	public void setUserEditor(UserPropertyEditor userEditor) {
		this.userEditor = userEditor;
	}

	@Autowired
	public void setUsersManager(UsersManager usersManager) {
		this.usersManager = usersManager;
	}

	
}
