package com.flux.web.controller.home;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/home/transactionList.do")
public class TransactionListController {

	private static final Logger LOGGER = Logger.getLogger(TransactionListController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showTransactionsForUser(ModelAndView modelAndView){
		ModelAndView resultModelAndView = modelAndView;
		
		return resultModelAndView;
	}
}
