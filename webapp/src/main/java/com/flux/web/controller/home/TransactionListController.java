package com.flux.web.controller.home;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flux.manager.TransactionsManager;

@Controller
@RequestMapping("/home/transactionList.do")
public class TransactionListController {

	private static final Logger LOGGER = Logger.getLogger(TransactionListController.class);
	
	private TransactionsManager transactionsManager; 
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showTransactionsForUser(ModelAndView modelAndView){
		ModelAndView resultModelAndView = modelAndView;
		
		return resultModelAndView;
	}

	@Autowired
	public void setTransactionsManager(TransactionsManager transactionsManager) {
		this.transactionsManager = transactionsManager;
	}
}
