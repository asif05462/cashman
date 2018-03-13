package com.wns.cashman.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wns.cashman.service.IAtmWithdrawal;
import com.wns.cashman.util.RequestBean;
import com.wns.cashman.util.ResponseBean;

@RestController
public class CashManController {

	private static final Logger logger = Logger.getLogger(CashManController.class);

	@Autowired
	private IAtmWithdrawal atmWitdrawal;


	@RequestMapping(value="/cashman" , method=RequestMethod.POST)
	public ResponseBean withDrawAmount(RequestBean requestBean){
		logger.info("Inside CashManController withDrawAmount() " + requestBean);
		int amount = Integer.parseInt(requestBean.getAmount());

		ResponseBean responseBean = atmWitdrawal.withdrawCash(amount);
		
		if(responseBean.getAmount()> 0) {			
			return responseBean;					
		}
		else if(responseBean.getAmount()>= responseBean.getTotalCashLeft()) {			
			return responseBean;					
		}
		
		logger.info("Executed CashManController withDrawAmount() " + requestBean);
		return null;	
	}

}
