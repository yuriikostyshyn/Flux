package com.flux.web.controller.transaction;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flux.domain.Transaction;
import com.flux.manager.TransactionsManager;

@Controller
@RequestMapping("/home/transactionList.do")
public class TransactionListController {

	public static final String ACCOUNT_ID_PARAMETER_NAME = "accountId";
	public static final String HOMEPAGE_PATH = "homepage/homepage";
	public static final String TRANSACTIONS_ATTRIBUTE_NAME = "transactions";
	
	private static final Logger LOGGER = Logger.getLogger(TransactionListController.class);
	
	private TransactionsManager transactionsManager; 
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showTransactionsForAccount(ModelAndView modelAndView){
		ModelAndView resultModelAndView = modelAndView;
		Map model = modelAndView.getModel();
		
		List<Transaction> transactions = transactionsManager.getTransactions(model);
		modelAndView.addObject(TRANSACTIONS_ATTRIBUTE_NAME, transactions);
		
		resultModelAndView.setViewName(HOMEPAGE_PATH);
		return resultModelAndView;
	}

	@Autowired
	public void setTransactionsManager(TransactionsManager transactionsManager) {
		this.transactionsManager = transactionsManager;
	}
}
