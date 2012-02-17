package com.flux.web.controller.transaction;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flux.manager.TransactionsManager;

@Controller
public class TransactionListController {

	public static final String ACCOUNT_ID_PARAMETER_NAME = "accountId";
	public static final String HOMEPAGE_PATH = "homepage/transactionsView";
	public static final String TRANSACTIONS_ATTRIBUTE_NAME = "transactions";
	
	private static final Logger LOGGER = Logger.getLogger(TransactionListController.class);
	
	private TransactionsManager transactionsManager; 
	
	@RequestMapping(value="/showTransactions.do", method=RequestMethod.GET)
	public ModelAndView showAllTransactions(HttpServletRequest request, HttpServletResponse response){
		ModelAndView resultModelAndView = new ModelAndView(HOMEPAGE_PATH);
			
		Map<String,Object> model = new HashMap<String, Object>();
		
		transactionsManager.getAllTransactions(model);
		
		resultModelAndView.addAllObjects(model);
		LOGGER.info("transactions was added to request");
		
		return resultModelAndView;
	}

	@Autowired
	public void setTransactionsManager(TransactionsManager transactionsManager) {
		this.transactionsManager = transactionsManager;
	}
}
