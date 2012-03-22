package com.flux.web.controller.transaction;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.flux.domain.Account;
import com.flux.domain.Transaction;
import com.flux.manager.AccountManager;
import com.flux.manager.TransactionsManager;
import com.flux.web.controller.account.AccountController;
import com.flux.web.util.propertyeditor.AccountPropertyEditor;
import com.flux.web.util.validator.TransactionValidator;

@Controller
public class TransactionListController {

	public static final String SHOW_TRANSACTIONS_SERVLET_PATH = "/showTransactions.do";
	public static final String ADD_NEW_TRANSACTION_SERVLET_PATH = "/newTransaction.do";

	public static final String HOMEPAGE_PATH = "homepage/transactionsView";
	public static final String NEW_TRANSACTION_PAGE_PATH = "homepage/newTransaction";

	public static final String ACCOUNT_ID_PARAMETER_NAME = "accountId";
	public static final String TRANSACTIONS_ATTRIBUTE_NAME = "transactions";
	public static final String NEW_TRANSACTION_ATTRIBUTE_NAME = "newTransaction";

	private static final Logger LOGGER = Logger.getLogger(TransactionListController.class);

	private TransactionsManager transactionsManager;
	private TransactionValidator transactionValidator;
	private AccountPropertyEditor accountEditor;

	@RequestMapping(value = SHOW_TRANSACTIONS_SERVLET_PATH, method = RequestMethod.GET)
	public ModelAndView showTransactionsByAccountId(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView resultModelAndView = new ModelAndView(HOMEPAGE_PATH);

		Map<String, Object> model = transactionsManager.addTransactionsByAccountIdToModel(request);

		resultModelAndView.addAllObjects(model);
		LOGGER.info("transactions was added to request");

		return resultModelAndView;
	}

	@RequestMapping(value = ADD_NEW_TRANSACTION_SERVLET_PATH, method = RequestMethod.GET)
	public ModelAndView initNewTransactionForm(@RequestParam Long selectedAccountId, ModelMap model) {

		ModelAndView resultModelAndView = new ModelAndView(NEW_TRANSACTION_PAGE_PATH);	
		
		initNewTransactionAttributeInModel(model, selectedAccountId);

		return resultModelAndView;
	}

	@RequestMapping(value = ADD_NEW_TRANSACTION_SERVLET_PATH, method = RequestMethod.POST)
	public String addNewTransaction(@ModelAttribute(NEW_TRANSACTION_ATTRIBUTE_NAME) Transaction transaction, BindingResult bindingResult, SessionStatus sessionStatus) {
		String resultPath = NEW_TRANSACTION_PAGE_PATH;
		transactionValidator.validate(transaction, bindingResult);

		if (!bindingResult.hasErrors()) {
			transactionsManager.saveNewTransaction(transaction);
			resultPath = AccountController.ACCOUNTS_VIEW_PAGE_PATH;
		}

		return resultPath;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder, WebRequest webRequest) {
		initAccountsFieldInAccountEditor(webRequest);

		dataBinder.registerCustomEditor(Account.class, accountEditor);
	}

	private void initNewTransactionAttributeInModel(ModelMap model, Long selectedAccountId) {
		Transaction newTransaction = createNewTransactionInstance(selectedAccountId);

		model.addAttribute(NEW_TRANSACTION_ATTRIBUTE_NAME, newTransaction);
	}

	private Transaction createNewTransactionInstance(Long selectedAccountId) {
		Transaction newTransaction = new Transaction();

		Account accountTo = new Account();
		Account accountFrom = new Account();
		accountFrom.setAccountId(selectedAccountId);

		newTransaction.setAccountTo(accountTo);
		newTransaction.setAccountFrom(accountFrom);
		return newTransaction;
	}

	@SuppressWarnings("unchecked")
	private void initAccountsFieldInAccountEditor(WebRequest webRequest) {
		List<Account> accounts = (List<Account>) webRequest.getAttribute(AccountManager.ACCOUNTS_ATTRIBUTE_NAME,
				RequestAttributes.SCOPE_SESSION);
		accountEditor.setAccounts(accounts);
	}

	@Autowired
	public void setTransactionsManager(TransactionsManager transactionsManager) {
		this.transactionsManager = transactionsManager;
	}

	@Autowired
	public void setTransactionValidator(TransactionValidator transactionValidator) {
		this.transactionValidator = transactionValidator;

	}

	@Autowired
	public void setAccountEditor(AccountPropertyEditor accountEditor) {
		this.accountEditor = accountEditor;
	}

}
